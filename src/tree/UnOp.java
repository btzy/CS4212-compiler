package tree;

import java.io.PrintStream;

public enum UnOp implements Printable {
	MINUS("-"),
	NEGATION("!");

	private final String text;

	UnOp(String text) { this.text = text; }

	public void print(PrintStream w) {
		w.print(text);
	}
}