package tree;


public class AssignStmt extends Stmt {
	private String target;
	private Expr expr;
	public AssignStmt(String target, Expr expr) {
		this.target = target;
		this.expr = expr;
	}
	public void print(NestedPrintStream w) {
		w.print(target);
		w.print('=');
		expr.print(w);
		w.println(';');
	}
}