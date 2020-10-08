package ir3;

import java.io.PrintStream;

/**
 * Represents a BinaryExpr with real type (cannot be nullptr_t).
 * In IR3, all expressions are typed.
 */
public class BinaryExpr extends Expr {
	public final Terminal left;
	public final Terminal right;
	public final BinOp op;

	public BinaryExpr(TypeName type, Terminal left, Terminal right, BinOp op) {
		super(type);
		this.left = left;
		this.right = right;
		this.op = op;
	}

	@Override
	public void print(PrintStream w, PrintContext pc) {
		left.print(w, pc);
		w.print(' ');
		op.print(w);
		w.print(' ');
		right.print(w, pc);
	}
}