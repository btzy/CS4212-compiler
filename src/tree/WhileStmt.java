package tree;

import java.io.PrintStream;
import java.util.ArrayList;

public class WhileStmt extends Stmt {
	private Expr cond;
	private ArrayList<Stmt> stmts;
	public WhileStmt(Expr cond, ArrayList<Stmt> stmts) {
		this.cond = cond;
		this.stmts = stmts;
	}
	public void print(PrintStream w) {
		w.print("While(");
		cond.print(w);
		w.println(')');
		w.println('{');
		for (Stmt s : stmts) s.print(w);
		w.println('}');
	}
}