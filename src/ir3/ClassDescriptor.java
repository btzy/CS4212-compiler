package ir3;


/**
 * Class descriptor for one class.
 */

public class ClassDescriptor {
	private ArrayList<TypeName> field_types;
	private HashMap<String, Integer> field_name_lookup;
	private ArrayList<MethodParamResult> method_param_results;
	private HashMap<String, ArrayList<Integer>> method_name_lookup; // value is an arraylist because we support overloading

	public ClassDescriptor() {}

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
		final int ret = method_param_results.size();
		final ArrayList<Integer> tmp = new ArrayList<Integer>();
		final ArrayList<Integer> old = method_name_lookup.putIfAbsent(name, tmp);
		final ArrayList<Integer> overload_list = old == null ? tmp : old;
		for (Integer idx : overload_list) {
			if (param_types.equals(method_param_results[idx].param_types)) {
				// existing overload
				throw new DuplicateMethodException(name);
			}
		}
		overload_list.add(ret);
		method_param_results.add(new MethodParamResult(return_type, param_types, param_names));
		return ret;
	}
}

