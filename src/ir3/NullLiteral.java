package ir3;

import java.io.PrintStream;

public class NullLiteral extends Literal {
	public NullLiteral(TypeName type) { super(type); }

	@Override
	public void print(PrintStream w, PrintContext pc) {
		w.print("null"); // null object
	}

	@Override
	public int emitAsm(PrintStream w, int hint_output_reg, EmitFunc ef, EmitContext ctx, boolean optimize) {
		AsmEmitter.emitMovImm(w, hint_output_reg, 0); // I guess NULL is represented as 0 on ARM... at least that's what GCC does
		return hint_output_reg;
	}
	
	@Override
	public RegOrImm emitAsmImm(PrintStream w, int hint_output_reg, EmitFunc ef, EmitContext ctx, boolean optimize) {
		return RegOrImm.makeImm(0);
	}
}