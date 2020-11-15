package ir3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.OptionalInt;
import util.LocationRange;

public class Context {
	private final LocalEnvironment env;
	private final ClassDescriptor this_cd;
	private final HashMap<TypeName, ClassDescriptor> cd_lookup;
	private ArrayList<FuncSpec> func_specs;
	private ArrayList<LocationRange> func_locations;
	private final TypeName return_type;
	private final LocationRange return_range;
	private int num_labels;

	public Context(LocalEnvironment env, ClassDescriptor this_cd, ArrayList<ClassDescriptor> cds, ArrayList<ir3.FuncSpec> func_specs, ArrayList<LocationRange> func_locations, TypeName return_type, LocationRange return_range) {
		this.env = env;
		this.this_cd = this_cd;
		this.cd_lookup = new HashMap<>();
		for (ClassDescriptor cd : cds) {
			cd_lookup.put(cd.this_type, cd);
		}
		this.func_specs = func_specs;
		this.func_locations = func_locations;
		this.return_type = return_type;
		this.return_range = return_range;
		this.num_labels = 0;
	}

	public LocalEnvironment getLocalEnvironment() {
		return env;
	}

	/**
	 * Represents a single variable (either local or obj variable)
	 */
	public static class Entry {
		public final boolean isLocal; // if true, it is a local idx, otherwise, it is a field idx
		public final int idx;
		public final TypeName type;
		public Entry(boolean isLocal, int idx, TypeName type) { this.isLocal = isLocal; this.idx = idx; this.type = type; }
	}
	public Optional<Entry> lookup(String name) {
		return asOptional(env.lookup(name))
			.map(i -> Optional.of(new Entry(true, i, env.getType(i))))
			.orElseGet(() -> {
				return asOptional(this_cd.lookupField(name))
					.map(i -> Optional.of(new Entry(false, i, this_cd.getFieldType(i))))
					.orElseGet(() -> Optional.empty());
			});
	}
	public LocationRange getEntryRange(Entry entry) {
		if (entry.isLocal) {
			return env.getRange(entry.idx);
		}
		else {
			return this_cd.getFieldRange(entry.idx);
		}
	}

	public static class FieldEntry {
		public final TypeName type;
		public final int idx;
		public FieldEntry(TypeName type, int idx) { this.type = type; this.idx = idx; }
	}
	public Optional<FieldEntry> lookupField(TypeName type, String member) {
		ClassDescriptor cd = cd_lookup.get(type);
		assert (cd != null);
		return asOptional(cd.lookupField(member))
			.map(i -> Optional.of(new FieldEntry(cd.getFieldType(i), i)))
			.orElseGet(() -> Optional.empty());
	}
	public LocationRange getFieldRange(TypeName type, int idx) {
		ClassDescriptor cd = cd_lookup.get(type);
		assert (cd != null);
		return cd.getFieldRange(idx);
	}

	public ArrayList<Integer> lookupMethod(TypeName type, String member) {
		ClassDescriptor cd = cd_lookup.get(type);
		assert (cd != null);
		return cd.lookupMethod(member);
	}

	public ArrayList<Integer> lookupLocalMethod(String member) {
		return this_cd.lookupMethod(member);
	}

	public FuncSpec getFunc(int funcidx) { return func_specs.get(funcidx); }

	public LocationRange getFuncRange(int funcidx) { return func_locations.get(funcidx); }

	private static Optional<Integer> asOptional(OptionalInt oi) {
		if (oi.isEmpty()) return Optional.empty();
		return Optional.of(oi.getAsInt());
	}

	public Label newLabel() {
		return new Label(num_labels++);
	}

	public TypeName thisType() { return this_cd.this_type; }

	public int newLocal(LocationRange virtual_range, TypeName type) {
		return env.newLocal(virtual_range, type);
	}

	public TypeName getReturnType() {
		return return_type;
	}
	public LocationRange getReturnRange() {
		return return_range;
	}
}