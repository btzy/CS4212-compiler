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
	private TypeName(String name, LocationRange report_range) { this.name = name; this.report_range = report_range; }

	/**
	 * Returns null if there was an existing value, or the new value otherwise.
	 */
	public static TypeName addType(String name, LocationRange report_range) {
		final TypeName ret = new TypeName(name, report_range);
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

	public static final TypeName INT = addType("Int", null);
	public static final TypeName BOOL = addType("Bool", null);
	public static final TypeName STRING = addType("String", null);
	public static final TypeName VOID = addType("Void", null);
}