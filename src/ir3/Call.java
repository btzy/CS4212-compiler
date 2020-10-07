package ir3;

import java.util.ArrayList;

public class Call extends Instruction {
	public final int idx; // funcidx
	public final ArrayList<Terminal> args;
	public Call(int idx, ArrayList<Terminal> args) { this.idx = idx; this.args = args; }
}