package ir3;

import java.io.PrintStream;
import java.util.OptionalInt;
import java.util.ArrayList;

public class Assign extends Instruction {
	public final int idx; // localidx
	public final Expr val;
	public Assign(int idx, Expr val) { this.idx = idx; this.val = val; }

	@Override
	public void print(PrintStream w, PrintContext pc) {
		w.print("  ");
		w.print(pc.env.getName(idx));
		w.print(" = ");
		val.print(w, pc);
		w.println(';');
	}

	@Override
	public void emitAsm(PrintStream w, EmitFunc ef, EmitContext ctx, boolean optimize) {
		final EmitFunc.StorageLocation sl = ef.storage_locations.get(idx);
		final int output_reg = val.emitAsm(w, sl.isRegister ? sl.value : EmitFunc.Registers.LR, ef, ctx, optimize);
		AsmEmitter.emitPseudoStoreVariable(w, sl, output_reg, ef.env.getType(idx));
	}

	@Override
	public OptionalInt getDef() { return OptionalInt.of(idx); }

	@Override
	public ArrayList<Integer> getUses() {
		return val.getUses();
	}
	
	@Override
	public ArrayList<Integer> getClobberedRegs() {
		final ArrayList<Integer> ret = new ArrayList<>(val.getClobberedRegs());
		ret.add(EmitFunc.Registers.LR);
		return ret;
	}
}