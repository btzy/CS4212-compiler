package ir3;

import java.util.ArrayList;
import java.util.HashMap;

public class LocalEnvironment {
	private ArrayList<TypeName> types;
	private HashMap<String, Integer> names;
	private int temporary_count;

	public LocalEnvironment() {
		types = new ArrayList<>();
		names = new HashMap<>();
		temporary_count = 0;
	}

	public void add(TypeName type, String name) {
		Integer i = names.putIfAbsent(name, types.size());
		assert(i == null); // no existing thing
		types.add(type);
	}
}