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
		out.accept(new ir3.Return());
	}
}