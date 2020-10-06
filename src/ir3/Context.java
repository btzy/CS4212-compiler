package ir3;

import java.util.ArrayList;

public class Context {
	private final LocalEnvironment env;
	private final ClassDescriptor this_cd;
	private final ArrayList<ClassDescriptor> cds;

	public Context(LocalEnvironment env, ClassDescriptor this_cd, ArrayList<ClassDescriptor> cds) {
		this.env = env;
		this.this_cd = this_cd;
		this.cds = cds;
	}

	public LocalEnvironment getLocalEnvironment() {
		return env;
	}
}