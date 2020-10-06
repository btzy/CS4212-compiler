package tree;


import util.LocationRange;

public class BinaryOperation extends Expr {
	private BinOp op;
	private Expr left, right;
	public BinaryOperation(LocationRange range, BinOp op, Expr left, Expr right) {
		super(range);
		this.op = op;
		this.left = left;
		this.right = right;
	}
	public void print(NestedPrintStream w) {
		w.print('[');
		left.print(w);
		w.print(',');
		right.print(w);
		w.print(']');
		w.print('(');
		op.print(w);
		w.print(')');
	}
}