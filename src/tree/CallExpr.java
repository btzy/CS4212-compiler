package tree;


import ir3.Context;
import ir3.NullableExpr;
import ir3.SemanticException;
import java.util.function.Consumer;
import java.util.ArrayList;
import java.util.stream.Collectors;
import util.LocationRange;

public class CallExpr extends Expr {
	private Expr callee;
	private ArrayList<Expr> args;
	public CallExpr(LocationRange range, Expr callee, ArrayList<Expr> args) {
		super(range);
		this.callee = callee;
		this.args = args;
	}
	public void print(NestedPrintStream w) {
		w.print('[');
		callee.print(w);
		w.print('(');
		Utils.commaSeparatedPrint(args, w);
		w.print(')');
		w.print(']');
	}
	
	/**
	 * Typecheck and emit IR3 code for this node.
	 */
	public NullableExpr typeCheckAndEmitIR3(Context ctx, Consumer<? super ir3.Instruction> out) throws SemanticException {
		ir3.ExprAndFuncIdxArray result_expr_funcidxs = callee.typeCheckAndEmitIR3ForMethod(ctx, out);

		// overload resolution
		ArrayList<ir3.NullableExpr> args_nullable = new ArrayList<>();
		for (Expr e : args) {
			args_nullable.add(e.typeCheckAndEmitIR3(ctx, out));
		}
		ArrayList<ir3.NullableTypeName> arg_types_nullable = args_nullable.stream().map(ne -> ne.getType()).collect(Collectors.toCollection(ArrayList::new));

		// throws exception when multiple matching overload or no match
		int funcidx = ir3.OverloadResolver.resolveOverload(arg_types_nullable, result_expr_funcidxs.idxs, ctx, range, result_expr_funcidxs.method_name, result_expr_funcidxs.class_name);

		ir3.FuncSpec spec = ctx.getFunc(funcidx);
		ArrayList<ir3.TypeName> param_types = spec.param_types;
		ir3.TypeName result_type = spec.result_type;

		assert(param_types.size() == args_nullable.size() + 1);

		// type of 'this' must have been correct already
		final ArrayList<ir3.Terminal> args_terminals = new ArrayList<>();
		args_terminals.add(result_expr_funcidxs.expr.makeTerminalByMaybeEmitIR3(callee.range, ctx, out));
		for (int i=0; i!=args_nullable.size(); ++i) {
			// TODO more specific error
			args_terminals.add(args_nullable.get(i).imbueType(param_types.get(i+1)).orElseThrow(() -> new SemanticException("Type error", range)).makeTerminalByMaybeEmitIR3(args.get(i).range, ctx, out));
		}

		return NullableExpr.of(new ir3.CallExpr(result_type, funcidx, args_terminals));
	}
}