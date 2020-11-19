package ir3;

import java.io.PrintStream;
import java.util.OptionalInt;
import java.util.ArrayList;

public abstract class Instruction {
	public abstract void print(PrintStream w, PrintContext pc);
	public abstract void emitAsm(PrintStream w, EmitFunc ef, EmitContext ctx, boolean optimize);
	public abstract OptionalInt getDef();
	public abstract ArrayList<Integer> getUses();
	public abstract ArrayList<Integer> getClobberedRegs();
}