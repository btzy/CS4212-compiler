package tree;

import util.LocationRange;
import ir3.Context;
import ir3.SemanticException;
import java.util.function.Consumer;

public class PrintStmt extends Stmt {
	private Expr expr;
	public PrintStmt(LocationRange range, Expr expr) {
		super(range);
		this.expr = expr;
	}
	public void print(NestedPrintStream w) {
		w.print("Println(");
		expr.print(w);
		w.println(");");
	}
	
	/**
	 * Typecheck and emit IR3 code for this node.
	 */
	@Override
	public void typeCheckAndEmitIR3(Context ctx, Consumer<? super ir3.Instruction> out) throws SemanticException {
		ir3.NullableExpr result_nullable = expr.typeCheckAndEmitIR3(ctx, out);
		
		// will throw if the type can't be converted (the only time it might be converted is for nulls)
		ir3.Expr result = result_nullable
			.imbueTypes(new ir3.TypeName[]{ ir3.TypeName.INT, ir3.TypeName.BOOL, ir3.TypeName.STRING })
			.orElseThrow(() -> new ir3.PrintStatementException(result_nullable.getType(), expr.range));
		
		// since print statements require an idc3 instead of Exp3, we need to convert Exp3 to idc3
		ir3.Terminal terminal = result.makeTerminalByMaybeEmitIR3(expr.range, ctx, out);

		out.accept(new ir3.Println(terminal));
	}
}