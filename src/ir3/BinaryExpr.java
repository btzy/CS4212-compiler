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

	@Override
	public AsmEmitter.Cond emitCondAsm(PrintStream w, int hint_scratch_reg, EmitFunc ef, EmitContext ctx, boolean optimize) {
		final int left_reg = left.emitAsm(w, EmitFunc.Registers.FP, ef, ctx, optimize);
		final int right_reg = right.emitAsm(w, EmitFunc.Registers.LR, ef, ctx, optimize);
		return emitCondOpInstruction(w, op, left_reg, right_reg);
	}
	
	private static AsmEmitter.Cond emitCondOpInstruction(PrintStream w, BinOp op, int left_reg, int right_reg) {
		switch (op) {
			case EQ:
				AsmEmitter.emitCmpReg(w, left_reg, right_reg);
				return AsmEmitter.Cond.EQ;
			case NE:
				AsmEmitter.emitCmpReg(w, left_reg, right_reg);
				return AsmEmitter.Cond.NE;
			case LT:
				AsmEmitter.emitCmpReg(w, left_reg, right_reg);
				return AsmEmitter.Cond.LT;
			case LE:
				AsmEmitter.emitCmpReg(w, left_reg, right_reg);
				return AsmEmitter.Cond.LE;
			case GT:
				AsmEmitter.emitCmpReg(w, left_reg, right_reg);
				return AsmEmitter.Cond.GT;
			case GE:
				AsmEmitter.emitCmpReg(w, left_reg, right_reg);
				return AsmEmitter.Cond.GE;
			case CONJUNCTION:
				AsmEmitter.emitTstReg(w, left_reg, right_reg);
				return AsmEmitter.Cond.NE;
			case DISJUNCTION:
				AsmEmitter.emitCmnReg(w, left_reg, right_reg);
				return AsmEmitter.Cond.NE;
		}
		assert(false);
		return null;
	}

	// this makes function calls to stdlib
	private static int emitConcatInstruction(PrintStream w, int hint_output_reg, Terminal left, Terminal right, EmitFunc ef, EmitContext ctx, boolean optimize) {
		// This part contains "microcode" of sorts to allocate a new string and copy the original data to it
		final int scratch1_reg = EmitFunc.Registers.FP;
		final int scratch2_reg = EmitFunc.Registers.V6;
		final int scratch3_reg = EmitFunc.Registers.V7;
		boolean hasScratch1 = isThisReg(left, scratch1_reg, ef) || isThisReg(right, scratch1_reg, ef);
		boolean hasScratch2 = isThisReg(left, scratch2_reg, ef) || isThisReg(right, scratch2_reg, ef);
		boolean hasScratch3 = isThisReg(left, scratch3_reg, ef) || isThisReg(right, scratch3_reg, ef);
		boolean hasLR = isThisReg(left, EmitFunc.Registers.LR, ef) || isThisReg(right, EmitFunc.Registers.LR, ef);
		final int scratch_reg = hasScratch1 ? (hasScratch2 ? scratch3_reg : scratch2_reg) : scratch1_reg;
		if (scratch_reg == scratch1_reg) hasScratch1 = true;
		else if (scratch_reg == scratch2_reg) hasScratch2 = true;
		else if (scratch_reg == scratch3_reg) hasScratch3 = true;
		final int tmp_output_reg = isThisReg(left, hint_output_reg, ef) || isThisReg(right, hint_output_reg, ef) || scratch_reg == hint_output_reg || hint_output_reg < 3 ? (
			hasLR ? (hasScratch1 ? (hasScratch2 ? (hasScratch3 ? EmitFunc.Registers.LR : scratch3_reg) : scratch2_reg) : scratch1_reg) : EmitFunc.Registers.LR
			) : hint_output_reg;

		int override_left_reg = -1, override_right_reg = -1;
		if (isThisReg(left, EmitFunc.Registers.A1, ef) || isThisReg(left, EmitFunc.Registers.A2, ef) || isThisReg(left, EmitFunc.Registers.A3, ef) || isThisReg(left, EmitFunc.Registers.A4, ef)) {
			override_left_reg = hasScratch1 ? (hasScratch2 ? scratch3_reg : scratch2_reg) : scratch1_reg;
			if (override_left_reg == scratch1_reg) hasScratch1 = true;
			else if (override_left_reg == scratch2_reg) hasScratch2 = true;
			else if (override_left_reg == scratch3_reg) hasScratch3 = true;
		}
		if (isThisReg(right, EmitFunc.Registers.A1, ef) || isThisReg(right, EmitFunc.Registers.A2, ef) || isThisReg(right, EmitFunc.Registers.A3, ef) || isThisReg(right, EmitFunc.Registers.A4, ef)) {
			override_right_reg = hasScratch1 ? (hasScratch2 ? scratch3_reg : scratch2_reg) : scratch1_reg;
			if (override_right_reg == scratch1_reg) hasScratch1 = true;
			else if (override_right_reg == scratch2_reg) hasScratch2 = true;
			else if (override_right_reg == scratch3_reg) hasScratch3 = true;
		}

		// Determine resultant string size
		{
			int left_reg;
			if (override_left_reg != -1) {
				left_reg = left.emitAsm(w, override_left_reg, ef, ctx, optimize);
				if (left_reg != override_left_reg) AsmEmitter.emitMovReg(w, override_left_reg, left_reg);
				left_reg = override_left_reg;
			}
			else {
				left_reg = left.emitAsm(w, EmitFunc.Registers.A1, ef, ctx, optimize);
			}
			int right_reg;
			if (override_right_reg != -1) {
				right_reg = right.emitAsm(w, override_right_reg, ef, ctx, optimize);
				if (right_reg != override_right_reg) AsmEmitter.emitMovReg(w, override_right_reg, right_reg);
				right_reg = override_right_reg;
			}
			else {
				right_reg = right.emitAsm(w, EmitFunc.Registers.A2, ef, ctx, optimize);
			}
			AsmEmitter.emitLdr(w, EmitFunc.Registers.A1, left_reg, 0);
			AsmEmitter.emitLdr(w, EmitFunc.Registers.A2, right_reg, 0);
			AsmEmitter.emitAddReg(w, scratch_reg, EmitFunc.Registers.A1, EmitFunc.Registers.A2);
		}
		// now resultant string size is stored in scratch1_reg

		// Allocate heap memory and write the size
		AsmEmitter.emitAddImm(w, EmitFunc.Registers.A1, scratch_reg, 4); // 4 extra bytes for storing the length
		AsmEmitter.emitBlPlt(w, "malloc");
		AsmEmitter.emitStr(w, EmitFunc.Registers.A1, 0, scratch_reg);
		AsmEmitter.emitMovReg(w, tmp_output_reg, EmitFunc.Registers.A1);
		// now the new buffer is stored in tmp_output_reg

		// Copy strings into new memory
		{
			AsmEmitter.emitAddImm(w, EmitFunc.Registers.A1, EmitFunc.Registers.A1, 4);
			final int left_reg = override_left_reg == -1 ? left.emitAsm(w, scratch_reg, ef, ctx, optimize) : override_left_reg;
			AsmEmitter.emitAddImm(w, EmitFunc.Registers.A2, left_reg, 4);
			AsmEmitter.emitLdr(w, EmitFunc.Registers.A3, left_reg, 0);
			AsmEmitter.emitMemcpySequence(w, EmitFunc.Registers.A1, EmitFunc.Registers.A2, EmitFunc.Registers.A3, EmitFunc.Registers.A4, ctx);
			final int right_reg = override_right_reg == -1 ? right.emitAsm(w, scratch_reg, ef, ctx, optimize) : override_right_reg;
			AsmEmitter.emitAddImm(w, EmitFunc.Registers.A2, right_reg, 4);
			AsmEmitter.emitLdr(w, EmitFunc.Registers.A3, right_reg, 0);
			AsmEmitter.emitMemcpySequence(w, EmitFunc.Registers.A1, EmitFunc.Registers.A2, EmitFunc.Registers.A3, EmitFunc.Registers.A4, ctx);
		}
		// now the string data has been copied, and tmp_output_reg still contains the pointer to the new string

		return tmp_output_reg;
	}

	private static int emitDivideInstruction(PrintStream w, int hint_output_reg, Terminal left, Terminal right, EmitFunc ef, EmitContext ctx, boolean optimize) {
		// % assembly code for long division:
		// mov __A2,right                 %dividend
		// mov __A1,left                  %divisor
		// mov A4,__A2,ASR #31            % -1 if negative, 0 otherwise
		// add A3,__A2,__A2,ASR #31       % magic to take absolute value
		// eors A2,A3,__A2,ASR #31        % magic to take absolute value
		// moveq hint_output_reg,#0       % if divisor is zero, set output to 0, and skip to end
		// beq L3
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

		int eA1 = EmitFunc.Registers.A1, eA2 = EmitFunc.Registers.A2, eA3 = EmitFunc.Registers.A3, eA4 = EmitFunc.Registers.A4;
		int left_reg = left.emitAsm(w, isThisReg(right, eA1, ef) ? eA2 : eA1, ef, ctx, optimize);
		if (left_reg == eA2) {
			int tmp = eA1;
			eA1 = eA2;
			eA2 = tmp;
		}
		if (left_reg == eA3) {
			int tmp = eA1;
			eA1 = eA3;
			eA3 = tmp;
		}
		if (left_reg == eA4) {
			int tmp = eA1;
			eA1 = eA4;
			eA4 = tmp;
		}
		int right_reg = right.emitAsm(w, eA2, ef, ctx, optimize);
		if (right_reg == eA3) {
			int tmp = eA2;
			eA2 = eA3;
			eA3 = tmp;
		}
		if (right_reg == eA4) {
			int tmp = eA2;
			eA2 = eA4;
			eA4 = tmp;
		}
		if (right_reg == left_reg && left_reg == eA1) {
			AsmEmitter.emitMovReg(w, eA2, left_reg);
		}
		if (hint_output_reg == eA1 || hint_output_reg == eA2 || hint_output_reg == eA3 || hint_output_reg == eA4) {
			hint_output_reg = EmitFunc.Registers.LR;
		}
		AsmEmitter.emitMovRegShift(w, eA4, right_reg, AsmEmitter.Shift.ASR, 31);
		AsmEmitter.emitAddRegShift(w, eA3, right_reg, right_reg, AsmEmitter.Shift.ASR, 31);
		AsmEmitter.emitEorFlagsRegShift(w, eA2, eA3, right_reg, AsmEmitter.Shift.ASR, 31);
		AsmEmitter.emitMovCondImm(w, AsmEmitter.Cond.EQ, hint_output_reg, 0);
		AsmEmitter.emitBCond(w, AsmEmitter.Cond.EQ, label3);
		AsmEmitter.emitEorRegShift(w, eA4, eA4, left_reg, AsmEmitter.Shift.ASR, 31);
		AsmEmitter.emitAddRegShift(w, eA3, left_reg, left_reg, AsmEmitter.Shift.ASR, 31);
		AsmEmitter.emitEorRegShift(w, eA1, eA3, left_reg, AsmEmitter.Shift.ASR, 31);
		AsmEmitter.emitMovImm(w, eA3, 1);
		w.print(label1);
		w.println(':');
		AsmEmitter.emitCmpRegShift(w, eA1, eA2, AsmEmitter.Shift.LSL, 1);
		AsmEmitter.emitMovCondRegShift(w, AsmEmitter.Cond.CS, eA2, eA2, AsmEmitter.Shift.LSL, 1);
		AsmEmitter.emitMovCondRegShift(w, AsmEmitter.Cond.CS, eA3, eA3, AsmEmitter.Shift.LSL, 1);
		AsmEmitter.emitBCond(w, AsmEmitter.Cond.CS, label1);
		AsmEmitter.emitMovReg(w, hint_output_reg, eA4);
		w.print(label2);
		w.println(':');
		AsmEmitter.emitCmpReg(w, eA1, eA2);
		AsmEmitter.emitSubCondReg(w, AsmEmitter.Cond.CS, eA1, eA1, eA2);
		AsmEmitter.emitAddCondReg(w, AsmEmitter.Cond.CS, hint_output_reg, hint_output_reg, eA3);
		AsmEmitter.emitMovFlagsRegShift(w, eA3, eA3, AsmEmitter.Shift.LSR, 1);
		AsmEmitter.emitMovCondRegShift(w, AsmEmitter.Cond.NE, eA2, eA2, AsmEmitter.Shift.LSR, 1);
		AsmEmitter.emitBCond(w, AsmEmitter.Cond.NE, label2);
		AsmEmitter.emitEorReg(w, hint_output_reg, hint_output_reg, eA4);
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
			ret.add(EmitFunc.Registers.V6);
			ret.add(EmitFunc.Registers.V7);
			ret.add(EmitFunc.Registers.FP);
			ret.add(EmitFunc.Registers.LR);
		}
		else if (op == BinOp.DIVIDE) {
			ret.add(EmitFunc.Registers.A1);
			ret.add(EmitFunc.Registers.A2);
			ret.add(EmitFunc.Registers.A3);
			ret.add(EmitFunc.Registers.A4);
			ret.add(EmitFunc.Registers.LR);
		}
		else {
			ret.add(EmitFunc.Registers.FP);
			ret.add(EmitFunc.Registers.LR);
		}
		return ret;
	}

	@Override
	public ArrayList<VarRegPair> getRegPreferences() {
		return new ArrayList<>();
	}

	@Override
	public OptionalInt getOutputRegPreference() {
		return OptionalInt.empty();
	}
}