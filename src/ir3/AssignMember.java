package ir3;

public class AssignMember extends Instruction {
	public final int idx; // localidx
	public final int field; // fieldidx
	public final Expr val;
	public AssignMember(int idx, int field, Expr val) { this.idx = idx; this.field = field; this.val = val; }
}