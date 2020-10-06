package tree;

import util.LocationRange;

public class ReadStmt extends Stmt {
	private String target;
	public ReadStmt(LocationRange range, String target) {
		super(range);
		this.target = target;
	}
	public void print(NestedPrintStream w) {
		w.print("Readln(");
		w.print(target);
		w.println(");");
	}
}