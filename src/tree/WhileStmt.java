package tree;


import java.util.ArrayList;
import util.LocationRange;
import ir3.Context;
import ir3.SemanticException;
import java.util.function.Consumer;

public class WhileStmt extends Stmt {
	private Expr cond;
	private ArrayList<Stmt> stmts;
	public WhileStmt(LocationRange range, Expr cond, ArrayList<Stmt> stmts) {
		super(range);
		this.cond = cond;
		this.stmts = stmts;
	}
	public void print(NestedPrintStream w) {
		w.print("While(");
		cond.print(w);
		w.println(')');
		w.println('{');
		w.enterContext();
		for (Stmt s : stmts) s.print(w);
		w.leaveContext();
		w.println('}');
	}
	
	/**
	 * Typecheck and emit IR3 code for this node.
	 */
	public void typeCheckAndEmitIR3(Context ctx, Consumer<? super ir3.Instruction> out) throws SemanticException {
		ir3.NullableExpr cond_nullable = cond.typeCheckAndEmitIR3(ctx, out);
		
		// will throw if the type can't be converted (the only time it might be converted is for nulls)
		// TODO: throw more specific exception
		// condition must be Bool
		ir3.Expr cond_res = cond_nullable.imbueType(ir3.TypeName.BOOL).orElseThrow(() -> new SemanticException("Type error", range));
		
		ir3.Label start_label = ctx.newLabel();
		ir3.Label in_label = ctx.newLabel();
		ir3.Label end_label = ctx.newLabel();

		// generate the IR3 while loop code
		out.accept(start_label);
		out.accept(new ir3.IfGoto(cond_res, in_label));
		out.accept(new ir3.Goto(end_label));
		out.accept(in_label);
		for (Stmt s : stmts) s.typeCheckAndEmitIR3(ctx, out);
		out.accept(new ir3.Goto(start_label));
		out.accept(end_label);
	}
}