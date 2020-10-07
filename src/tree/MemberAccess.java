package tree;

import util.LocationRange;
import ir3.Context;
import ir3.NullableExpr;
import ir3.SemanticException;
import java.util.ArrayList;
import java.util.function.Consumer;

public class MemberAccess extends Expr {
	private Expr obj;
	private String member;
	private LocationRange member_range;
	public MemberAccess(LocationRange range, LocationRange member_range, Expr obj, String member) {
		super(range);
		this.member_range = member_range;
		this.obj = obj;
		this.member = member;
	}
	public void print(NestedPrintStream w) {
		obj.print(w);
		w.print('.');
		w.print(member);
	}
	
	/**
	 * Typecheck and emit IR3 code for this node.
	 */
	public NullableExpr typeCheckAndEmitIR3(Context ctx, Consumer<? super ir3.Instruction> out) throws SemanticException {
		ir3.NullableExpr obj_result_nullable = obj.typeCheckAndEmitIR3(ctx, out);

		// TODO: throw more specific exception
		ir3.Expr obj_result = obj_result_nullable.fixType().orElseThrow(() -> new SemanticException("Cannot be null here", range));

		// new temporary local for obj_result
		int localidx = ctx.newLocal(range, obj_result.type);

		// emit assign to temporary
		out.accept(new ir3.Assign(localidx, obj_result));

		// lookup field
		// TODO: detect lookup matching a method name
		ir3.Context.FieldEntry fieldentry = ctx.lookupField(obj_result.type, member).orElseThrow(() ->
			new ir3.NoSuchMemberFieldException(member, obj_result.type.name, member_range, ctx.lookupMethod(obj_result.type, member).size()));

		// generate the instruction
		return NullableExpr.of(new ir3.MemberExpr(fieldentry.type, localidx, fieldentry.idx));
	}
	
	/**
	 * Generates code to look up as a method.
	 */
	public ir3.ExprAndFuncIdxArray typeCheckAndEmitIR3ForMethod(Context ctx, Consumer<? super ir3.Instruction> out) throws SemanticException {
		ir3.NullableExpr obj_result_nullable = obj.typeCheckAndEmitIR3(ctx, out);

		// TODO: throw more specific exception
		ir3.Expr obj_result = obj_result_nullable.fixType().orElseThrow(() -> new SemanticException("Cannot be null here", range));

		// lookup method
		// TODO: detect lookup matching a method name
		ArrayList<Integer> methodidxs = ctx.lookupMethod(obj_result.type, member);

		return new ir3.ExprAndFuncIdxArray(obj_result, methodidxs);
	}
}