package tree;


import java.util.ArrayList;

public class CallStmt extends Stmt {
	private Expr callee;
	private ArrayList<Expr> args;
	public CallStmt(Expr callee, ArrayList<Expr> args) {
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