package tree;


public enum BinOp implements Printable {
	EQ("==", ir3.BinOp.EQ), NE("!=", ir3.BinOp.NE),
	LT("<", ir3.BinOp.LT), LE("<=", ir3.BinOp.LE),
	GT(">", ir3.BinOp.GT), GE(">=", ir3.BinOp.GE),
	PLUS("+", null), MINUS("-", ir3.BinOp.MINUS), TIMES("*", ir3.BinOp.TIMES), DIVIDE("/", ir3.BinOp.DIVIDE),
	CONJUNCTION("&&", ir3.BinOp.CONJUNCTION), DISJUNCTION("||", ir3.BinOp.DISJUNCTION);

	private final String text;
	private final ir3.BinOp ir3_op;

	BinOp(String text, ir3.BinOp ir3_op) { this.text = text; this.ir3_op = ir3_op; }

	public void print(NestedPrintStream w) {
		w.print(text);
	}

	public ir3.BinOp getIR3Op() {
		assert(this != PLUS);
		return ir3_op;
	}
}