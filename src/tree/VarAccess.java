package tree;

import util.LocationRange;
import ir3.Context;
import ir3.NullableExpr;
import ir3.SemanticException;
import java.util.ArrayList;
import java.util.function.Consumer;

public class VarAccess extends Expr {
	private String target;
	public VarAccess(LocationRange range, String target) {
		super(range);
		this.target = target;
	}
	public void print(NestedPrintStream w) {
		w.print(target);
	}
	
	/**
	 * Typecheck and emit IR3 code for this node.
	 */
	public NullableExpr typeCheckAndEmitIR3(Context ctx, Consumer<? super ir3.Instruction> out) throws SemanticException {
		ir3.Context.Entry entry = ctx.lookup(target).orElseThrow(() -> new ir3.NoSuchNameException(target, range));

		if (entry.isLocal) {
			// local variable load
			return NullableExpr.of(new ir3.LocalVariable(entry.type, entry.idx));
		}
		else {
			// implicit 'this'
			return NullableExpr.of(new ir3.MemberExpr(entry.type, ctx.getLocalEnvironment().thisIndex(), entry.idx));
		}
	}
	
	/**
	 * Generates code to look up as a method.
	 */
	public ir3.ExprAndFuncIdxArray typeCheckAndEmitIR3ForMethod(Context ctx, Consumer<? super ir3.Instruction> out) throws SemanticException {
		// lookup method
		// TODO: detect lookup matching a field name
		ArrayList<Integer> methodidxs = ctx.lookupLocalMethod(target);

		return new ir3.ExprAndFuncIdxArray(new ir3.LocalVariable(ctx.thisType(), ctx.getLocalEnvironment().thisIndex()), methodidxs);
	}
}