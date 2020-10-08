package ir3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.OptionalInt;
import java.io.PrintStream;
import util.LocationRange;
import ir3.OverloadResolver;

/**
 * Class descriptor for one class.
 */

public class ClassDescriptor {
	public final TypeName this_type;
	private ArrayList<TypeName> field_types;
	private ArrayList<LocationRange> field_locations;
	private ArrayList<String> field_names;
	private HashMap<String, Integer> field_name_lookup;
	private HashMap<String, ArrayList<Integer>> method_name_lookup; // value is an arraylist because we support overloading

	public ClassDescriptor(TypeName this_type) {
		this.this_type = this_type;
		this.field_types = new ArrayList<>();
		this.field_locations = new ArrayList<>();
		this.field_names = new ArrayList<>();
		this.field_name_lookup = new HashMap<>();
		this.method_name_lookup = new HashMap<>();
	}

	public int addField(LocationRange range, TypeName type, String name) throws SemanticException {
		final int ret = field_types.size();
		if (field_name_lookup.putIfAbsent(name, ret) != null) {
			throw new DuplicateClassFieldException(name, this_type.name, range, field_locations.get(field_name_lookup.get(name)));
		}
		field_types.add(type);
		field_locations.add(range);
		field_names.add(name);
		return ret;
	}

	public void addMethod(LocationRange range /* the whole signature */, ArrayList<ir3.FuncSpec> func_specs, ArrayList<LocationRange> func_locations, int funcidx, String name, ArrayList<TypeName> param_types) throws SemanticException {
		final ArrayList<Integer> tmp = new ArrayList<Integer>();
		final ArrayList<Integer> old = method_name_lookup.putIfAbsent(name, tmp);
		final ArrayList<Integer> overload_list = old == null ? tmp : old;
		for (Integer idx : overload_list) {
			if (param_types.equals(func_specs.get(idx).param_types)) {
				// existing overload
				throw new DuplicateMethodException(OverloadResolver.makePrintableSignatureRemovingThis(name, param_types), this_type.name, range, func_locations.get(idx));
			}
		}
		overload_list.add(funcidx);
	}

	public TypeName getTypeName() { return this.this_type; }

	public OptionalInt lookupField(String name) {
		Integer i = field_name_lookup.get(name);
		if (i == null) return OptionalInt.empty();
		return OptionalInt.of(i);
	}

	public ArrayList<Integer> lookupMethod(String name) {
		final ArrayList<Integer> ret = method_name_lookup.get(name);
		return ret != null ? ret : new ArrayList<>();
	}

	public TypeName getFieldType(int idx) {
		return field_types.get(idx);
	}

	public String getFieldName(int idx) {
		return field_names.get(idx);
	}

	public LocationRange getFieldRange(int idx) {
		return field_locations.get(idx);
	}

	public void print(PrintStream w) {
		w.print("class ");
		w.print(this_type.name);
		w.println('{');
		final int num_fields = field_types.size();
		for (int i=0; i!=num_fields; ++i) {
			w.print("  ");
			w.print(field_types.get(i).name);
			w.print(' ');
			w.print(field_names.get(i));
			w.println(';');
		}
		w.println('}');
	}
}

