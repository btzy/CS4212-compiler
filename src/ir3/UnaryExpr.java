package ir3;

/**
 * Represents a UnaryExpr with real type (cannot be nullptr_t).
 * In IR3, all expressions are typed.
 */
public class UnaryExpr extends Expr {
	public final Terminal arg;
	public final UnOp op;

	public UnaryExpr(TypeName type, Terminal arg, UnOp op) {
		super(type);
		this.arg = arg;
		this.op = op;
	}
}