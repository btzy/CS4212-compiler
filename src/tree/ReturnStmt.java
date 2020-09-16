package tree;

import java.io.PrintStream;

public class ReturnStmt extends Stmt {
	public void print(PrintStream w) {
		w.println("return;");
	}
}