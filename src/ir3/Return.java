package ir3;

import java.util.Optional;
import java.io.PrintStream;

public class Return extends Instruction {
	public final Optional<LocalVariable> val; // might return nothing
	public Return() { this.val = Optional.empty(); }
	public Return(LocalVariable val) { this.val = Optional.of(val); }
	public Return(Optional<LocalVariable> val) { this.val = val; }

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
}