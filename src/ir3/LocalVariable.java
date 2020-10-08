package ir3;

import util.LocationRange;
import java.util.function.Consumer;
import java.io.PrintStream;

public class LocalVariable extends Terminal {
	public final int idx;
	public LocalVariable(TypeName type, int idx) { super(type); this.idx = idx; }

	@Override
	public LocalVariable makeLocalVariableByMaybeEmitIR3(LocationRange virtual_range, Context ctx, Consumer<? super ir3.Instruction> out) throws SemanticException {
		return this;
	}

	@Override
	public void print(PrintStream w, PrintContext pc) {
		w.print(pc.env.getName(idx));
	}
}