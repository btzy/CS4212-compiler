package tree;

import util.LocationRange;
import ir3.Context;
import ir3.SemanticException;
import java.util.function.Consumer;

public class ReturnValueStmt extends Stmt {
	private Expr expr;
	public ReturnValueStmt(LocationRange range, Expr expr) {
		super(range);
		this.expr = expr;
	}
	public void print(NestedPrintStream w) {
		w.print("Return ");
		expr.print(w);
		w.println(';');
	}
	
	/**
	 * Typecheck and emit IR3 code for this node.
	 */
	public void typeCheckAndEmitIR3(Context ctx, Consumer<? super ir3.Instruction> out) throws SemanticException {
		ir3.NullableExpr result_nullable = expr.typeCheckAndEmitIR3(ctx, out);

		// will throw if the type can't be converted (the only time it might be converted is for nulls)
		ir3.Expr result = result_nullable.imbueType(ctx.getReturnType()).orElseThrow(() -> new ir3.ReturnTypeException(result_nullable.getType(), range, ctx.getReturnType(), ctx.getReturnRange()));
	
		// since return statements require an id3 instead of Exp3, we need to convert Exp3 to id3
		ir3.Terminal terminal = result.makeTerminalByMaybeEmitIR3(range, ctx, out);
		
		out.accept(new ir3.Return(terminal));
	}
}