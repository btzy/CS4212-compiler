package ir3;

import java.io.PrintStream;

public class Println extends Instruction {
	public final Terminal term;
	public Println(Terminal term) { this.term = term; }

	@Override
	public void print(PrintStream w, PrintContext pc) {
		w.print("  println(");
		term.print(w, pc);
		w.println(");");
	}
}