package ir3;

import java.io.PrintStream;

public class BooleanLiteral extends Literal {
	public final boolean value;

	public BooleanLiteral(boolean value) { super(TypeName.BOOL); this.value = value; }

	@Override
	public void print(PrintStream w, PrintContext pc) {
		if (value) w.print("true");
		else w.print("false");
	}

	@Override
	public int emitAsm(PrintStream w, int hint_output_reg, EmitFunc ef, EmitContext ctx, boolean optimize) {
		AsmEmitter.emitMovImm(w, hint_output_reg, value ? 1 : 0);
		return hint_output_reg;
	}
}