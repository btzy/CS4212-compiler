package tree;

import util.LocationRange;

public class PrintStmt extends Stmt {
	private Expr expr;
	public PrintStmt(LocationRange range, Expr expr) {
		super(range);
		this.expr = expr;
	}
	public void print(NestedPrintStream w) {
		w.print("Println(");
		expr.print(w);
		w.println(");");
	}
}