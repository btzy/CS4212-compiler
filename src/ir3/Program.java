package ir3;

import java.util.ArrayList;

public class Program {
	private final ArrayList<ClassDescriptor> cds;
	private final ArrayList<FuncSpec> func_specs;
	private final ArrayList<FuncBody> func_bodies;

	public Program(ArrayList<ClassDescriptor> cds, ArrayList<FuncSpec> func_specs, ArrayList<FuncBody> func_bodies) {
		this.cds = cds;
		this.func_specs = func_specs;
		this.func_bodies = func_bodies;
	}
}