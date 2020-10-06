package tree;

import java.util.function.Consumer;
import ir3.Context;

import util.LocationRange;

public abstract class Stmt extends Node {
	public Stmt(LocationRange range) { super(range); }

	public void typeCheckAndEmitIR3(Context ctx, Consumer<? super ir3.Instruction> out) {
		// TODO: make this function abstract
	}
}