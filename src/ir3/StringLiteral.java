package ir3;

public class StringLiteral extends Literal {
	public final String value;

	public StringLiteral(String value) { super(TypeName.STRING); this.value = value; }
}