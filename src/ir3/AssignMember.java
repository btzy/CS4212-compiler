package ir3;

import java.io.PrintStream;

public class AssignMember extends Instruction {
	public final int idx; // localidx
	public final int field; // fieldidx
	public final Expr val;
	public AssignMember(int idx, int field, Expr val) { this.idx = idx; this.field = field; this.val = val; }

	@Override
	public void print(PrintStream w, PrintContext pc) {
		w.print("  ");
		w.print(pc.env.getName(idx));
		w.print('.');
		w.print(pc.getClassDescriptor(pc.env.getType(idx)).getFieldName(field));
		w.print(" = ");
		val.print(w, pc);
		w.println(';');
	}
}