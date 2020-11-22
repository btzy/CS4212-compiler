package ir3;

import java.util.OptionalInt;
import java.util.ArrayList;
import java.io.PrintStream;

public class CallUtil {
	// called by Call and CallExpr.
	public static void emitCall(PrintStream w, int funcidx, ArrayList<Terminal> args, EmitFunc ef, EmitContext ctx, boolean optimize) {
		boolean[] prepped = new boolean[args.size()];

		// Increase stack size to store excess args
		final int stack_extra_bytes = roundUpToMultipleOf8(4 * (args.size() - 4));
		if (args.size() > 4) AsmEmitter.emitSubImm(w, EmitFunc.Registers.SP, EmitFunc.Registers.SP, stack_extra_bytes);
		EmitFunc ef2 = ef.transpose((-4) * (args.size() - 4));
		
		// Move all registers that should go to memory so that we get a free scratch register
		for (int i=4; i<args.size(); ++i) {
			Terminal term = args.get(i);
			if (term instanceof LocalVariable) {
				final EmitFunc.StorageLocation sl = ef.storage_locations.get(((LocalVariable)term).idx);
				if (sl.isRegister) {
					final int reg = term.emitAsm(w, sl.value, ef2, ctx, optimize);
					AsmEmitter.emitPseudoStr(w, EmitFunc.Registers.SP, (i-4)*4, reg, ef.env.getType(i));
					prepped[i] = true;
				}
			}
		}

		// Find a free scratch register
		int scratch_reg = -1;
		for (int reg : new int[]{EmitFunc.Registers.A1, EmitFunc.Registers.A2, EmitFunc.Registers.A3, EmitFunc.Registers.A4, EmitFunc.Registers.LR}) {
			boolean isUsed = false;
			for (int i=0;i!=args.size(); ++i) {
				Terminal term = args.get(i);
				if (term instanceof LocalVariable) {
					final EmitFunc.StorageLocation sl = ef.storage_locations.get(((LocalVariable)term).idx);
					if (sl.isRegister && sl.value == reg) {
						isUsed = true;
						break;
					}
				}
			}
			if (!isUsed) {
				scratch_reg = reg;
				break;
			}
		}

		// Determine what *args* are currently in A1-A4
		int[] stuff_in_arg_reg = new int[4];
		for (int i=0;i!=4;++i) stuff_in_arg_reg[i]=-1;
		for (int i=0;i!=prepped.length; ++i){
			if (!prepped[i]) {
				Terminal term = args.get(i);
				if (term instanceof LocalVariable) {
					final EmitFunc.StorageLocation sl = ef.storage_locations.get(((LocalVariable)term).idx);
					if (sl.isRegister && sl.value < 4) {
						stuff_in_arg_reg[sl.value] = i;
					}
				}
			}
		}

		// Move items not involving the scratch register
		boolean[] visited = new boolean[4];
		for (int i=0;i!=prepped.length; ++i){
			if (!prepped[i]) {
				int res = prep(w, i, visited, prepped, stuff_in_arg_reg, args, scratch_reg, ef2, ctx, optimize);
				if (res == 1) {
					AsmEmitter.emitMovReg(w, i, scratch_reg);
				}
			}
		}

		// Move items involving the scratch register
		for (int i=0;i!=prepped.length; ++i){
			if (!prepped[i]) {
				prepLast(w, i, visited, prepped, stuff_in_arg_reg, args, scratch_reg, ef2, ctx, optimize);
			}
		}

		// Call function
		AsmEmitter.emitBl(w, ctx.func_specs.get(funcidx).getMangledName());
		if (args.size() > 4) AsmEmitter.emitAddImm(w, EmitFunc.Registers.SP, EmitFunc.Registers.SP, stack_extra_bytes);
	}

	/**
	 * Return 0: ok and prepped
	 * Return 1: cycle
	 * Return 2: delay due to scratch reg
	 */
	private static int prep(PrintStream w, int i, boolean[] visited, boolean[] prepped, int[] stuff_in_arg_reg, ArrayList<Terminal> args, int scratch_reg, EmitFunc ef, EmitContext ctx, boolean optimize) {
		if (i == scratch_reg) return 2;
		prepped[i] = true;
		Terminal term = args.get(i);
		if (term instanceof LocalVariable) {
			EmitFunc.StorageLocation sl = ef.storage_locations.get(((LocalVariable)term).idx);
			if (sl.isRegister && sl.value < 4) visited[sl.value] = true;
			if (i < 4 && i == sl.value) return 0;
		}
		if (i < 4 && visited[i]) { // cycle
			AsmEmitter.emitMovReg(w, scratch_reg, i);
			return 1;
		}
		if (i < 4 && stuff_in_arg_reg[i] != -1 && !prepped[stuff_in_arg_reg[i]]) {
			//if (visited[stuff_in_arg_reg[i]]) {
				//AsmEmitter.emitMovReg(w, scratch_reg, i);
				//return 1;
			//}
			int ret = prep(w, stuff_in_arg_reg[i], visited, prepped, stuff_in_arg_reg, args, scratch_reg, ef, ctx, optimize);
			if (ret == 2) {
				prepped[i] = false;
				return 2;
			}
			if (ret == 1) {
				AsmEmitter.emitMovReg(w, stuff_in_arg_reg[i], i);
				return 1;
			}
		}
		if (i < 4) {
			if (optimize) {
				final RegOrImm regimm = term.emitAsmImm(w, i, ef, ctx, optimize);
				regimm.ifRegOrElse(reg -> {
					if (reg != i) AsmEmitter.emitMovReg(w, i, reg);
				}, imm -> {
					AsmEmitter.emitMovImm(w, i, imm);
				});
			}
			else {
				final int reg = term.emitAsm(w, i, ef, ctx, optimize);
				if (reg != i) AsmEmitter.emitMovReg(w, i, reg);
			}
		}
		else {
			final int reg = term.emitAsm(w, scratch_reg, ef, ctx, optimize);
			AsmEmitter.emitPseudoStr(w, EmitFunc.Registers.SP, (i-4)*4, reg, ef.env.getType(i));
		}
		return 0;
	}
	private static void prepLast(PrintStream w, int i, boolean[] visited, boolean[] prepped, int[] stuff_in_arg_reg, ArrayList<Terminal> args, int scratch_reg, EmitFunc ef, EmitContext ctx, boolean optimize) {
		prepped[i] = true;
		Terminal term = args.get(i);
		if (i < 4 && stuff_in_arg_reg[i] != -1 && !prepped[stuff_in_arg_reg[i]]) {
			prepLast(w, stuff_in_arg_reg[i], visited, prepped, stuff_in_arg_reg, args, scratch_reg, ef, ctx, optimize);
		}
		assert (i < 4);
		if (optimize) {
			final RegOrImm regimm = term.emitAsmImm(w, i, ef, ctx, optimize);
			regimm.ifRegOrElse(reg -> {
				if (reg != i) AsmEmitter.emitMovReg(w, i, reg);
			}, imm -> {
				AsmEmitter.emitMovImm(w, i, imm);
			});
		}
		else {
			final int reg = term.emitAsm(w, i, ef, ctx, optimize);
			if (reg != i) AsmEmitter.emitMovReg(w, i, reg);
		}
	}

	public static int roundUpToMultipleOf8(int val) {
		return (val + 7) & (-8);
	}
}