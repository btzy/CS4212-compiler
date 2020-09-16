package tree;


public class ReturnStmt extends Stmt {
	public void print(NestedPrintStream w) {
		w.println("return;");
	}
}