package tree;

import java.io.PrintStream;
import java.util.ArrayList;

public class CallExpr extends Expr {
	private Expr callee;
	private ArrayList<Expr> args;
	public CallExpr(Expr callee, ArrayList<Expr> args) {
		this.callee = callee;
		this.args = args;
	}
	public void print(PrintStream w) {
		w.print('[');
		callee.print(w);
		w.print('(');
		Utils.commaSeparatedPrint(args, w);
		w.print(')');
		w.print(']');
	}
}