package ir3;

import java.util.HashMap;
import util.LocationRange;

/**
 * Kind of an enum... but not really, because it contains one per class name.
 */

public class TypeName {
	private static final HashMap<String, TypeName> hm = new HashMap<String, TypeName>();

	public final String name;
	public final LocationRange report_range;
	public final int size;
	public final int alignment;
	private TypeName(String name, LocationRange report_range, int size, int alignment) { this.name = name; this.report_range = report_range; this.size = size; this.alignment = alignment; }

	/**
	 * Returns null if there was an existing value, or the new value otherwise.
	 */
	public static TypeName addType(String name, LocationRange report_range) {
		return addType(name, report_range, 4, 4); // objects are always 4-byte size and alignment
	}
	private static TypeName addType(String name, LocationRange report_range, int size, int alignment) {
		final TypeName ret = new TypeName(name, report_range, size, alignment);
		TypeName tn = hm.putIfAbsent(name, ret);
		if (tn != null) {
			// has existing value
			return null;
		}
		else {
			return ret;
		}
	}
	public static TypeName getType(String name) {
		return hm.get(name);
	}
	public boolean isPrimitive() {
		return this == INT || this == BOOL || this == STRING || this == VOID;
	}

	@Override
	public String toString() { return name; }

	public static final TypeName INT = addType("Int", null, 4, 4);
	public static final TypeName BOOL = addType("Bool", null, 1, 1);
	public static final TypeName STRING = addType("String", null, 4, 4);
	public static final TypeName VOID = addType("Void", null, 0, 1);
}