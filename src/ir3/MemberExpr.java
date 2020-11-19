package ir3;

import java.io.PrintStream;
import java.util.OptionalInt;
import java.util.ArrayList;

public class MemberExpr extends Expr {
	public final int idx; // localidx
	public final int field; // fieldidx
	public MemberExpr(TypeName type, int idx, int field) { super(type); this.idx = idx; this.field = field; }

	@Override
	public void print(PrintStream w, PrintContext pc) {
		w.print(pc.env.getName(idx));
		w.print('.');
		w.print(pc.getClassDescriptor(pc.env.getType(idx)).getFieldName(field));
	}

	@Override
	public int emitAsm(PrintStream w, int hint_output_reg, EmitFunc ef, EmitContext ctx, boolean optimize) {
		final EmitFunc.StorageLocation sl = ef.storage_locations.get(idx);
		final int ptr_reg = AsmEmitter.emitPseudoLoadVariable(w, hint_output_reg, sl, ef.env.getType(idx));
		final EmitClass ec = ctx.getEmitClass(ef.env.getType(idx));
		AsmEmitter.emitPseudoLdr(w, hint_output_reg, ptr_reg, ec.field_offsets.get(field), ec.field_types.get(field));
		return hint_output_reg;
	}

	@Override
	public ArrayList<Integer> getUses() {
		final ArrayList<Integer> ret = new ArrayList<Integer>();
		ret.add(idx);
		return ret;
	}
	
	@Override
	public ArrayList<Integer> getClobberedRegs() {
		return new ArrayList<>();
	}
}