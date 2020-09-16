package tree;


public class MemberAssignStmt extends Stmt {
	private Expr obj;
	private String member;
	private Expr expr;
	public MemberAssignStmt(Expr obj, String member, Expr expr) {
		this.obj = obj;
		this.member = member;
		this.expr = expr;
	}
	public void print(NestedPrintStream w) {
		obj.print(w);
		w.print('.');
		w.print(member);
		w.print('=');
		expr.print(w);
		w.println(';');
	}
}