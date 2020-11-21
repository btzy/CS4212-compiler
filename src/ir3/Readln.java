package ir3;

import java.io.PrintStream;
import java.util.OptionalInt;
import java.util.ArrayList;

public class Readln extends Instruction {
	public final int idx;
	public Readln(int idx) { this.idx = idx; }

	@Override
	public void print(PrintStream w, PrintContext pc) {
		w.print("  readln(");
		w.print(pc.env.getName(idx));
		w.println(");");
	}

	@Override
	public void emitAsm(PrintStream w, EmitFunc ef, EmitContext ctx, boolean optimize) {
		final TypeName type = ef.env.getType(idx);
		final EmitFunc.StorageLocation sl = ef.storage_locations.get(idx);

		final String stdin_handle = ctx.addExternWord("stdin");
		final String empty_string_lit = ctx.addCStringLiteral("");
		final Object label_namespace = new Object();

		AsmEmitter.emitMovImm(w, EmitFunc.Registers.A1, 0);
		AsmEmitter.emitStrPreOffset(w, EmitFunc.Registers.SP, -4, EmitFunc.Registers.A1);
		AsmEmitter.emitMovReg(w, EmitFunc.Registers.A1, EmitFunc.Registers.SP);
		AsmEmitter.emitMovImm(w, EmitFunc.Registers.A2, 0);
		AsmEmitter.emitStrPreOffset(w, EmitFunc.Registers.SP, -4, EmitFunc.Registers.A2);
		AsmEmitter.emitMovReg(w, EmitFunc.Registers.A2, EmitFunc.Registers.SP);
		AsmEmitter.emitLdrLitAddr(w, EmitFunc.Registers.A3, stdin_handle);
		AsmEmitter.emitLdr(w, EmitFunc.Registers.A3, EmitFunc.Registers.A3, 0);
		AsmEmitter.emitBlPlt(w, "getline");
		AsmEmitter.emitAddImm(w, EmitFunc.Registers.SP, EmitFunc.Registers.SP, 8);
		int output_reg = sl.isRegister ? sl.value : EmitFunc.Registers.A1;
		int scratch2_reg = output_reg == EmitFunc.Registers.A2 ? EmitFunc.Registers.A1 : EmitFunc.Registers.A2;
		int scratch3_reg = output_reg == EmitFunc.Registers.A3 ? EmitFunc.Registers.A1 : EmitFunc.Registers.A3;
		int scratch4_reg = output_reg == EmitFunc.Registers.A4 ? EmitFunc.Registers.A1 : EmitFunc.Registers.A4;

		if (type == TypeName.INT) {
			AsmEmitter.emitCmnImm(w, EmitFunc.Registers.A1, 1);
			AsmEmitter.emitMovCondImm(w, AsmEmitter.Cond.EQ, output_reg, 0);
			AsmEmitter.emitPseudoStoreVariableCond(w, AsmEmitter.Cond.EQ, sl, output_reg, TypeName.INT);
			AsmEmitter.emitLdrCond(w, AsmEmitter.Cond.NE, EmitFunc.Registers.A1, EmitFunc.Registers.SP, -4);
			AsmEmitter.emitBlCondPlt(w, AsmEmitter.Cond.NE, "atoi");
			AsmEmitter.emitPseudoStoreVariableCond(w, AsmEmitter.Cond.NE, sl, EmitFunc.Registers.A1, TypeName.INT);
		}
		else if (type == TypeName.BOOL) {
			final String fail_label = ctx.addLabel(label_namespace, 1);
			AsmEmitter.emitCmnImm(w, EmitFunc.Registers.A1, 1);
			AsmEmitter.emitMovCondImm(w, AsmEmitter.Cond.EQ, output_reg, 0);
			AsmEmitter.emitBCond(w, AsmEmitter.Cond.EQ, fail_label);
			AsmEmitter.emitLdr(w, output_reg, EmitFunc.Registers.SP, -4);
			AsmEmitter.emitLdrb(w, output_reg, output_reg, 0);
			AsmEmitter.emitCmpImm(w, output_reg, (int)'t');
			AsmEmitter.emitMovCondImm(w, AsmEmitter.Cond.EQ, output_reg, 1);
			AsmEmitter.emitBCond(w, AsmEmitter.Cond.EQ, fail_label);
			AsmEmitter.emitCmpImm(w, output_reg, (int)'1');
			AsmEmitter.emitMovCondImm(w, AsmEmitter.Cond.EQ, output_reg, 1);
			AsmEmitter.emitMovCondImm(w, AsmEmitter.Cond.NE, output_reg, 0);
			w.print(fail_label);
			w.println(':');
			AsmEmitter.emitPseudoStoreVariable(w, sl, output_reg, TypeName.BOOL);
		}
		else if (type == TypeName.STRING) {
			final String fail_label = ctx.addLabel(label_namespace, 1);
			AsmEmitter.emitCmnImm(w, EmitFunc.Registers.A1, 1);
			AsmEmitter.emitLdrCondLitAddr(w, AsmEmitter.Cond.EQ, output_reg, empty_string_lit);
			AsmEmitter.emitBCond(w, AsmEmitter.Cond.EQ, fail_label);
			AsmEmitter.emitSubFlagsImm(w, scratch3_reg, EmitFunc.Registers.A1, 1);
			AsmEmitter.emitLdr(w, output_reg, EmitFunc.Registers.SP, -4);
			AsmEmitter.emitBCond(w, AsmEmitter.Cond.LT, fail_label);
			AsmEmitter.emitLdrbIdx(w, scratch2_reg, output_reg, scratch3_reg);
			AsmEmitter.emitCmpImm(w, scratch2_reg, (int)'\n');
			AsmEmitter.emitMovCondImm(w, AsmEmitter.Cond.EQ, scratch2_reg, 0);
			AsmEmitter.emitStrbCondIdx(w, AsmEmitter.Cond.EQ, output_reg, scratch3_reg, scratch2_reg);
			w.print(fail_label);
			w.println(':');
			AsmEmitter.emitPseudoStoreVariable(w, sl, output_reg, TypeName.STRING);
		}
		else {
			assert(false);
		}
	}

	@Override
	public OptionalInt getDef() { return OptionalInt.of(idx); }

	@Override
	public ArrayList<Integer> getUses() {
		return new ArrayList<>();
	}

	@Override
	public ArrayList<Integer> getClobberedRegs() {
		final ArrayList<Integer> ret = new ArrayList<>();
		ret.add(EmitFunc.Registers.A1);
		ret.add(EmitFunc.Registers.A2);
		ret.add(EmitFunc.Registers.A3);
		ret.add(EmitFunc.Registers.A4);
		ret.add(EmitFunc.Registers.LR);
		return ret;
	}

	@Override
	public ArrayList<VarRegPair> getRegPreferences() {
		return new ArrayList<>();
	}
}