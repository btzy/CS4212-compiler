package ir3;

import java.io.PrintStream;

public class IntegerLiteral extends Literal {
	public final int value;

	public IntegerLiteral(int value) { super(TypeName.INT); this.value = value; }

	@Override
	public void print(PrintStream w, PrintContext pc) {
		w.print(value);
	}

	@Override
	public int emitAsm(PrintStream w, int hint_output_reg, EmitFunc ef, EmitContext ctx, boolean optimize) {
		AsmEmitter.emitMovImm(w, hint_output_reg, value);
		return hint_output_reg;
	}
	
	@Override
	public RegOrImm emitAsmImm(PrintStream w, int hint_output_reg, EmitFunc ef, EmitContext ctx, boolean optimize) {
		return RegOrImm.makeImm(value);
	}
}