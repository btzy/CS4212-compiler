package ir3;

import java.io.PrintStream;

public abstract class Instruction {
	public abstract void print(PrintStream w, PrintContext pc);
}