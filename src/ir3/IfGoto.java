package ir3;

import java.io.PrintStream;

public class IfGoto extends Instruction {
	public final Expr cond;
	public final Label target;
	public IfGoto(Expr cond, Label target) { this.cond = cond; this.target = target; }

	@Override
	public void print(PrintStream w, PrintContext pc) {
		w.print("  if(");
		cond.print(w, pc);
		w.print(") goto ");
		w.print(target.index + 1); // offset so 1-indexed
		w.println(';');
	}
}