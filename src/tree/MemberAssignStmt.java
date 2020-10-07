package tree;

import util.LocationRange;
import ir3.Context;
import ir3.SemanticException;
import java.util.function.Consumer;

public class MemberAssignStmt extends Stmt {
	private Expr obj;
	private String member;
	private LocationRange member_range;
	private Expr expr;
	public MemberAssignStmt(LocationRange range, LocationRange member_range, Expr obj, String member, Expr expr) {
		super(range);
		this.member_range = member_range;
		this.obj = obj;
		this.member = member;
		this.expr = expr;
	}
	public void print(NestedPrintStream w) {
		obj.print(w);
		w.print('.');
		w.print(member);
		w.print('=');
		expr.print(w);
		w.println(';');
	}
	
	/**
	 * Typecheck and emit IR3 code for this node.
	 */
	public void typeCheckAndEmitIR3(Context ctx, Consumer<? super ir3.Instruction> out) throws SemanticException {
		// Order of evaluation of Java is from left-to-right, so we must emit 'obj' before 'expr'
		ir3.NullableExpr obj_result_nullable = obj.typeCheckAndEmitIR3(ctx, out);
		ir3.NullableExpr rhs_result_nullable = expr.typeCheckAndEmitIR3(ctx, out);

		// TODO: throw more specific exception
		ir3.Expr obj_result = obj_result_nullable.fixType().orElseThrow(() -> new SemanticException("Cannot be null here", range));

		// TODO: avoid generating this temporary when not needed
		// new temporary local for obj_result
		int localidx = ctx.newLocal(range, obj_result.type);

		// emit assign to temporary
		out.accept(new ir3.Assign(localidx, obj_result));

		// lookup field
		// TODO: hint method location (if there is one method)
		ir3.Context.FieldEntry fieldentry = ctx.lookupField(obj_result.type, member).orElseThrow(() ->
			new ir3.NoSuchMemberFieldException(member, obj_result.type.name, member_range, ctx.lookupMethod(obj_result.type, member).size()));

		// will throw if the type can't be converted (the only time it might be converted is for nulls)
		// TODO: throw more specific exception
		// TODO: detect lookup matching a method name
		ir3.Expr rhs_result = rhs_result_nullable.imbueType(fieldentry.type).orElseThrow(() -> new SemanticException("Type error", range));
		
		// generate the instruction
		out.accept(new ir3.AssignMember(localidx, fieldentry.idx, rhs_result));
	}
}