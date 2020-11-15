package ir3;

import java.io.PrintStream;

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
}