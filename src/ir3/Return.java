package ir3;

import java.util.Optional;
import java.io.PrintStream;

public class Return extends Instruction {
	public final Optional<Terminal> val; // might return nothing
	public Return() { this.val = Optional.empty(); }
	public Return(Terminal val) { this.val = Optional.of(val); }
	public Return(Optional<Terminal> val) { this.val = val; }

	@Override
	public void print(PrintStream w, PrintContext pc) {
		if (val.isEmpty()) {
			w.println("  return;");
		}
		else {
			w.print("  return ");
			val.get().print(w, pc);
			w.println(';');
		}
	}

	@Override
	public void emitAsm(PrintStream w, EmitFunc ef, EmitContext ctx, boolean optimize) {
		val.ifPresent(terminal -> {
			final int reg = terminal.emitAsm(w, EmitFunc.Registers.A1, ef, ctx, optimize);
			if (reg != EmitFunc.Registers.A1) AsmEmitter.emitMovReg(w, EmitFunc.Registers.A1, reg);
		});
		AsmEmitter.emitEpilogue(w, ef);
	}
}