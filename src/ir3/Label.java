package ir3;

import java.io.PrintStream;

public class Label extends Instruction {
	public final int index;
	public Label(int index) { this.index = index; }

	@Override
	public void print(PrintStream w, PrintContext pc) {
		w.print(" Label ");
		w.print(index);
		w.println(':');
	}
}