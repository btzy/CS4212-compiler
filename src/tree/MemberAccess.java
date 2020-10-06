package tree;

import util.LocationRange;

public class MemberAccess extends Expr {
	private Expr obj;
	private String member;
	public MemberAccess(LocationRange range, Expr obj, String member) {
		super(range);
		this.obj = obj;
		this.member = member;
	}
	public void print(NestedPrintStream w) {
		obj.print(w);
		w.print('.');
		w.print(member);
	}
}