package ir3;

import java.io.PrintStream;
import java.util.OptionalInt;
import java.util.ArrayList;

public class Label extends Instruction {
	public final int index;
	public Label(int index) { this.index = index; }

	@Override
	public void print(PrintStream w, PrintContext pc) {
		w.print(" Label ");
		w.print(index + 1); // offset so 1-indexed
		w.println(':');
	}

	@Override
	public void emitAsm(PrintStream w, EmitFunc ef, EmitContext ctx, boolean optimize) {
		final String name = ctx.addLabel(ef.env, index);
		w.print(name);
		w.println(':');
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
}