package ir3;

import java.io.PrintStream;

public class BooleanLiteral extends Literal {
	public final boolean value;

	public BooleanLiteral(boolean value) { super(TypeName.BOOL); this.value = value; }

	@Override
	public void print(PrintStream w, PrintContext pc) {
		if (value) w.print("true");
		else w.print("false");
	}
}