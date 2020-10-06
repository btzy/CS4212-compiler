package tree;


import java.util.ArrayList;
import util.LocationRange;

public class Literal extends Expr {
	private Object value; // could be Boolean, Integer, String, or null
	public Literal(LocationRange range, Object value) {
		super(range);
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
			w.print('"');
			w.print((String) value);
			w.print('"');
		}
		else if (value == null) {
			w.print("null");
		}
		else {
			assert(false);
		}
	}
}