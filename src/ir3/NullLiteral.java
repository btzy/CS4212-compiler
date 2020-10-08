package ir3;

import java.io.PrintStream;

public class NullLiteral extends Literal {
	public NullLiteral(TypeName type) { super(type); }

	@Override
	public void print(PrintStream w, PrintContext pc) {
		w.print("null"); // null object
	}
}