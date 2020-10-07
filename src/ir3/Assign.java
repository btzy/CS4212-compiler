package ir3;

public class Assign extends Instruction {
	public final int idx; // localidx
	public final Expr val;
	public Assign(int idx, Expr val) { this.idx = idx; this.val = val; }
}