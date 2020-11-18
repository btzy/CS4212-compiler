package ir3;

import java.util.ArrayList;
import java.io.PrintStream;

public class CallExpr extends Expr {
	public final int idx; // funcidx
	public final ArrayList<Terminal> args;
	public CallExpr(TypeName result_type, int idx, ArrayList<Terminal> args) { super(result_type); this.idx = idx; this.args = args; }

	@Override
	public void print(PrintStream w, PrintContext pc) {
		w.print(pc.func_specs.get(idx).getMangledName());
		w.print('(');
		boolean needComma = false;
		for (Terminal arg : args) {
			if (needComma) w.print(',');
			arg.print(w, pc);
			needComma = true;
		}
		w.print(')');
	}

	@Override
	public int emitAsm(PrintStream w, int hint_output_reg, EmitFunc ef, EmitContext ctx, boolean optimize) {
		// note: exactly the same as Call instruction
		// put up all the args, in reverse order
		for (int i = 0; i < Math.min(4, args.size()); ++i) {
			final int reg = args.get(i).emitAsm(w, i, ef, ctx, optimize);
			if (reg != i) AsmEmitter.emitMovReg(w, i, reg);
		}
		{
			int i = args.size();
			EmitFunc ef2 = ef;
			while (i > 4) {
				--i;
				final int reg = args.get(i).emitAsm(w, EmitFunc.Registers.FP, ef2, ctx, optimize);
				AsmEmitter.emitPseudoStrPreOffset(w, EmitFunc.Registers.SP, -4, reg, args.get(i).type);
				ef2 = ef2.transpose(-4);
			}
		}
		AsmEmitter.emitBl(w, ctx.func_specs.get(idx).getMangledName());
		if (args.size() > 4) AsmEmitter.emitAddImm(w, EmitFunc.Registers.SP, EmitFunc.Registers.SP, 4 * (args.size() - 4));
		return EmitFunc.Registers.A1;
	}
}