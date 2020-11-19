package ir3;

import util.LocationRange;
import java.util.function.Consumer;
import java.io.PrintStream;
import java.util.OptionalInt;
import java.util.ArrayList;

/**
 * Represents a BinaryExpr with real type (cannot be nullptr_t).
 * In IR3, all expressions are typed.
 */
public class BinaryExpr extends Expr {
	public final Terminal left;
	public final Terminal right;
	public final BinOp op;

	public BinaryExpr(TypeName type, Terminal left, Terminal right, BinOp op) {
		super(type);
		this.left = left;
		this.right = right;
		this.op = op;
	}

	@Override
	public Expr makeRelExp3ByMaybeEmitIR3(LocationRange virtual_range, Context ctx, Consumer<? super ir3.Instruction> out) throws SemanticException {
		if (op.isRelOp()) return this;
		return super.makeRelExp3ByMaybeEmitIR3(virtual_range, ctx, out);
	}

	@Override
	public void print(PrintStream w, PrintContext pc) {
		left.print(w, pc);
		w.print(' ');
		op.print(w);
		w.print(' ');
		right.print(w, pc);
	}

	@Override
	public int emitAsm(PrintStream w, int hint_output_reg, EmitFunc ef, EmitContext ctx, boolean optimize) {
		if (op == BinOp.CONCAT)	{
			return emitConcatInstruction(w, hint_output_reg, left, right, ef, ctx, optimize);
		}
		else if (op == BinOp.DIVIDE) {
			return emitDivideInstruction(w, hint_output_reg, left, right, ef, ctx, optimize);
		}
		else {
			final int left_reg = left.emitAsm(w, EmitFunc.Registers.FP, ef, ctx, optimize);
			final int right_reg = right.emitAsm(w, EmitFunc.Registers.LR, ef, ctx, optimize);
			return emitOpInstruction(w, op, hint_output_reg, left_reg, right_reg);
		}
	}

	private static int emitOpInstruction(PrintStream w, BinOp op, int output_reg, int left_reg, int right_reg) {
		switch (op) {
			case EQ:
				AsmEmitter.emitCmpReg(w, left_reg, right_reg);
				AsmEmitter.emitMovCondImm(w, AsmEmitter.Cond.EQ, output_reg, 1);
				AsmEmitter.emitMovCondImm(w, AsmEmitter.Cond.NE, output_reg, 0);
				return output_reg;
			case NE:
				AsmEmitter.emitCmpReg(w, left_reg, right_reg);
				AsmEmitter.emitMovCondImm(w, AsmEmitter.Cond.NE, output_reg, 1);
				AsmEmitter.emitMovCondImm(w, AsmEmitter.Cond.EQ, output_reg, 0);
				return output_reg;
			case LT:
				AsmEmitter.emitCmpReg(w, left_reg, right_reg);
				AsmEmitter.emitMovCondImm(w, AsmEmitter.Cond.LT, output_reg, 1);
				AsmEmitter.emitMovCondImm(w, AsmEmitter.Cond.GE, output_reg, 0);
				return output_reg;
			case LE:
				AsmEmitter.emitCmpReg(w, left_reg, right_reg);
				AsmEmitter.emitMovCondImm(w, AsmEmitter.Cond.LE, output_reg, 1);
				AsmEmitter.emitMovCondImm(w, AsmEmitter.Cond.GT, output_reg, 0);
				return output_reg;
			case GT:
				AsmEmitter.emitCmpReg(w, left_reg, right_reg);
				AsmEmitter.emitMovCondImm(w, AsmEmitter.Cond.GT, output_reg, 1);
				AsmEmitter.emitMovCondImm(w, AsmEmitter.Cond.LE, output_reg, 0);
				return output_reg;
			case GE:
				AsmEmitter.emitCmpReg(w, left_reg, right_reg);
				AsmEmitter.emitMovCondImm(w, AsmEmitter.Cond.GE, output_reg, 1);
				AsmEmitter.emitMovCondImm(w, AsmEmitter.Cond.LT, output_reg, 0);
				return output_reg;
			case PLUS:
				AsmEmitter.emitAddReg(w, output_reg, left_reg, right_reg);
				return output_reg;
			case MINUS:
				AsmEmitter.emitSubReg(w, output_reg, left_reg, right_reg);
				return output_reg;
			case TIMES:
				AsmEmitter.emitMulReg(w, output_reg, left_reg, right_reg);
				return output_reg;
			case CONJUNCTION:
				AsmEmitter.emitAndReg(w, output_reg, left_reg, right_reg);
				return output_reg;
			case DISJUNCTION:
				AsmEmitter.emitOrrReg(w, output_reg, left_reg, right_reg);
				return output_reg;
		}
		assert(false);
		return -1;
	}

