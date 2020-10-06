package tree;
import util.LocationRange;

public class AssignStmt extends Stmt {
	private String target;
	private Expr expr;
	public AssignStmt(LocationRange range, String target, Expr expr) {
		super(range);
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