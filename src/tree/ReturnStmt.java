package tree;

import util.LocationRange;
import ir3.Context;
import ir3.SemanticException;
import java.util.function.Consumer;

public class ReturnStmt extends Stmt {

	public ReturnStmt(LocationRange range) { super(range); }
	public void print(NestedPrintStream w) {
		w.println("Return;");
	}
	
	/**
	 * Typecheck and emit IR3 code for this node.
	 */
	public void typeCheckAndEmitIR3(Context ctx, Consumer<? super ir3.Instruction> out) throws SemanticException {
		// check that this function returns Void
		if (ctx.getReturnType() != ir3.TypeName.VOID) throw new ir3.ReturnTypeException(ir3.NullableTypeName.of(ir3.TypeName.VOID), range, ctx.getReturnType(), ctx.getReturnRange());

		out.accept(new ir3.Return());
	}
	
	@Override
	public boolean hasReturn() {
		return true;
	}
}