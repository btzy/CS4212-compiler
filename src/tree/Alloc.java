package tree;

import util.LocationRange;
import ir3.Context;
import ir3.NullableExpr;
import ir3.SemanticException;
import java.util.function.Consumer;

public class Alloc extends Expr {
	private final String classname;
	private final LocationRange type_range;
	public Alloc(LocationRange range, LocationRange type_range, String classname) {
		super(range);
		this.type_range = type_range;
		this.classname = classname;
	}
	public void print(NestedPrintStream w) {
		w.print('[');
		w.print("New ");
		w.print(classname);
		w.print("()]");
	}

	/**
	 * Generates code for this expression.
	 */
	public NullableExpr typeCheckAndEmitIR3(Context ctx, Consumer<? super ir3.Instruction> out) throws SemanticException {
		ir3.TypeName type = ir3.TypeName.getType(classname);
		if (type == null) throw new ir3.NoSuchTypeException(classname, type_range);
	
		return NullableExpr.of(new ir3.AllocExpr(type));
	}
}