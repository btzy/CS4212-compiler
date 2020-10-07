package ir3;

import java.util.Optional;

/**
 * Represents a Expr that could be null.
 */
public class NullableExpr {
	private final Expr inner; // null to represent null

	private NullableExpr(Expr inner) { this.inner = inner; }

	public static NullableExpr of(Expr expr) { assert(expr != null); return new NullableExpr(expr); }
	public static NullableExpr nullValue() { return new NullableExpr(null); }

	public Optional<Expr> imbueType(TypeName type) {
		if (inner != null) {
			if (inner.type == type) return Optional.of(inner);
			return Optional.empty();
		}
		if (type == TypeName.STRING) return Optional.of(new StringLiteral(""));
		if (!type.isPrimitive()) return Optional.of(new NullLiteral(type));
	}

	public Optional<Expr> fixType() {
		return Optional.ofNullable(inner);
	}

	public NullableTypeName getType() {
		if (inner == null) return NullableTypeName.nullValue();
		return NullableTypeName.of(inner.type);
	}
}