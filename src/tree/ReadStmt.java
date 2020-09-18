package tree;



public class ReadStmt extends Stmt {
	private String target;
	public ReadStmt(String target) {
		this.target = target;
	}
	public void print(NestedPrintStream w) {
		w.print("Readln(");
		w.print(target);
		w.println(");");
	}
}