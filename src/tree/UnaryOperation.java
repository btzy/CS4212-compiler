package tree;

import util.LocationRange;

public class UnaryOperation extends Expr {
	private UnOp op;
	private Expr expr;
	public UnaryOperation(LocationRange range, UnOp op, Expr expr) {
		super(range);
		this.op = op;
		this.expr = expr;
	}
	public void print(NestedPrintStream w) {
		w.print('(');
		op.print(w);
		w.print(')');
		w.print('[');
		expr.print(w);
		w.print(']');
	}
}