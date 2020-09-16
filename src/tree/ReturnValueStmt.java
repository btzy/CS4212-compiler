package tree;

import java.io.PrintStream;

public class ReturnValueStmt extends Stmt {
	private Expr expr;
	public ReturnValueStmt(Expr expr) {
		this.expr = expr;
	}
	public void print(PrintStream w) {
		w.println("return;");
	}
}