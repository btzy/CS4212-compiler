package tree;

import util.LocationRange;
import ir3.Context;
import ir3.NullableExpr;
import ir3.SemanticException;
import java.util.function.Consumer;

public class ErrorExpr extends Expr {

	public ErrorExpr(LocationRange range) {
		super(range);
	}

	public void print(NestedPrintStream w) {
		w.print("<<Error Expression>>");
	}

	public NullableExpr typeCheckAndEmitIR3(Context ctx, Consumer<? super ir3.Instruction> out) throws SemanticException {
		assert(false); // this shouldn't happen
	}
}