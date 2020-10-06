package tree;

import util.LocationRange;

public class ErrorStmt extends Stmt {

	public ErrorStmt(LocationRange range) {super(range); }
	public void print(NestedPrintStream w) {
		w.println("<<Error Statement>>");
	}
}