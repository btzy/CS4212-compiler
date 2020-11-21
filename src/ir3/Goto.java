package ir3;

import java.io.PrintStream;
import java.util.OptionalInt;
import java.util.ArrayList;

public class Goto extends Instruction {
	public final Label target;
	public Goto(Label target) { this.target = target; }

	@Override
	public void print(PrintStream w, PrintContext pc) {
		w.print("  goto ");
		w.print(target.index + 1); // offset so 1-indexed
		w.println(';');
	}

	@Override
	public void emitAsm(PrintStream w, EmitFunc ef, EmitContext ctx, boolean optimize) {
		final String name = ctx.addLabel(ef.env, target.index);
		AsmEmitter.emitB(w, name);
	}

	@Override
	public OptionalInt getDef() { return OptionalInt.empty(); }

	@Override
	public ArrayList<Integer> getUses() {
		return new ArrayList<>();
	}
	
	@Override
	public ArrayList<Integer> getClobberedRegs() {
		return new ArrayList<>();
	}

	@Override
	public ArrayList<VarRegPair> getRegPreferences() {
		return new ArrayList<>();
	}
}