package ir3;

import java.util.ArrayList;

public class FuncBody {
	private final LocalEnvironment env;
	private final int num_params;
	private final ArrayList<Instruction> insts;

	public FuncBody(LocalEnvironment env, int num_params, ArrayList<Instruction> insts) {
		this.env = env;
		this.num_params = num_params;
		this.insts = insts;
	}
}