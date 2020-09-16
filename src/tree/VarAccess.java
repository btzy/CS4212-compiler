package tree;

import java.io.PrintStream;

public class VarAccess extends Expr {
	private String target;
	public VarAccess(String target) {
		this.target = target;
	}
	public void print(PrintStream w) {
		w.print(target);
	}
}