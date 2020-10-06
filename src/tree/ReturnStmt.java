package tree;

import util.LocationRange;

public class ReturnStmt extends Stmt {

	public ReturnStmt(LocationRange range) { super(range); }
	public void print(NestedPrintStream w) {
		w.println("Return;");
	}
}