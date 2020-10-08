package ir3;

import java.util.ArrayList;
import java.io.PrintStream;

public class CallExpr extends Expr {
	public final int idx; // funcidx
	public final ArrayList<Terminal> args;
	public CallExpr(TypeName result_type, int idx, ArrayList<Terminal> args) { super(result_type); this.idx = idx; this.args = args; }

	@Override
	public void print(PrintStream w, PrintContext pc) {
		w.print(pc.func_specs.get(idx).getMangledName());
		w.print('(');
		boolean needComma = false;
		for (Terminal arg : args) {
			if (needComma) w.print(',');
			arg.print(w, pc);
			needComma = true;
		}
		w.print(')');
	}
}