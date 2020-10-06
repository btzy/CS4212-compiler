package tree;


import java.util.ArrayList;
import util.LocationRange;

public class CallStmt extends Stmt {
	private Expr callee;
	private ArrayList<Expr> args;
	public CallStmt(LocationRange range, Expr callee, ArrayList<Expr> args) {
		super(range);
		this.callee = callee;
		this.args = args;
	}
	public void print(NestedPrintStream w) {
		callee.print(w);
		w.print('(');
		Utils.commaSeparatedPrint(args, w);
		w.println(");");
	}
}