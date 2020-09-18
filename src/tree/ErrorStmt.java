package tree;

public class ErrorStmt extends Stmt {
	public void print(NestedPrintStream w) {
		w.println("<<Error Statement>>");
	}
}