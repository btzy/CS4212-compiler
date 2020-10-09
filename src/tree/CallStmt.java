package tree;


import ir3.Context;
import ir3.SemanticException;
import java.util.function.Consumer;
import java.util.ArrayList;
import java.util.stream.Collectors;
import util.LocationRange;

public class CallStmt extends Stmt {
	private Expr callee;
	private ArrayList<Expr> args;
	public CallStmt(LocationRange range, Expr callee, ArrayList<Expr> args) {
		super(range);
		this.callee = callee;
		this.args = args;
	}
	public void print(NestedPrintStream w) {
		callee.print(w);
		w.print('(');
		Utils.commaSeparatedPrint(args, w);
		w.println(");");
	}
	
	/**
	 * Typecheck and emit IR3 code for this node.
	 */
	public void typeCheckAndEmitIR3(Context ctx, Consumer<? super ir3.Instruction> out) throws SemanticException {
		ir3.ExprAndFuncIdxArray result_expr_funcidxs = callee.typeCheckAndEmitIR3ForMethod(ctx, out);

		// overload resolution
		ArrayList<ir3.NullableExpr> args_nullable = new ArrayList<>();
		for (Expr e : args) {
			args_nullable.add(e.typeCheckAndEmitIR3(ctx, out));
		}
		ArrayList<ir3.NullableTypeName> arg_types_nullable = args_nullable.stream().map(ne -> ne.getType()).collect(Collectors.toCollection(ArrayList::new));

		// throws exception when multiple matching overload or no match
		int funcidx = ir3.OverloadResolver.resolveOverload(arg_types_nullable, result_expr_funcidxs.idxs, ctx, range, result_expr_funcidxs.method_name, result_expr_funcidxs.class_name);

		ArrayList<ir3.TypeName> param_types = ctx.getFunc(funcidx).param_types;

		assert(param_types.size() == args_nullable.size() + 1);

		// type of 'this' must have been correct already
		final ArrayList<ir3.Terminal> args_terminals = new ArrayList<>();
		args_terminals.add(result_expr_funcidxs.expr.makeTerminalByMaybeEmitIR3(callee.range, ctx, out));
		for (int i=0; i!=args_nullable.size(); ++i) {
			// this error shouldn't actually happen because we already did overload resolution...
			final int ii = i;
			args_terminals.add(args_nullable
				.get(ii)
				.imbueType(param_types.get(ii+1))
				.orElseThrow(() -> new ir3.TypeImbueException(args_nullable.get(ii).getType(), args.get(ii).range, param_types.get(ii+1)))
				.makeTerminalByMaybeEmitIR3(args.get(ii).range, ctx, out));
		}

		out.accept(new ir3.Call(funcidx, args_terminals));
	}
}