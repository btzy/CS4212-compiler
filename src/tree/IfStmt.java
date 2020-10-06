package tree;

import java.util.ArrayList;
import util.LocationRange;

public class IfStmt extends Stmt {
	private Expr cond;
	private ArrayList<Stmt> true_stmts;
	private ArrayList<Stmt> false_stmts;
	public IfStmt(LocationRange range, Expr cond, ArrayList<Stmt> true_stmts, ArrayList<Stmt> false_stmts) {
		super(range);
		this.cond = cond;
		this.true_stmts = true_stmts;
		this.false_stmts = false_stmts;
	}
	public void print(NestedPrintStream w) {
		w.print("If(");
		cond.print(w);
		w.println(')');
		w.println('{');
		w.enterContext();
		for (Stmt s : true_stmts) s.print(w);
		w.leaveContext();
		w.println('}');
		w.println("else");
		w.println('{');
		w.enterContext();
		for (Stmt s : false_stmts) s.print(w);
		w.leaveContext();
		w.println('}');
	}
}