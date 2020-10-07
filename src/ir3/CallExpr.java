package ir3;

import java.util.ArrayList;

public class CallExpr extends Expr {
	public final int idx; // funcidx
	public final ArrayList<Terminal> args;
	public CallExpr(TypeName result_type, int idx, ArrayList<Terminal> args) { super(result_type); this.idx = idx; this.args = args; }
}