package ir3;



public enum BinOp {
	EQ("==", TypeName.INT, TypeName.BOOL), NE("!=", TypeName.INT, TypeName.BOOL), LT("<", TypeName.INT, TypeName.BOOL), LE("<=", TypeName.INT, TypeName.BOOL), GT(">", TypeName.INT, TypeName.BOOL), GE(">=", TypeName.INT, TypeName.BOOL),
	PLUS("+", TypeName.INT, TypeName.INT), MINUS("-", TypeName.INT, TypeName.INT), TIMES("*", TypeName.INT, TypeName.INT), DIVIDE("/", TypeName.INT, TypeName.INT),
	CONJUNCTION("&&", TypeName.BOOL, TypeName.BOOL), DISJUNCTION("||", TypeName.BOOL, TypeName.BOOL),
	CONCAT("+", TypeName.STRING, TypeName.STRING);

	private final String text;
	public final TypeName arg_type;
	public final TypeName result_type;

	BinOp(String text, TypeName arg_type, TypeName result_type) { this.text = text; this.arg_type = arg_type; this.result_type = result_type; }
}