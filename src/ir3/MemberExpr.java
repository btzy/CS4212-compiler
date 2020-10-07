package ir3;

public class MemberExpr extends Expr {
	public final int idx; // localidx
	public final int field; // fieldidx
	public MemberExpr(TypeName type, int idx, int field) { super(type); this.idx = idx; this.field = field; }
}