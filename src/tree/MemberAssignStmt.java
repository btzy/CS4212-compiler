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
		final ir3.NullableExpr obj_result_nullable = obj.typeCheckAndEmitIR3(ctx, out);
		final ir3.NullableExpr rhs_result_nullable = expr.typeCheckAndEmitIR3(ctx, out);

		final ir3.Expr obj_result = obj_result_nullable.fixType().orElseThrow(() -> new ir3.NullMemberAccessException(range));

		// new temporary local for obj_result
		final ir3.LocalVariable local = obj_result.makeLocalVariableByMaybeEmitIR3(range, ctx, out);

		// lookup field
		final ir3.Context.FieldEntry fieldentry = ctx.lookupField(obj_result.type, member).orElseThrow(() ->
			new ir3.NoSuchMemberFieldException(member, obj_result.type.name, member_range, ctx.lookupMethod(obj_result.type, member).size()));

		// will throw if the type can't be converted (the only time it might be converted is for nulls)
		final ir3.Expr rhs_result = rhs_result_nullable.imbueType(fieldentry.type).orElseThrow(() ->
			new ir3.TypeImbueAssignException(rhs_result_nullable.getType(), expr.range, fieldentry.type, member, ctx.getFieldRange(obj_result.type, fieldentry.idx)));
		
		// generate the instruction
		out.accept(new ir3.AssignMember(local.idx, fieldentry.idx, rhs_result));
	}
}