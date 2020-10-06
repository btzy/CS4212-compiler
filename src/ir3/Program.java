package ir3;

import java.util.ArrayList;

public class Program {
	private final ArrayList<ClassDescriptor> cds;
	private final ArrayList<MethodBody> mtd_bodies;
	private final ArrayList<String> mtd_names; // mangled names

	public Program(ArrayList<ClassDescriptor> cds, ArrayList<MethodBody> mtd_bodies, ArrayList<String> mtd_names) {
		this.cds = cds;
		this.mtd_bodies = mtd_bodies;
		this.mtd_names = mtd_names;
	}
}