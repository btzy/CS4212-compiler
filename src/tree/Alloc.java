package tree;

import util.LocationRange;

public class Alloc extends Expr {
	private String classname;
	public Alloc(LocationRange range, String classname) {
		super(range);
		this.classname = classname;
	}
	public void print(NestedPrintStream w) {
		w.print('[');
		w.print("New ");
		w.print(classname);
		w.print("()]");
	}
}