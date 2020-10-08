package ir3;

import java.util.ArrayList;
import java.io.PrintStream;

public class Call extends Instruction {
	public final int idx; // funcidx
	public final ArrayList<Terminal> args;
	public Call(int idx, ArrayList<Terminal> args) { this.idx = idx; this.args = args; }

	@Override
	public void print(PrintStream w, PrintContext pc) {
		w.print("  ");
		w.print(pc.func_specs.get(idx).getMangledName());
		w.print('(');
		boolean needComma = false;
		for (Terminal arg : args) {
			if (needComma) w.print(',');
			arg.print(w, pc);
			needComma = true;
		}
		w.print(')');
		w.println(';');
	}
}