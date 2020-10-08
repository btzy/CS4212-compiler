package ir3;

import java.util.ArrayList;
import java.util.HashMap;

public class PrintContext {
	public final LocalEnvironment env;
	public final ArrayList<FuncSpec> func_specs;
	public final HashMap<TypeName, ClassDescriptor> cd_lookup;
	public PrintContext(LocalEnvironment env, ArrayList<FuncSpec> func_specs, ArrayList<ClassDescriptor> cds) {
		this.env = env;
		this.func_specs = func_specs;
		this.cd_lookup = new HashMap<>();
		for (ClassDescriptor cd : cds) {
			cd_lookup.put(cd.this_type, cd);
		}
	}
	public ClassDescriptor getClassDescriptor(TypeName type) {
		return cd_lookup.get(type);
	}
}