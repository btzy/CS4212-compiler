package ir3;

import java.io.PrintStream;

/**
 * `new X()`.  The type is of course equal to super.type.
 */
public class AllocExpr extends Expr {
	public AllocExpr(TypeName type) { super(type); }

	@Override
	public void print(PrintStream w, PrintContext pc) {
		w.print("new ");
		w.print(super.type.name);
		w.print("()");
	}
}