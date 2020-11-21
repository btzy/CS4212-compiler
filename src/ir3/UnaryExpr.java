package ir3;

import util.LocationRange;
import java.util.function.Consumer;
import java.io.PrintStream;
import java.util.OptionalInt;
import java.util.ArrayList;

/**
 * Represents a UnaryExpr with real type (cannot be nullptr_t).
 * In IR3, all expressions are typed.
 */
public class UnaryExpr extends Expr {
	public final Terminal arg;
	public final UnOp op;

	public UnaryExpr(TypeName type, Terminal arg, UnOp op) {
		super(type);
		this.arg = arg;
		this.op = op;
	}

	@Override
	public Expr makeRelExp3ByMaybeEmitIR3(LocationRange virtual_range, Context ctx, Consumer<? super ir3.Instruction> out) throws SemanticException {
		if (op.isRelOp()) return this;
		return super.makeRelExp3ByMaybeEmitIR3(virtual_range, ctx, out);
	}

	@Override
	public void print(PrintStream w, PrintContext pc) {
		op.print(w);
		arg.print(w, pc);
	}

	@Override
	public int emitAsm(PrintStream w, int hint_output_reg, EmitFunc ef, EmitContext ctx, boolean optimize) {
		final int arg_reg = arg.emitAsm(w, hint_output_reg, ef, ctx, optimize);
		return emitOpInstruction(w, op, hint_output_reg, arg_reg);
	}

	private static int emitOpInstruction(PrintStream w, UnOp op, int output_reg, int arg_reg) {
		switch (op) {
			case MINUS:
				AsmEmitter.emitRsbImm(w, output_reg, arg_reg, 0);
				return output_reg;
			case NEGATION:
				AsmEmitter.emitEorImm(w, output_reg, arg_reg, 1);
				return output_reg;
		}
		assert(false);
		return -1;
	}

	@Override
	public AsmEmitter.Cond emitCondAsm(PrintStream w, int hint_scratch_reg, EmitFunc ef, EmitContext ctx, boolean optimize) {
		final int arg_reg = arg.emitAsm(w, hint_scratch_reg, ef, ctx, optimize);
		return emitCondOpInstruction(w, op, arg_reg);
	}

	private static AsmEmitter.Cond emitCondOpInstruction(PrintStream w, UnOp op, int arg_reg) {
		switch (op) {
			case NEGATION:
				AsmEmitter.emitCmpImm(w, arg_reg, 0);
				return AsmEmitter.Cond.EQ;
		}
		assert(false);
		return null;
	}

	@Override
	public ArrayList<Integer> getUses() {
		return arg.getUses();
	}
	
	@Override
	public ArrayList<Integer> getClobberedRegs() {
		return arg.getClobberedRegs();
	}

	@Override
	public ArrayList<VarRegPair> getRegPreferences() {
		return new ArrayList<>();
	}

	@Override
	public OptionalInt getOutputRegPreference() {
		return OptionalInt.empty();
	}
}