package ir3;

import java.util.ArrayList;
import java.io.PrintStream;

public class Call extends Instruction {
	public final int idx; // funcidx
	public final ArrayList<Terminal> args;
	public Call(int idx, ArrayList<Terminal> args) { this.idx = idx; this.args = args; }

	@Override
	public void print(PrintStream w, PrintContext pc) {
		w.print("  ");
		w.print(pc.func_specs.get(idx).getMangledName());
		w.print('(');
		boolean needComma = false;
		for (Terminal arg : args) {
			if (needComma) w.print(',');
			arg.print(w, pc);
			needComma = true;
		}
		w.print(')');
		w.println(';');
	}

	@Override
	public void emitAsm(PrintStream w, EmitFunc ef, EmitContext ctx, boolean optimize) {
		// note: exactly the same as CallExpr
		// put up all the args, in reverse order
		int i = args.size();
		while (i > 4) {
			--i;
			final int reg = args.get(i).emitAsm(w, EmitFunc.Registers.FP, ef, ctx, optimize);
			AsmEmitter.emitPseudoStrPreOffset(w, EmitFunc.Registers.SP, 4, reg, args.get(i).type);
		}
		while (i > 0) {
			--i;
			final int reg = args.get(i).emitAsm(w, i, ef, ctx, optimize);
			if (reg != i) AsmEmitter.emitMovReg(w, i, reg);
		}
		AsmEmitter.emitBl(w, ctx.func_specs.get(idx).getMangledName());
	}
}