package ir3;

/**
 * Represents a Literal with real type (cannot be nullptr_t).
 * In IR3, all terminals are typed.
 */
public abstract class Literal extends Terminal {
	public Literal(TypeName type) { super(type); }
}