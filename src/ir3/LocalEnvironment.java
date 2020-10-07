package ir3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.OptionalInt;
import util.LocationRange;

public class LocalEnvironment {
	private ArrayList<TypeName> types;
	private ArrayList<LocationRange> report_ranges;
	private HashMap<String, Integer> names;
	private int temporary_count;

	public LocalEnvironment() {
		types = new ArrayList<>();
		report_ranges = new ArrayList<>();
		names = new HashMap<>();
		temporary_count = 0;
	}

	public void add(LocationRange range, TypeName type, String name) throws SemanticException {
		Integer i = names.putIfAbsent(name, types.size());
		if (i != null) throw new DuplicateLocalVariableException(name, range, report_ranges.get(names.get(name)));
		types.add(type);
		report_ranges.add(range);
	}

	public OptionalInt lookup(String name) {
		Integer i = names.get(name);
		if (i == null) return OptionalInt.empty();
		return OptionalInt.of(i);
	}

	public int thisIndex() {
		return 0;
	}

	public TypeName getType(int idx) {
		return types.get(idx);
	}

	public int newLocal(LocationRange virtual_range, TypeName type) {
		int res = types.size();
		String name = "_t" + String.valueOf(++temporary_count);
		types.add(type);
		report_ranges.add(virtual_range);
		return res;
	}
}