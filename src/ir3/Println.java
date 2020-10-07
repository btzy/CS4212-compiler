package ir3;

public class Println extends Instruction {
	public final Terminal term;
	public Println(Terminal term) { this.term = term; }
}