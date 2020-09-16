package tree;


public class PrintStmt extends Stmt {
	private Expr expr;
	public PrintStmt(Expr expr) {
		this.expr = expr;
	}
	public void print(NestedPrintStream w) {
		w.print("Println(");
		w.print(expr);
		w.println(");");
	}
}