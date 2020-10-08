package ir3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.OptionalInt;
import util.LocationRange;

public class LocalEnvironment {
	private ArrayList<TypeName> types;
	private ArrayList<LocationRange> report_ranges;
	private ArrayList<String> names;
	private HashMap<String, Integer> name_lookup;
	private int temporary_count;

	public LocalEnvironment() {
		types = new ArrayList<>();
		report_ranges = new ArrayList<>();
		names = new ArrayList<>();
		name_lookup = new HashMap<>();
		temporary_count = 0;
	}

	public void add(LocationRange range, TypeName type, String name) throws SemanticException {
		Integer i = name_lookup.putIfAbsent(name, types.size());
		if (i != null) throw new DuplicateLocalVariableException(name, range, report_ranges.get(name_lookup.get(name)));
		types.add(type);
		report_ranges.add(range);
		names.add(name);
	}

	public OptionalInt lookup(String name) {
		Integer i = name_lookup.get(name);
		if (i == null) return OptionalInt.empty();
		return OptionalInt.of(i);
	}

	public int thisIndex() {
		return 0;
	}

	public TypeName getType(int idx) {
		return types.get(idx);
	}

	public String getName(int idx) {
		return names.get(idx);
	}

	public LocationRange getRange(int idx) {
		return report_ranges.get(idx);
	}

	public int size() {
		return types.size();
	}

	public int newLocal(LocationRange virtual_range, TypeName type) {
		final int res = types.size();
		final String name = "_t" + String.valueOf(++temporary_count);
		types.add(type);
		report_ranges.add(virtual_range);
		names.add(name);
		return res;
	}
}