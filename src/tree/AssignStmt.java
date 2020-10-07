package tree;
import util.LocationRange;
import ir3.Context;
import ir3.SemanticException;
import ir3.NoSuchNameException;
import java.util.function.Consumer;

public class AssignStmt extends Stmt {
	private String target;
	private LocationRange target_range;
	private Expr expr;
	public AssignStmt(LocationRange range, LocationRange target_range, String target, Expr expr) {
		super(range);
		this.target_range = target_range;
		this.target = target;
		this.expr = expr;
	}
	public void print(NestedPrintStream w) {
		w.print(target);
		w.print('=');
		expr.print(w);
		w.println(';');
	}
	
	/**
	 * Typecheck and emit IR3 code for this node.
	 */
	public void typeCheckAndEmitIR3(Context ctx, Consumer<? super ir3.Instruction> out) throws SemanticException {
		ir3.NullableExpr rhs_result_nullable = expr.typeCheckAndEmitIR3(ctx, out);
		ir3.Context.Entry entry = ctx.lookup(target).orElseThrow(() -> new NoSuchNameException(target, target_range));

		// will throw if the type can't be converted (the only time it might be converted is for nulls)
		// TODO: throw more specific exception
		ir3.Expr rhs_result = rhs_result_nullable.imbueType(entry.type).orElseThrow(() -> new SemanticException("Type error", range));
		
		if (entry.isLocal) {
			// normal assign statement
			out.accept(new ir3.Assign(entry.idx, rhs_result));
		}
		else {
			// implicit 'this'
			out.accept(new ir3.AssignMember(ctx.getLocalEnvironment().thisIndex(), entry.idx, rhs_result));
		}
	}
}