package tree;



public enum UnOp implements Printable {
	MINUS("-", ir3.UnOp.MINUS),
	NEGATION("!", ir3.UnOp.NEGATION);

	private final String text;
	private final ir3.UnOp ir3_op;

	UnOp(String text, ir3.UnOp ir3_op) { this.text = text; this.ir3_op = ir3_op; }

	public void print(NestedPrintStream w) {
		w.print(text);
	}

	public ir3.UnOp getIR3Op() {
		return ir3_op;
	}
}