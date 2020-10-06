package tree;

import util.LocationRange;

public class VarAccess extends Expr {
	private String target;
	public VarAccess(LocationRange range, String target) {
		super(range);
		this.target = target;
	}
	public void print(NestedPrintStream w) {
		w.print(target);
	}
}