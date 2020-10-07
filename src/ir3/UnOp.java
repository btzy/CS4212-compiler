package ir3;



public enum UnOp {
	MINUS("-", TypeName.INT, TypeName.INT),
	NEGATION("!", TypeName.BOOL, TypeName.BOOL);

	private final String text;
	public final TypeName arg_type;
	public final TypeName result_type;

	UnOp(String text, TypeName arg_type, TypeName result_type) { this.text = text; this.arg_type = arg_type; this.result_type = result_type; }
}