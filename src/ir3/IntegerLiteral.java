package ir3;

import java.io.PrintStream;

public class IntegerLiteral extends Literal {
	public final int value;

	public IntegerLiteral(int value) { super(TypeName.INT); this.value = value; }

	@Override
	public void print(PrintStream w, PrintContext pc) {
		w.print(value);
	}
}