package ir3;

public class Goto extends Instruction {
	public final Label target;
	public Goto(Label target) { this.target = target; }
}