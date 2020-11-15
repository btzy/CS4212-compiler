package ir3;

import java.util.ArrayList;
import java.io.PrintStream;

public class Program {
	private final ArrayList<ClassDescriptor> cds;
	private final ArrayList<FuncSpec> func_specs;
	private final ArrayList<FuncBody> func_bodies;

	public Program(ArrayList<ClassDescriptor> cds, ArrayList<FuncSpec> func_specs, ArrayList<FuncBody> func_bodies) {
		this.cds = cds;
		this.func_specs = func_specs;
		this.func_bodies = func_bodies;
		assert(func_specs.size() == func_bodies.size());
	}

	public void print(PrintStream w) {
		w.println("======= CData3 =======");
		w.println();
		for (ClassDescriptor cd : cds) {
			cd.print(w);
			w.println();
		}
		w.println("=======  CMtd3 =======");
		w.println();
		final int num_funcs = func_specs.size();
		for (int i=0; i!=num_funcs; ++i) {
			func_specs.get(i).print(w);
			func_bodies.get(i).print(w, func_specs, cds);
			w.println();
		}
		w.println("======= End of IR3 Program =======");
	}

	/**
	 * Actually compile this program into ARM assembly.
	 * `optimize` - whether to use optimisations.
	 */
	public void compile(PrintStream w, boolean optimize) {
		// Generate offsets for each class field:
		ArrayList<EmitClass> class_layouts = new ArrayList<>();
		for (ClassDescriptor cd : cds) {
			class_layouts.add(cd.generateLayout());
		}

		// storage for context (e.g. .data section)
		EmitContext ctx = new EmitContext(func_specs, class_layouts);

		// emit pre-code
		ctx.emitPreCode(w);

		// Emit ARM assembly
		for (FuncBody fb : func_bodies) {
			// Determine where to place each variable
			EmitFunc ef = fb.determineStorage(optimize);
			// Emit asm code
			fb.emitAsm(w, ef, ctx, optimize);
		}

		// emit post-code (e.g. string literals)
		ctx.emitPostCode(w);
	}
}