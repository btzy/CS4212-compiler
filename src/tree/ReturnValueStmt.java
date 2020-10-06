package tree;

import util.LocationRange;

public class ReturnValueStmt extends Stmt {
	private Expr expr;
	public ReturnValueStmt(LocationRange range, Expr expr) {
		super(range);
		this.expr = expr;
	}
	public void print(NestedPrintStream w) {
		w.print("Return ");
		expr.print(w);
		w.println(';');
	}
}