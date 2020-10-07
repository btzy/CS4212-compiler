package tree;


import ir3.Context;
import ir3.NullableExpr;
import ir3.SemanticException;
import java.util.function.Consumer;
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
		else if (value instanceof Integer) {
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
	
	/**
	 * Typecheck and emit IR3 code for this node.
	 */
	public NullableExpr typeCheckAndEmitIR3(Context ctx, Consumer<? super ir3.Instruction> out) throws SemanticException {
		if (value instanceof Boolean) {
			return NullableExpr.of(new ir3.BooleanLiteral(((Boolean) value).booleanValue()));
		}
		else if (value instanceof Integer) {
			return NullableExpr.of(new ir3.IntegerLiteral(((Integer) value).intValue()));
		}
		else if (value instanceof String) {
			return NullableExpr.of(new ir3.StringLiteral((String) value));
		}
		else if (value == null) {
			return NullableExpr.nullValue();
		}
		else {
			assert(false);
			return null;
		}
	}
}