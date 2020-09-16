package tree;


public enum BinOp implements Printable {
	EQ("=="), NE("!="), LT("<"), LE("<="), GT(">"), GE(">="),
	PLUS("+"), MINUS("-"), TIMES("*"), DIVIDE("/"),
	CONJUNCTION("&&"), DISJUNCTION("||");

	private final String text;

	BinOp(String text) { this.text = text; }

	public void print(NestedPrintStream w) {
		w.print(text);
	}
}