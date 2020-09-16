package tree;

import java.io.PrintStream;

public class BinaryOperation extends Expr {
	private BinOp op;
	private Expr left, right;
	public BinaryOperation(BinOp op, Expr left, Expr right) {
		this.op = op;
		this.left = left;
		this.right = right;
	}
	public void print(PrintStream w) {
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