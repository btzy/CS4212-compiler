package ir3;

import util.LocationRange;
import java.util.function.Consumer;
import java.io.PrintStream;
import java.util.OptionalInt;
import java.util.ArrayList;

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

	@Override
	public int emitAsm(PrintStream w, int hint_output_reg, EmitFunc ef, EmitContext ctx, boolean optimize) {
		final EmitFunc.StorageLocation sl = ef.storage_locations.get(idx);
		return AsmEmitter.emitPseudoLoadVariable(w, hint_output_reg, sl, ef.env.getType(idx));
	}

	@Override
	public ArrayList<Integer> getUses() {
		final ArrayList<Integer> ret = new ArrayList<>();
		ret.add(idx);
		return ret;
	}
	
	@Override
	public ArrayList<Integer> getClobberedRegs() {
		return new ArrayList<>();
	}
}