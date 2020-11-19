package ir3;

import java.util.Optional;
import java.io.PrintStream;
import java.util.OptionalInt;
import java.util.ArrayList;

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

	@Override
	public OptionalInt getDef() { return OptionalInt.empty(); }

	@Override
	public ArrayList<Integer> getUses() {
		final ArrayList<Integer> ret = new ArrayList<>();
		val.ifPresent(term -> {
			ret.addAll(term.getUses());
		});
		return ret;
	}

	@Override
	public ArrayList<Integer> getClobberedRegs() {
		if (val.isPresent()) return val.get().getClobberedRegs();
		else return new ArrayList<>();
	}
}