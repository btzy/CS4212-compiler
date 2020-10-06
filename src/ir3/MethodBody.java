package ir3;

import java.util.ArrayList;

public class MethodBody {
	private final LocalEnvironment env;
	private final int num_params;
	private final ArrayList<Instruction> insts;

	public MethodBody(LocalEnvironment env, int num_params, ArrayList<Instruction> insts) {
		this.env = env;
		this.num_params = num_params;
		this.insts = insts;
	}
}