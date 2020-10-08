package ir3;

import java.io.PrintStream;

public class MemberExpr extends Expr {
	public final int idx; // localidx
	public final int field; // fieldidx
	public MemberExpr(TypeName type, int idx, int field) { super(type); this.idx = idx; this.field = field; }

	@Override
	public void print(PrintStream w, PrintContext pc) {
		w.print(pc.env.getName(idx));
		w.print('.');
		w.print(pc.getClassDescriptor(pc.env.getType(idx)).getFieldName(field));
	}
}