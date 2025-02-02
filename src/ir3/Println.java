package ir3;

import java.io.PrintStream;
import java.util.OptionalInt;
import java.util.ArrayList;

public class Println extends Instruction {
	public final Terminal term;
	public Println(Terminal term) { this.term = term; }

	@Override
	public void print(PrintStream w, PrintContext pc) {
		w.print("  println(");
		term.print(w, pc);
		w.println(");");
	}

	@Override
	public void emitAsm(PrintStream w, EmitFunc ef, EmitContext ctx, boolean optimize) {
		if (term.type == TypeName.INT) {
			final String format = ctx.addCStringLiteral("%d\n");
			if (optimize) {
				final RegOrImm arg_regimm = term.emitAsmImm(w, EmitFunc.Registers.A2, ef, ctx, optimize);
				arg_regimm.ifRegOrElse(reg -> {
					if (reg != EmitFunc.Registers.A2) AsmEmitter.emitMovReg(w, EmitFunc.Registers.A2, reg);
				}, imm -> {
					AsmEmitter.emitMovImm(w, EmitFunc.Registers.A2, imm);
				});
				}
			else {
				final int arg_reg = term.emitAsm(w, EmitFunc.Registers.A2, ef, ctx, optimize);
				if (arg_reg != EmitFunc.Registers.A2) AsmEmitter.emitMovReg(w, EmitFunc.Registers.A2, arg_reg);
			}
			AsmEmitter.emitLdrLitAddr(w, EmitFunc.Registers.A1, format);
			AsmEmitter.emitBlPlt(w, "printf");
		}
		else if (term.type == TypeName.BOOL) {
			if (optimize) {
				final RegOrImm arg_regimm = term.emitAsmImm(w, EmitFunc.Registers.A1, ef, ctx, optimize);
				arg_regimm.ifRegOrElse(reg -> {
					final String true_lit = ctx.addCStringLiteral("true");
					final String false_lit = ctx.addCStringLiteral("false");
					AsmEmitter.emitCmpImm(w, reg, 0);
					AsmEmitter.emitLdrCondLitAddr(w, AsmEmitter.Cond.NE, EmitFunc.Registers.A1, true_lit);
					AsmEmitter.emitLdrCondLitAddr(w, AsmEmitter.Cond.EQ, EmitFunc.Registers.A1, false_lit);
				}, imm -> {
					if (imm != 0) {
						final String true_lit = ctx.addCStringLiteral("true");
						AsmEmitter.emitLdrLitAddr(w, EmitFunc.Registers.A1, true_lit);
					}
					else {
						final String false_lit = ctx.addCStringLiteral("false");
						AsmEmitter.emitLdrLitAddr(w, EmitFunc.Registers.A1, false_lit);
					}
				});
			}
			else {
				final String true_lit = ctx.addCStringLiteral("true");
				final String false_lit = ctx.addCStringLiteral("false");
				final int arg_reg = term.emitAsm(w, EmitFunc.Registers.A1, ef, ctx, optimize);
				AsmEmitter.emitCmpImm(w, arg_reg, 0);
				AsmEmitter.emitLdrCondLitAddr(w, AsmEmitter.Cond.NE, EmitFunc.Registers.A1, true_lit);
				AsmEmitter.emitLdrCondLitAddr(w, AsmEmitter.Cond.EQ, EmitFunc.Registers.A1, false_lit);
			}
			AsmEmitter.emitBlPlt(w, "puts");
		}
		else if (term.type == TypeName.STRING) {
			final String format = ctx.addCStringLiteral("%.*s\n");
			final int arg_reg = term.emitAsm(w, EmitFunc.Registers.A3, ef, ctx, optimize);
			if (arg_reg != EmitFunc.Registers.A3) {
				AsmEmitter.emitAddImm(w, EmitFunc.Registers.A3, arg_reg, 4);
				AsmEmitter.emitLdr(w, EmitFunc.Registers.A2, arg_reg, 0);
			}
			else {
				AsmEmitter.emitLdrPostOffset(w, EmitFunc.Registers.A2, EmitFunc.Registers.A3, 4);
			}
			AsmEmitter.emitLdrLitAddr(w, EmitFunc.Registers.A1, format);
			AsmEmitter.emitBlPlt(w, "printf");
		}
		else {
			assert(false);
		}
	}

	@Override
	public OptionalInt getDef() { return OptionalInt.empty(); }

	@Override
	public ArrayList<Integer> getUses() {
		final ArrayList<Integer> ret = new ArrayList<>();
		ret.addAll(term.getUses());
		return ret;
	}

	@Override
	public ArrayList<Integer> getClobberedRegs() {
		final ArrayList<Integer> ret = new ArrayList<>(term.getClobberedRegs());
		ret.add(EmitFunc.Registers.A1);
		ret.add(EmitFunc.Registers.A2);
		ret.add(EmitFunc.Registers.A3);
		ret.add(EmitFunc.Registers.A4);
		ret.add(EmitFunc.Registers.LR);
		return ret;
	}

	@Override
	public ArrayList<VarRegPair> getRegPreferences() {
		if (term instanceof LocalVariable) {
			final LocalVariable lv = (LocalVariable)term;
			if (term.type == TypeName.INT) {
				final ArrayList<VarRegPair> ret = new ArrayList<>();
				ret.add(new VarRegPair(lv.idx, EmitFunc.Registers.A2));
				return ret;
			}
			else if (term.type == TypeName.BOOL) {
				return new ArrayList<>();
			}
			else if (term.type == TypeName.STRING) {
				final ArrayList<VarRegPair> ret = new ArrayList<>();
				ret.add(new VarRegPair(lv.idx, EmitFunc.Registers.A3));
				return ret;
			}
			else {
				assert(false);
			}
		}
		return new ArrayList<>();
	}
}