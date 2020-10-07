package ir3;

public class BooleanLiteral extends Literal {
	public final boolean value;

	public BooleanLiteral(boolean value) { super(TypeName.BOOL); this.value = value; }
}