	// this makes function calls to stdlib
	private static int emitConcatInstruction(PrintStream w, int hint_output_reg, Terminal left, Terminal right, EmitFunc ef, EmitContext ctx, boolean optimize) {
		// This part contains "microcode" of sorts to allocate a new string and copy the original data to it
		final int scratch1_reg = EmitFunc.Registers.FP;
		final int scratch2_reg = EmitFunc.Registers.LR; // note: this reg is not preserved across function calls
		final int tmp_output_reg = isThisReg(left, hint_output_reg, ef) || isThisReg(right, hint_output_reg, ef) ? scratch1_reg : hint_output_reg;

		// Determine resultant string size
		{
			final int left_reg = left.emitAsm(w, scratch1_reg, ef, ctx, optimize);
			AsmEmitter.emitLdr(w, scratch1_reg, left_reg, 0);
			final int right_reg = right.emitAsm(w, scratch2_reg, ef, ctx, optimize);
			AsmEmitter.emitLdr(w, scratch2_reg, right_reg, 0);
			AsmEmitter.emitAddReg(w, scratch1_reg, scratch1_reg, scratch2_reg);
		}
		// now resultant string size is stored in scratch1_reg

		// Allocate heap memory and write the size
		AsmEmitter.emitAddImm(w, EmitFunc.Registers.A1, scratch1_reg, 4); // 4 extra bytes for storing the length
		AsmEmitter.emitBlPlt(w, "malloc");
		AsmEmitter.emitStr(w, EmitFunc.Registers.A1, 0, scratch1_reg);
		AsmEmitter.emitMovReg(w, tmp_output_reg, EmitFunc.Registers.A1);
		// now the new buffer is stored in tmp_output_reg

		// Copy strings into new memory
		{
			AsmEmitter.emitAddImm(w, EmitFunc.Registers.A1, EmitFunc.Registers.A1, 4);
			final int left_reg = left.emitAsm(w, scratch2_reg, ef, ctx, optimize);
			AsmEmitter.emitAddImm(w, EmitFunc.Registers.A2, left_reg, 4);
			AsmEmitter.emitLdr(w, EmitFunc.Registers.A3, left_reg, 0);
			emitMemcpySequence(w, EmitFunc.Registers.A1, EmitFunc.Registers.A2, EmitFunc.Registers.A3, EmitFunc.Registers.A4, ctx);
			final int right_reg = right.emitAsm(w, scratch2_reg, ef, ctx, optimize);
			AsmEmitter.emitAddImm(w, EmitFunc.Registers.A2, right_reg, 4);
			AsmEmitter.emitLdr(w, EmitFunc.Registers.A3, right_reg, 0);
			emitMemcpySequence(w, EmitFunc.Registers.A1, EmitFunc.Registers.A2, EmitFunc.Registers.A3, EmitFunc.Registers.A4, ctx);
		}
		// now the string data has been copied, and tmp_output_reg still contains the pointer to the new string

		return tmp_output_reg;
	}

