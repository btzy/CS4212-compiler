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

	@Override
	public int emitAsm(PrintStream w, int hint_output_reg, EmitFunc ef, EmitContext ctx, boolean optimize) {
		final int sz = ctx.getEmitClass(type).size;
		AsmEmitter.emitMovImm(w, EmitFunc.Registers.A1, sz);
		AsmEmitter.emitBlPlt(w, "_Znwj");
		return EmitFunc.Registers.A1;
	}
}