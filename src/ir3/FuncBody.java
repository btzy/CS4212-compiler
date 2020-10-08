package ir3;

import java.util.ArrayList;
import java.io.PrintStream;

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
}