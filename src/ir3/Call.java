package ir3;

import java.util.OptionalInt;
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
		if (!optimize) {
			{
				int i = args.size();
				EmitFunc ef2 = ef;
				while (i > 4) {
					--i;
					final int reg = args.get(i).emitAsm(w, EmitFunc.Registers.A1, ef2, ctx, optimize);
					AsmEmitter.emitPseudoStrPreOffset(w, EmitFunc.Registers.SP, -4, reg, args.get(i).type);
					ef2 = ef2.transpose(-4);
				}
				while (i > 0) {
					--i;
					final int reg = args.get(i).emitAsm(w, i, ef2, ctx, optimize);
					if (reg != i) AsmEmitter.emitMovReg(w, i, reg);
				}
			}
			AsmEmitter.emitBl(w, ctx.func_specs.get(idx).getMangledName());
			if (args.size() > 4) AsmEmitter.emitAddImm(w, EmitFunc.Registers.SP, EmitFunc.Registers.SP, 4 * (args.size() - 4));
		}
		else {
			CallUtil.emitCall(w, idx, args, ef, ctx, optimize);
		}
	}

	@Override
	public OptionalInt getDef() { return OptionalInt.empty(); }

	@Override
	public ArrayList<Integer> getUses() {
		final ArrayList<Integer> ret = new ArrayList<>();
		for (Terminal term : args) {
			ret.addAll(term.getUses());
		}
		return ret;
	}
	
	@Override
	public ArrayList<Integer> getClobberedRegs() {
		final ArrayList<Integer> ret = new ArrayList<>();
		for (Terminal term : args) ret.addAll(term.getClobberedRegs());
		ret.add(EmitFunc.Registers.A1);
		ret.add(EmitFunc.Registers.A2);
		ret.add(EmitFunc.Registers.A3);
		ret.add(EmitFunc.Registers.A4);
		ret.add(EmitFunc.Registers.LR);
		return ret;
	}
}