package ir3;

import java.io.PrintStream;

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
}