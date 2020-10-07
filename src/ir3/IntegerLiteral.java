package ir3;

public class IntegerLiteral extends Literal {
	public final int value;

	public IntegerLiteral(int value) { super(TypeName.INT); this.value = value; }
}