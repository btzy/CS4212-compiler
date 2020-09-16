package tree;


public class VarAccess extends Expr {
	private String target;
	public VarAccess(String target) {
		this.target = target;
	}
	public void print(NestedPrintStream w) {
		w.print(target);
	}
}