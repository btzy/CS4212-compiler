package tree;

import java.io.PrintStream;

public class PrintStmt extends Stmt {
	private Expr expr;
	public PrintStmt(Expr expr) {
		this.expr = expr;
	}
	public void print(PrintStream w) {
		w.print("Println(");
		w.print(expr);
		w.println(");");
	}
}