	/**
	 * Emits something like memcpy, but modifies the given regs.  `clobber_reg` is clobbered.
	 * Upon returning, the regs will contain these values:
	 * dest_reg <- dest_reg + count_reg;
	 * src_reg <- src_reg + count_reg;
	 * count_reg <- 0;
	 */
	private static void emitMemcpySequence(PrintStream w, int dest_reg, int src_reg, int count_reg, int clobber_reg, EmitContext ctx) {
		final Object label_namespace = new Object();
		final String label_start = ctx.addLabel(label_namespace, 1);
		final String label_end = ctx.addLabel(label_namespace, 2);
		AsmEmitter.emitCmpImm(w, count_reg, 0);
		AsmEmitter.emitBCond(w, AsmEmitter.Cond.EQ, label_end);
		w.print(label_start);
		w.println(':');
		AsmEmitter.emitLdrbPostOffset(w, clobber_reg, src_reg, 1);
		AsmEmitter.emitStrbPostOffset(w, dest_reg, 1, clobber_reg);
		AsmEmitter.emitSubFlagsImm(w, count_reg, count_reg, 1);
		AsmEmitter.emitBCond(w, AsmEmitter.Cond.NE, label_start);
		w.print(label_end);
		w.println(':');
	}

	private static int emitDivideInstruction(PrintStream w, int hint_output_reg, Terminal left, Terminal right, EmitFunc ef, EmitContext ctx, boolean optimize) {
		// % assembly code for long division:
		// mov __A2,right                 %dividend
		// mov A4,__A2,ASR #31            % -1 if negative, 0 otherwise
		// add A3,__A2,__A2,ASR #31       % magic to take absolute value
		// eors A2,A3,__A2,ASR #31        % magic to take absolute value
		// moveq hint_output_reg,#0       % if divisor is zero, set output to 0, and skip to end
		// beq L3
		// mov __A1,left                  %divisor
		// eor A4,A4,__A1,ASR #31         % -1 if sign different, 0 if same
		// add A3,__A1,__A1,ASR #31       % magic to take absolute value
		// eor A1,A3,__A1,ASR #31         % magic to take absolute value
		// % now A1 contains nonnegative dividend, A2 contains nonnegative divisor
		// mov A3,#1
		// L1:
		// cmp A1,A2,LSL #1               % shift A2 and A3 to the biggest digit
		// movcs A2,A2,LSL #1
		// movcs A3,A3,LSL #1
		// bcs L1
		// mov hint_output_reg,A4         % set output to -1 if we need to flip the sign later, otherwise 0.
		// L2:
		// cmp A1,A2
		// subcs A1,A1,A2
		// addcs hint_output_reg,hint_output_reg,A3
		// movs A3,A3,LSR #1
		// movne A2,A2,LSR #1
		// bne L2
		// eor hint_output_reg,hint_output_reg,A4  % set the sign
		// L3:

		final Object label_namespace = new Object();
		final String label1 = ctx.addLabel(label_namespace, 1);
		final String label2 = ctx.addLabel(label_namespace, 2);
		final String label3 = ctx.addLabel(label_namespace, 3);

		final int right_reg = right.emitAsm(w, EmitFunc.Registers.A2, ef, ctx, optimize);
		AsmEmitter.emitMovRegShift(w, EmitFunc.Registers.A4, right_reg, AsmEmitter.Shift.ASR, 31);
		AsmEmitter.emitAddRegShift(w, EmitFunc.Registers.A3, right_reg, right_reg, AsmEmitter.Shift.ASR, 31);
		AsmEmitter.emitEorFlagsRegShift(w, EmitFunc.Registers.A2, EmitFunc.Registers.A3, right_reg, AsmEmitter.Shift.ASR, 31);
		AsmEmitter.emitMovCondImm(w, AsmEmitter.Cond.EQ, hint_output_reg, 0);
		AsmEmitter.emitBCond(w, AsmEmitter.Cond.EQ, label3);
		final int left_reg = left.emitAsm(w, EmitFunc.Registers.A1, ef, ctx, optimize);
		AsmEmitter.emitEorRegShift(w, EmitFunc.Registers.A4, EmitFunc.Registers.A4, left_reg, AsmEmitter.Shift.ASR, 31);
		AsmEmitter.emitAddRegShift(w, EmitFunc.Registers.A3, left_reg, left_reg, AsmEmitter.Shift.ASR, 31);
		AsmEmitter.emitEorRegShift(w, EmitFunc.Registers.A1, EmitFunc.Registers.A3, left_reg, AsmEmitter.Shift.ASR, 31);
		AsmEmitter.emitMovImm(w, EmitFunc.Registers.A3, 1);
		w.print(label1);
		w.println(':');
		AsmEmitter.emitCmpRegShift(w, EmitFunc.Registers.A1, EmitFunc.Registers.A2, AsmEmitter.Shift.LSL, 1);
		AsmEmitter.emitMovCondRegShift(w, AsmEmitter.Cond.CS, EmitFunc.Registers.A2, EmitFunc.Registers.A2, AsmEmitter.Shift.LSL, 1);
		AsmEmitter.emitMovCondRegShift(w, AsmEmitter.Cond.CS, EmitFunc.Registers.A3, EmitFunc.Registers.A3, AsmEmitter.Shift.LSL, 1);
		AsmEmitter.emitBCond(w, AsmEmitter.Cond.CS, label1);
		AsmEmitter.emitMovReg(w, hint_output_reg, EmitFunc.Registers.A4);
		w.print(label2);
		w.println(':');
		AsmEmitter.emitCmpReg(w, EmitFunc.Registers.A1, EmitFunc.Registers.A2);
		AsmEmitter.emitSubCondReg(w, AsmEmitter.Cond.CS, EmitFunc.Registers.A1, EmitFunc.Registers.A1, EmitFunc.Registers.A2);
		AsmEmitter.emitAddCondReg(w, AsmEmitter.Cond.CS, hint_output_reg, hint_output_reg, EmitFunc.Registers.A3);
		AsmEmitter.emitMovFlagsRegShift(w, EmitFunc.Registers.A3, EmitFunc.Registers.A3, AsmEmitter.Shift.LSR, 1);
		AsmEmitter.emitMovCondRegShift(w, AsmEmitter.Cond.NE, EmitFunc.Registers.A2, EmitFunc.Registers.A2, AsmEmitter.Shift.LSR, 1);
		AsmEmitter.emitBCond(w, AsmEmitter.Cond.NE, label2);
		AsmEmitter.emitEorReg(w, hint_output_reg, hint_output_reg, EmitFunc.Registers.A4);
		w.print(label3);
		w.println(':');

		return hint_output_reg;
	}

