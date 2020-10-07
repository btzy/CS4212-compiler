package ir3;

/**
 * `new X()`.  The type is of course equal to super.type.
 */
public class AllocExpr extends Expr {
	public AllocExpr(TypeName type) { super(type); }
}