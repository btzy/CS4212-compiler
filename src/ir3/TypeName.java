package ir3;

import java.util.HashMap;

/**
 * Kind of an enum... but not really, because it contains one per class name.
 */

public class TypeName {
	private static final HashMap<String, TypeName> hm = new HashMap<String, TypeName>();

	public final String name;
	private TypeName(String name) { this.name = name; }

	/**
	 * Returns null if there was an existing value, or the new value otherwise.
	 */
	public static TypeName addType(String name) {
		final TypeName ret = new TypeName(name);
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

	public static final TypeName INT = addType("Int");
	public static final TypeName BOOL = addType("Bool");
	public static final TypeName STRING = addType("String");
	public static final TypeName VOID = addType("Void");
}