	private static boolean isThisReg(Terminal t, int reg, EmitFunc ef) {
		if (!(t instanceof LocalVariable)) return false;
		final EmitFunc.StorageLocation sl = ef.storage_locations.get(((LocalVariable)t).idx);
		return sl.isRegister && sl.value == reg;
	}

	@Override
	public ArrayList<Integer> getUses() {
		final ArrayList<Integer> ret = left.getUses();
		ret.addAll(right.getUses());
		return ret;
	}
	
	@Override
	public ArrayList<Integer> getClobberedRegs() {
		final ArrayList<Integer> ret = left.getClobberedRegs();
		ret.addAll(right.getClobberedRegs());
		if (op == BinOp.CONCAT) {
			ret.add(EmitFunc.Registers.A1);
			ret.add(EmitFunc.Registers.A2);
			ret.add(EmitFunc.Registers.A3);
			ret.add(EmitFunc.Registers.A4);
			ret.add(EmitFunc.Registers.LR);
		}
		else if (op == BinOp.DIVIDE) {
			ret.add(EmitFunc.Registers.A1);
			ret.add(EmitFunc.Registers.A2);
			ret.add(EmitFunc.Registers.A3);
			ret.add(EmitFunc.Registers.A4);
		}
		else {
			ret.add(EmitFunc.Registers.FP);
			ret.add(EmitFunc.Registers.LR);
		}
		return ret;
	}
}