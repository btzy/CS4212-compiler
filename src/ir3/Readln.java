package ir3;

import java.io.PrintStream;
import java.util.OptionalInt;
import java.util.ArrayList;

public class Readln extends Instruction {
	public final int idx;
	public Readln(int idx) { this.idx = idx; }

	@Override
	public void print(PrintStream w, PrintContext pc) {
		w.print("  readln(");
		w.print(pc.env.getName(idx));
		w.println(");");
	}

	@Override
	public void emitAsm(PrintStream w, EmitFunc ef, EmitContext ctx, boolean optimize) {
		assert(false);
		// TODO: add this
	}

	@Override
	public OptionalInt getDef() { return OptionalInt.of(idx); }

	@Override
	public ArrayList<Integer> getUses() {
		return new ArrayList<>();
	}

	@Override
	public ArrayList<Integer> getClobberedRegs() {
		return new ArrayList<>();  // TODO
	}
}