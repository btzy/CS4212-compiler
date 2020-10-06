package ir3;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class descriptor for one class.
 */

public class ClassDescriptor {
	private TypeName this_type;
	private ArrayList<TypeName> field_types;
	private HashMap<String, Integer> field_name_lookup;
	private ArrayList<MethodSpec> method_specs;
	private HashMap<String, ArrayList<Integer>> method_name_lookup; // value is an arraylist because we support overloading

	public ClassDescriptor(TypeName this_type) { this.this_type = this_type; }

	public int addField(TypeName type, String name) {
		final int ret = field_types.size();
		if (field_name_lookup.putIfAbsent(name, ret) != null) {
			// TODO: give the location
			throw new DuplicateClassFieldException(name);
		}
		field_types.add(type);
		return ret;
	}

	public int addMethod(TypeName return_type, String name, ArrayList<TypeName> param_types, ArrayList<String> param_names) {
		final int ret = method_specs.size();
		final ArrayList<Integer> tmp = new ArrayList<Integer>();
		final ArrayList<Integer> old = method_name_lookup.putIfAbsent(name, tmp);
		final ArrayList<Integer> overload_list = old == null ? tmp : old;
		for (Integer idx : overload_list) {
			if (param_types.equals(method_specs.get(idx).param_types)) {
				// existing overload
				throw new DuplicateMethodException(name);
			}
		}
		overload_list.add(ret);
		method_specs.add(new MethodSpec(return_type, this_type, name, param_types/*, param_names*/));
		return ret;
	}

	public ArrayList<MethodSpec> getMethodSpecs() { return this.method_specs; }

	public TypeName getTypeName() { return this.this_type; }
}

