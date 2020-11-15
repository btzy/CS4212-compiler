package ir3;

import java.io.PrintStream;

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
			final String format = ctx.addCStringLiteral("%d\\012\\000"); // TODO: is %d 32-bits on ARM?
			final int arg_reg = term.emitAsm(w, EmitFunc.Registers.A2, ef, ctx, optimize);
			if (arg_reg != EmitFunc.Registers.A2) AsmEmitter.emitMovReg(w, EmitFunc.Registers.A2, arg_reg);
			AsmEmitter.emitLdrLitAddr(w, EmitFunc.Registers.A1, format);
			AsmEmitter.emitBlPlt(w, "printf");
		}
		else if (term.type == TypeName.BOOL) {
			final String true_lit = ctx.addCStringLiteral("true\\012\\000");
			final String false_lit = ctx.addCStringLiteral("false\\012\\000");
			final int arg_reg = term.emitAsm(w, EmitFunc.Registers.FP, ef, ctx, optimize);
			AsmEmitter.emitCmpImm(w, arg_reg, 0);
			AsmEmitter.emitLdrCondLitAddr(w, AsmEmitter.Cond.NE, EmitFunc.Registers.A1, true_lit);
			AsmEmitter.emitLdrCondLitAddr(w, AsmEmitter.Cond.EQ, EmitFunc.Registers.A1, false_lit);
			AsmEmitter.emitBlPlt(w, "printf");
		}
		else if (term.type == TypeName.STRING) {
			final String format = ctx.addCStringLiteral("%.*s\\012\\000");
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
}