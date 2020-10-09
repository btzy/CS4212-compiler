package tree;


import util.LocationRange;
import ir3.Context;
import ir3.NullableExpr;
import ir3.SemanticException;
import java.util.function.Consumer;

public class UnaryOperation extends Expr {
	private UnOp op;
	private Expr expr;
	public UnaryOperation(LocationRange range, UnOp op, Expr expr) {
		super(range);
		this.op = op;
		this.expr = expr;
	}
	public void print(NestedPrintStream w) {
		w.print('(');
		op.print(w);
		w.print(')');
		w.print('[');
		expr.print(w);
		w.print(']');
	}

	/**
	 * Generates code for this expression.
	 */
	public NullableExpr typeCheckAndEmitIR3(Context ctx, Consumer<? super ir3.Instruction> out) throws SemanticException {
		NullableExpr e_nullable = expr.typeCheckAndEmitIR3(ctx, out);

		ir3.UnOp ir3_op = op.getIR3Op();
		
		ir3.Terminal e_term = e_nullable
			.imbueType(ir3_op.arg_type)
			.orElseThrow(() -> new ir3.TypeImbueUnaryOperatorException(e_nullable.getType(), expr.range, ir3_op, ir3_op.arg_type))
			.makeTerminalByMaybeEmitIR3(expr.range, ctx, out);

		return NullableExpr.of(new ir3.UnaryExpr(ir3_op.result_type, e_term, ir3_op));
	}
}