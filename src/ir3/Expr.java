package ir3;

import util.LocationRange;
import java.util.function.Consumer;

/**
 * Represents a Expr with real type (cannot be nullptr_t).
 * In IR3, all expressions are typed.
 */
public abstract class Expr {
	public TypeName type;

	public Expr(TypeName type) {
		this.type = type;
	}

	/**
	 * Converts this Expr to a Terminal.
	 * This method is overriden by Terminal.
	 */
	public Terminal makeTerminalByMaybeEmitIR3(LocationRange virtual_range, Context ctx, Consumer<? super ir3.Instruction> out) throws SemanticException {
		int localidx = ctx.newLocal(virtual_range, type);
		out.accept(new ir3.Assign(localidx, this));
		return new LocalVariable(type, localidx);
	}

	/**
	 * Converts this Expr to a LocalVariable.
	 * This method is overriden by LocalVariable.
	 */
	public LocalVariable makeLocalVariableByMaybeEmitIR3(LocationRange virtual_range, Context ctx, Consumer<? super ir3.Instruction> out) throws SemanticException {
		int localidx = ctx.newLocal(virtual_range, type);
		out.accept(new ir3.Assign(localidx, this));
		return new LocalVariable(type, localidx);
	}
}