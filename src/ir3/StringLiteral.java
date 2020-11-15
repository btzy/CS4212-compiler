package ir3;

import java.io.PrintStream;

public class StringLiteral extends Literal {
	public final String value;

	public StringLiteral(String value) { super(TypeName.STRING); this.value = value; }

	@Override
	public void print(PrintStream w, PrintContext pc) {
		if (value.isEmpty()) w.print("null"); // null string
		else {
			w.print('\"');
			w.print(value);
			w.print('\"');
		}
	}

	@Override
	public int emitAsm(PrintStream w, int hint_output_reg, EmitFunc ef, EmitContext ctx, boolean optimize) {
		String name = ctx.addStringLiteral(value);
		AsmEmitter.emitLdrLitAddr(w, hint_output_reg, name);
		return hint_output_reg;
	}
}