package tree;

import util.LocationRange;

public class ErrorExpr extends Expr {

	public ErrorExpr(LocationRange range) {
		super(range);
	}

	public void print(NestedPrintStream w) {
		w.print("<<Error Expression>>");
	}
}