package ir3;

import java.io.PrintStream;
import java.util.OptionalInt;
import java.util.ArrayList;

public class AssignMember extends Instruction {
	public final int idx; // localidx
	public final int field; // fieldidx
	public final Expr val;
	public AssignMember(int idx, int field, Expr val) { this.idx = idx; this.field = field; this.val = val; }

	@Override
	public void print(PrintStream w, PrintContext pc) {
		w.print("  ");
		w.print(pc.env.getName(idx));
		w.print('.');
		w.print(pc.getClassDescriptor(pc.env.getType(idx)).getFieldName(field));
		w.print(" = ");
		val.print(w, pc);
		w.println(';');
	}

	@Override
	public void emitAsm(PrintStream w, EmitFunc ef, EmitContext ctx, boolean optimize) {
		final EmitFunc.StorageLocation sl = ef.storage_locations.get(idx);
		final int output_reg = val.emitAsm(w, EmitFunc.Registers.FP, ef, ctx, optimize);
		final int ptr_reg = AsmEmitter.emitPseudoLoadVariable(w, output_reg == EmitFunc.Registers.FP ? EmitFunc.Registers.LR : EmitFunc.Registers.FP, sl, ef.env.getType(idx));
		final EmitClass ec = ctx.getEmitClass(ef.env.getType(idx));
		AsmEmitter.emitPseudoStr(w, ptr_reg, ec.field_offsets.get(field), output_reg, ec.field_types.get(field));
	}

	@Override
	public OptionalInt getDef() { return OptionalInt.empty(); }

	@Override
	public ArrayList<Integer> getUses() {
		final ArrayList<Integer> ret = val.getUses();
		ret.add(idx);
		return ret;
	}
	
	@Override
	public ArrayList<Integer> getClobberedRegs() {
		final ArrayList<Integer> ret = new ArrayList<>(val.getClobberedRegs());
		ret.add(EmitFunc.Registers.FP);
		ret.add(EmitFunc.Registers.LR);
		return ret;
	}

	@Override
	public ArrayList<VarRegPair> getRegPreferences() {
		final ArrayList<VarRegPair> ret = val.getRegPreferences();
		val.getOutputRegPreference().ifPresent(reg -> {
			ret.add(new VarRegPair(idx, reg));
		});
		return ret;
	}

}