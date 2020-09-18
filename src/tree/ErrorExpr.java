package tree;

public class ErrorExpr extends Expr {
	public void print(NestedPrintStream w) {
		w.println("<<Error Expression>>");
	}
}