package tree;


public enum UnOp implements Printable {
	MINUS("-"),
	NEGATION("!");

	private final String text;

	UnOp(String text) { this.text = text; }

	public void print(NestedPrintStream w) {
		w.print(text);
	}
}