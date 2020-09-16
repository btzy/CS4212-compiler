package tree;

import java.io.PrintStream;

public enum BinOp implements Printable {
	EQ("=="), NE("!="), LT("<"), LE("<="), GT(">"), GE(">="),
	PLUS("+"), MINUS("-"), TIMES("*"), DIVIDE("/"),
	CONJUNCTION("&&"), DISJUNCTION("||");

	private final String text;

	BinOp(String text) { this.text = text; }

	public void print(PrintStream w) {
		w.print(text);
	}
}