package ir3;

import util.LocationRange;
import java.util.function.Consumer;
import java.io.PrintStream;
import java.util.OptionalInt;
import java.util.ArrayList;

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

	/**
	 * Converts this Expr to a RelExp3 - a virtual type that allows RelOps or Terminals only.
	 * This method is overriden by Terminal and BinaryExpr to prevent expansion.
	 */
	public Expr makeRelExp3ByMaybeEmitIR3(LocationRange virtual_range, Context ctx, Consumer<? super ir3.Instruction> out) throws SemanticException {
		int localidx = ctx.newLocal(virtual_range, type);
		out.accept(new ir3.Assign(localidx, this));
		return new LocalVariable(type, localidx);
	}

	public abstract void print(PrintStream w, PrintContext pc);
	public abstract int emitAsm(PrintStream w, int hint_output_reg, EmitFunc ef, EmitContext ctx, boolean optimize);
	public AsmEmitter.Cond emitCondAsm(PrintStream w, int hint_scratch_reg, EmitFunc ef, EmitContext ctx, boolean optimize) {
		assert(false);
		return null;
	}
	public abstract ArrayList<Integer> getUses();
	public abstract ArrayList<Integer> getClobberedRegs();
	public abstract ArrayList<VarRegPair> getRegPreferences();
	public abstract OptionalInt getOutputRegPreference();
}