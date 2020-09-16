package tree;

import java.io.PrintStream;
import java.util.ArrayList;

public class IfStmt extends Stmt {
	private Expr cond;
	private ArrayList<Stmt> true_stmts;
	private ArrayList<Stmt> false_stmts;
	public IfStmt(Expr cond, ArrayList<Stmt> true_stmts, ArrayList<Stmt> false_stmts) {
		this.cond = cond;
		this.true_stmts = true_stmts;
		this.false_stmts = false_stmts;
	}
	public void print(PrintStream w) {
		w.print("If(");
		cond.print(w);
		w.println(')');
		w.println('{');
		for (Stmt s : true_stmts) s.print(w);
		w.println('}');
		w.println("else");
		w.println('{');
		for (Stmt s : false_stmts) s.print(w);
		w.println('}');
	}
}