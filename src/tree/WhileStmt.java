package tree;

import java.util.ArrayList;
import util.LocationRange;

public class WhileStmt extends Stmt {
	private Expr cond;
	private ArrayList<Stmt> stmts;
	public WhileStmt(LocationRange range, Expr cond, ArrayList<Stmt> stmts) {
		super(range);
		this.cond = cond;
		this.stmts = stmts;
	}
	public void print(NestedPrintStream w) {
		w.print("While(");
		cond.print(w);
		w.println(')');
		w.println('{');
		w.enterContext();
		for (Stmt s : stmts) s.print(w);
		w.leaveContext();
		w.println('}');
	}
}