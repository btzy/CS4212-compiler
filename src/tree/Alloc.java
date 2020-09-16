package tree;

import java.io.PrintStream;

public class Alloc extends Expr {
	private String classname;
	public Alloc(String classname) {
		this.classname = classname;
	}
	public void print(PrintStream w) {
		w.print('[');
		w.print("New ");
		w.print(classname);
		w.print(']');
	}
}