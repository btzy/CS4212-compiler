package tree;

import util.LocationRange;
import ir3.Context;
import ir3.SemanticException;
import java.util.function.Consumer;

public class ErrorStmt extends Stmt {

	public ErrorStmt(LocationRange range) {super(range); }
	public void print(NestedPrintStream w) {
		w.println("<<Error Statement>>");
	}
	
	
	public void typeCheckAndEmitIR3(Context ctx, Consumer<? super ir3.Instruction> out) throws SemanticException {
		assert(false); // we shouldn't get here
	}
}