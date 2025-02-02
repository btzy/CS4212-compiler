package ir3;

import java.util.ArrayList;
import java.io.PrintStream;
import java.util.Collections;

public class FuncBody {
	private final LocalEnvironment env;
	private final int num_params;
	private final ArrayList<Instruction> insts;

	public FuncBody(LocalEnvironment env, int num_params, ArrayList<Instruction> insts) {
		this.env = env;
		this.num_params = num_params;
		this.insts = insts;
	}

	public void print(PrintStream w, ArrayList<FuncSpec> func_specs, ArrayList<ClassDescriptor> cds) {
		w.print('(');
		boolean need_comma = false;
		for (int i=0; i!=num_params; ++i) {
			if (need_comma) {
				w.print(',');
			}
			w.print(env.getType(i).name);
			w.print(' ');
			w.print(env.getName(i));
			need_comma = true;
		}
		w.println("){");
		for (int i=num_params; i!=env.size(); ++i) {
			w.print("  ");
			w.print(env.getType(i).name);
			w.print(' ');
			w.print(env.getName(i));
			w.println(';');
		}
		final PrintContext pc = new PrintContext(env, func_specs, cds);
		for (Instruction inst : insts) {
			inst.print(w, pc);
		}
		w.println('}');
	}

	/**
	 * Determine where to store each variable, and amount to shift the sp.
	 */
	public EmitFunc determineStorage(boolean optimize) {
		if (!optimize) return determineStorageUnoptimized();
		else return determineStorageOptimized();
	}

	
	public EmitFunc determineStorageUnoptimized() {
		// 2+k callee-saved registers to store: fp, lr, <all args from registers>
		// and all locals except params go onto the stack
		final int stackBytes = CallUtil.roundUpToMultipleOf8((2 + Math.min(num_params, 4) + (env.size() - num_params)) * 4);
		ArrayList<Integer> saved_regs = new ArrayList<>();
		ArrayList<Integer> saved_params = new ArrayList<>();
		saved_regs.add(EmitFunc.Registers.FP);
		saved_regs.add(EmitFunc.Registers.LR);
		for (int i=0; i!=Math.min(num_params, 4); ++i) {
			saved_params.add(i);
		}
		ArrayList<EmitFunc.StorageLocation> storage_locations = new ArrayList<>();
		for (int i=0; i!=env.size(); ++i) {
			if (i < num_params) {
				// is a param
				if (i < 4) storage_locations.add(EmitFunc.StorageLocation.makeMemLocal(stackBytes - (2 + Math.min(num_params, 4) - i) * 4));
				else storage_locations.add(EmitFunc.StorageLocation.makeMemLocal(stackBytes + (i - 4) * 4));
			}
			else {
				storage_locations.add(EmitFunc.StorageLocation.makeMemLocal(stackBytes - (3 + Math.min(num_params, 4) + (i - num_params)) * 4));
			}
		}
		// round up to 8 to satisfy ARM stack alignment
		return new EmitFunc(stackBytes, saved_regs, saved_params, 0, storage_locations, env);
	}

	public EmitFunc determineStorageOptimized() {
		// Generate graph using liveness analysis
		InterferenceGraph graph = InterferenceGraph.generate(env, num_params, insts);
		// Run graph colouring
		EmitFunc.StorageLocation[] assignment = graph.colour();

		// Prepare the EmitFunc
		boolean[] used_regs = new boolean[16];
		used_regs[EmitFunc.Registers.LR] = true;
		int num_spills = 0;
		for (EmitFunc.StorageLocation sl : assignment) {
			if (sl.isRegister) used_regs[sl.value] = true;
		}
		ArrayList<Integer> saved_regs = new ArrayList<>();
		for (int i=4;i!=used_regs.length; ++i) {
			if (used_regs[i]) saved_regs.add(i);
		}
		for (int i=0; i!=assignment.length; ++i){
			if (!assignment[i].isRegister && (i < 4 || i >= num_params)) {
				++num_spills;
			}
		}
		final int stack_bytes = CallUtil.roundUpToMultipleOf8((saved_regs.size() + num_spills) * 4);
		int offset = 0;
		for (int i=0; i!=assignment.length; ++i){
			if (!assignment[i].isRegister) {
				if (i < 4 || i >= num_params) {
					assignment[i] = EmitFunc.StorageLocation.makeMemLocal(stack_bytes - (saved_regs.size() + (++offset)) * 4);
				}
				else {
					assignment[i] = EmitFunc.StorageLocation.makeMemLocal(stack_bytes + (i - 4) * 4);
				}
			}
		}

		ArrayList<EmitFunc.StorageLocation> storage_locations = new ArrayList<>();
		Collections.addAll(storage_locations, assignment);
		return new EmitFunc(stack_bytes, saved_regs, new ArrayList<>(), num_params, storage_locations, env);
	}

	public void emitAsm(PrintStream w, EmitFunc ef, EmitContext ctx, boolean optimize) {
		// emit function prologue
		AsmEmitter.emitPrologue(w, ef);

		// emit all instructions
		for (Instruction inst : insts) {
			inst.emitAsm(w, ef, ctx, optimize);
		}

		// no need to emit function epilogue, since returns are explicit now
		// AsmEmitter.emitEpilogue(w, ef);
	}

	/**
	 * Transforms all `return` to `return 0`, and append `return 0` to the end of the function.
	 */
	public void transformMainFunc() {
		for (int i=0; i!=insts.size(); ++i) {
			final Instruction inst = insts.get(i);
			if (inst instanceof Return) {
				assert(((Return)inst).val.isEmpty());
				insts.set(i, new Return(new IntegerLiteral(0)));
			}
		}
	}

	public void transformReturn(TypeName result_type) {
		if (result_type == TypeName.VOID) {
			if (!insts.isEmpty()) {
				final Instruction inst = insts.get(insts.size() - 1);
				if (!(inst instanceof Return)) {
					insts.add(new Return());
				}
			}
			else {
				insts.add(new Return());
			}
		}
	}

}