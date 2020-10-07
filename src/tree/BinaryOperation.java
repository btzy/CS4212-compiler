package tree;


import util.LocationRange;
import ir3.Context;
import ir3.NullableExpr;
import ir3.SemanticException;
import java.util.function.Consumer;

public class BinaryOperation extends Expr {
	private BinOp op;
	private Expr left, right;
	public BinaryOperation(LocationRange range, BinOp op, Expr left, Expr right) {
		super(range);
		this.op = op;
		this.left = left;
		this.right = right;
	}
	public void print(NestedPrintStream w) {
		w.print('[');
		left.print(w);
		w.print(',');
		right.print(w);
		w.print(']');
		w.print('(');
		op.print(w);
		w.print(')');
	}

	/**
	 * Generates code for this expression.
	 */
	public NullableExpr typeCheckAndEmitIR3(Context ctx, Consumer<? super ir3.Instruction> out) throws SemanticException {
		NullableExpr left_nullable = left.typeCheckAndEmitIR3(ctx, out);
		NullableExpr right_nullable = right.typeCheckAndEmitIR3(ctx, out);

		ir3.BinOp ir3_op;
		if (op == BinOp.PLUS) {
			ir3_op = resolvePlusOp(left_nullable, right_nullable);
		}
		else {
			ir3_op = op.getIR3Op();
		}

		// TODO more specific error
		ir3.Terminal left_term = left_nullable.imbueType(ir3_op.arg_type).orElseThrow(() -> new SemanticException("Type error", range)).makeTerminalByMaybeEmitIR3(left.range, ctx, out);
		// TODO more specific error
		ir3.Terminal right_term = right_nullable.imbueType(ir3_op.arg_type).orElseThrow(() -> new SemanticException("Type error", range)).makeTerminalByMaybeEmitIR3(right.range, ctx, out);

		return NullableExpr.of(new ir3.BinaryExpr(ir3_op.result_type, left_term, right_term, ir3_op));
	}

	private ir3.BinOp resolvePlusOp(NullableExpr left_nullable, NullableExpr right_nullable) throws SemanticException {
		ir3.TypeName left_type = left_nullable.getType().asOptional().orElse(ir3.TypeName.STRING); // nulls turn into String here
		ir3.TypeName right_type = right_nullable.getType().asOptional().orElse(ir3.TypeName.STRING); // nulls turn into String here
		if (left_type == ir3.TypeName.STRING && right_type == ir3.TypeName.STRING) {
			return ir3.BinOp.CONCAT;
		}
		if (left_type == ir3.TypeName.INT && right_type == ir3.TypeName.INT) {
			return ir3.BinOp.PLUS;
		}
		// TODO: more specific exception
		throw new SemanticException("Plus operator type error", range);
	}
}