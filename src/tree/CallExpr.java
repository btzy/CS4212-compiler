package tree;


import java.util.ArrayList;
import util.LocationRange;

public class CallExpr extends Expr {
	private Expr callee;
	private ArrayList<Expr> args;
	public CallExpr(LocationRange range, Expr callee, ArrayList<Expr> args) {
		super(range);
		this.callee = callee;
		this.args = args;
	}
	public void print(NestedPrintStream w) {
		w.print('[');
		callee.print(w);
		w.print('(');
		Utils.commaSeparatedPrint(args, w);
		w.print(')');
		w.print(']');
	}
}