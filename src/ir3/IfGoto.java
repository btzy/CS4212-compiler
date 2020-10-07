package ir3;

public class IfGoto extends Instruction {
	public final Expr cond;
	public final Label target;
	public IfGoto(Expr cond, Label target) { this.cond = cond; this.target = target; }
}