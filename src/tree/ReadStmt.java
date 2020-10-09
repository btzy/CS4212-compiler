package tree;

import util.LocationRange;
import ir3.Context;
import ir3.SemanticException;
import java.util.function.Consumer;

public class ReadStmt extends Stmt {
	private String target;
	private LocationRange target_range;
	public ReadStmt(LocationRange range, LocationRange target_range, String target) {
		super(range);
		this.target_range = target_range;
		this.target = target;
	}
	public void print(NestedPrintStream w) {
		w.print("Readln(");
		w.print(target);
		w.println(");");
	}
	
	/**
	 * Typecheck and emit IR3 code for this node.
	 */
	public void typeCheckAndEmitIR3(Context ctx, Consumer<? super ir3.Instruction> out) throws SemanticException {
		ir3.Context.Entry entry = ctx.lookup(target).orElseThrow(() -> new ir3.NoSuchNameException(target, target_range));

		if (entry.type != ir3.TypeName.INT && entry.type != ir3.TypeName.BOOL && entry.type != ir3.TypeName.STRING) throw new ir3.ReadStatementException(entry.type, target_range, target, ctx.getEntryRange(entry));

		if (entry.isLocal) {
			// normal read statement
			out.accept(new ir3.Readln(entry.idx));
		}
		else {
			// implicit 'this'
			int localidx = ctx.newLocal(range, entry.type);
			out.accept(new ir3.Readln(localidx));
			out.accept(new ir3.AssignMember(ctx.getLocalEnvironment().thisIndex(), entry.idx, new ir3.LocalVariable(entry.type, localidx)));
		}
	}
}