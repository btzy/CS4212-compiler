package tree;


import java.util.ArrayList;
import util.LocationRange;
import ir3.Context;
import ir3.SemanticException;
import java.util.function.Consumer;

public class IfStmt extends Stmt {
	private Expr cond;
	private ArrayList<Stmt> true_stmts;
	private ArrayList<Stmt> false_stmts;
	public IfStmt(LocationRange range, Expr cond, ArrayList<Stmt> true_stmts, ArrayList<Stmt> false_stmts) {
		super(range);
		this.cond = cond;
		this.true_stmts = true_stmts;
		this.false_stmts = false_stmts;
	}
	public void print(NestedPrintStream w) {
		w.print("If(");
		cond.print(w);
		w.println(')');
		w.println('{');
		w.enterContext();
		for (Stmt s : true_stmts) s.print(w);
		w.leaveContext();
		w.println('}');
		w.println("else");
		w.println('{');
		w.enterContext();
		for (Stmt s : false_stmts) s.print(w);
		w.leaveContext();
		w.println('}');
	}
	
	/**
	 * Typecheck and emit IR3 code for this node.
	 */
	public void typeCheckAndEmitIR3(Context ctx, Consumer<? super ir3.Instruction> out) {
		// will throw if the type can't be converted (the only time it might be converted is for nulls)
		// condition must be Bool
		ir3.Expr cond_res = SemanticException.bound(() -> {
			ir3.NullableExpr cond_nullable = cond.typeCheckAndEmitIR3(ctx, out);
			return cond_nullable
				.imbueType(ir3.TypeName.BOOL)
				.orElseThrow(() -> new ir3.TypeImbueIfStatementConditionException(cond_nullable.getType(), cond.range))
				.makeRelExp3ByMaybeEmitIR3(cond.range, ctx, out);
		}, new ir3.BooleanLiteral(true));
		
		ir3.Label true_label = ctx.newLabel();
		ir3.Label end_label = ctx.newLabel();

		// generate the IR3 branching code
		out.accept(new ir3.IfGoto(cond_res, true_label));
		for (Stmt s : false_stmts) SemanticException.bound(() -> { s.typeCheckAndEmitIR3(ctx, out); });
		out.accept(new ir3.Goto(end_label));
		out.accept(true_label);
		for (Stmt s : true_stmts) SemanticException.bound(() -> { s.typeCheckAndEmitIR3(ctx, out); });
		out.accept(end_label);
	}
}