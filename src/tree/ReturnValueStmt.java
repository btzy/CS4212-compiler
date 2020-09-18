package tree;



public class ReturnValueStmt extends Stmt {
	private Expr expr;
	public ReturnValueStmt(Expr expr) {
		this.expr = expr;
	}
	public void print(NestedPrintStream w) {
		w.print("Return ");
		expr.print(w);
		w.println(';');
	}
}