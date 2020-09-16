package tree;

import java.io.PrintStream;

public class ReadStmt extends Stmt {
	private String target;
	public ReadStmt(String target) {
		this.target = target;
	}
	public void print(PrintStream w) {
		w.print("Readln(");
		w.print(target);
		w.println(");");
	}
}