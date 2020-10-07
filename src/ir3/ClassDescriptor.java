package ir3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.OptionalInt;
import util.LocationRange;

/**
 * Class descriptor for one class.
 */

public class ClassDescriptor {
	public final TypeName this_type;
	private ArrayList<TypeName> field_types;
	private ArrayList<LocationRange> field_locations;
	private HashMap<String, Integer> field_name_lookup;
	private ArrayList<MethodSpec> method_specs; // TODO: maybe all methods should be combined to outside the class descriptor?
	private ArrayList<LocationRange> method_locations;
	private HashMap<String, ArrayList<Integer>> method_name_lookup; // value is an arraylist because we support overloading

	public ClassDescriptor(TypeName this_type) {
		this.this_type = this_type;
		this.field_types = new ArrayList<>();
		this.field_locations = new ArrayList<>();
		this.field_name_lookup = new HashMap<>();
		this.method_specs = new ArrayList<>();
		this.method_locations = new ArrayList<>();
		this.method_name_lookup = new HashMap<>();
	}

	public int addField(LocationRange range, TypeName type, String name) throws SemanticException {
		final int ret = field_types.size();
		if (field_name_lookup.putIfAbsent(name, ret) != null) {
			// TODO: give the location
			throw new DuplicateClassFieldException(this_type.name, name, range, field_locations.get(field_name_lookup.get(name)));
		}
		field_types.add(type);
		field_locations.add(range);
		return ret;
	}

	public int addMethod(LocationRange range /* the whole signature */, TypeName return_type, String name, ArrayList<TypeName> param_types, ArrayList<String> param_names) throws SemanticException {
		final int ret = method_specs.size();
		final ArrayList<Integer> tmp = new ArrayList<Integer>();
		final ArrayList<Integer> old = method_name_lookup.putIfAbsent(name, tmp);
		final ArrayList<Integer> overload_list = old == null ? tmp : old;
		for (Integer idx : overload_list) {
			if (param_types.equals(method_specs.get(idx).param_types)) {
				// existing overload
				throw new DuplicateMethodException(this_type.name, name, range, method_locations.get(idx));
			}
		}
		overload_list.add(ret);
		method_specs.add(new MethodSpec(return_type, this_type, name, param_types/*, param_names*/));
		method_locations.add(range);
		return ret;
	}

	public ArrayList<MethodSpec> getMethodSpecs() { return this.method_specs; }

	public TypeName getTypeName() { return this.this_type; }

	public OptionalInt lookupField(String name) {
		Integer i = field_name_lookup.get(name);
		if (i == null) return OptionalInt.empty();
		return OptionalInt.of(i);
	}

	public TypeName getFieldType(int idx) {
		return field_types.get(idx);
	}
}

