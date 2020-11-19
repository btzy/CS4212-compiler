package ir3;

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
	public void print(PrintStream w, PrintContext pc) {
		op.print(w);
		arg.print(w, pc);
	}

	@Override
	public int emitAsm(PrintStream w, int hint_output_reg, EmitFunc ef, EmitContext ctx, boolean optimize) {
		final int arg_reg = arg.emitAsm(w, EmitFunc.Registers.LR, ef, ctx, optimize);
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
	public ArrayList<Integer> getUses() {
		return arg.getUses();
	}
	
	@Override
	public ArrayList<Integer> getClobberedRegs() {
		ArrayList<Integer> ret = arg.getClobberedRegs();
		ret.add(EmitFunc.Registers.LR);
		return ret;
	}
}