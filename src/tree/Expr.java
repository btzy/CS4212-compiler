package tree;

import util.LocationRange;
import ir3.Context;
import ir3.SemanticException;
import java.util.function.Consumer;

public abstract class Expr extends Node {
	public Expr(LocationRange range) { super(range); }

	/**
	 * Generates code as if this expr is a value.
	 * If there are errors, or if this is a method instead, it will throw some SemanticException.
	 */
	public abstract ir3.NullableExpr typeCheckAndEmitIR3(Context ctx, Consumer<? super ir3.Instruction> out) throws SemanticException;

	/**
	 * Generates code as if this expr is a method call.
	 * If there are errors, or if this is a value instead, it will throw some SemanticException.
	 * It cannot return a nullptr_t (because it can't be a function in that way)
	 */
	public ir3.ExprAndFuncIdxArray typeCheckAndEmitIR3ForMethod(Context ctx, Consumer<? super ir3.Instruction> out) throws SemanticException {
		// by default, this is invalid
		// only MemberAccess and VarAccess will override this
		throw new SemanticException("Expected a method name here", range);
	}
}