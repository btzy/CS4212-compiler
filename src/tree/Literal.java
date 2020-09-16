package tree;

import java.util.ArrayList;

public class Literal extends Expr {
	private Object value; // could be Boolean, Integer, String, or null
	public Literal(Object value) {
		this.value = value;
	}
	public void print(NestedPrintStream w) {
		if (value instanceof Boolean) {
			if (((Boolean) value).booleanValue()) {
				w.print("true");
			}
			else {
				w.print("false");
			}
		}
		else if (value instanceof Number) {
			w.print(((Integer) value).intValue());
		}
		else if (value instanceof String) {
			w.print((String) value);
		}
		else if (value == null) {
			w.print("null");
		}
		else {
			assert(false);
		}
	}
}