package ir3;

/**
 * Kind of an enum... but not really, because it contains one per class name.
 */

public class TypeName {
	private static final HashMap<String, TypeName> hm;

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

	public static final TypeName INT;
	public static final TypeName BOOL;
	public static final TypeName STRING;
	public static final TypeName VOID;

	static {
		TypeName.hm = new HashMap<String, TypeName>();
		TypeName.INT = addType("Int");
		TypeName.BOOL = addType("Bool");
		TypeName.STRING = addType("String");
		TypeName.VOID = addType("Void");
	}
}