package ir3;

import java.util.Optional;

public class NullableTypeName {
	private final TypeName inner; // null to represent null

	private NullableTypeName(TypeName inner) { this.inner = inner; }

	public static NullableTypeName of(TypeName type) { assert(type != null); return new NullableTypeName(type); }
	public static NullableTypeName nullValue() { return new NullableTypeName(null); }

	public boolean isNull() { return inner == null; }
	public TypeName getTypeName() { assert(inner != null); return inner; }
	public String getNullableName() { return inner != null ? inner.name : "null"; }

	public Optional<TypeName> asOptional() { return Optional.ofNullable(inner); }
}