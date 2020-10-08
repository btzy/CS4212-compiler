package ir3;

import java.io.PrintStream;

public class Goto extends Instruction {
	public final Label target;
	public Goto(Label target) { this.target = target; }

	@Override
	public void print(PrintStream w, PrintContext pc) {
		w.print("  goto ");
		w.print(target.index + 1); // offset so 1-indexed
		w.println(';');
	}
}