package tree;


public class MemberAccess extends Expr {
	private Expr obj;
	private String member;
	public MemberAccess(Expr obj, String member) {
		this.obj = obj;
		this.member = member;
	}
	public void print(NestedPrintStream w) {
		obj.print(w);
		w.print('.');
		w.print(member);
	}
}