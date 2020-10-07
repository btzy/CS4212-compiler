package tree;

import util.LocationRange;
import java.util.function.Consumer;
import ir3.Context;
import ir3.SemanticException;

public abstract class Stmt extends Node {
	public Stmt(LocationRange range) { super(range); }

	public abstract void typeCheckAndEmitIR3(Context ctx, Consumer<? super ir3.Instruction> out) throws SemanticException;
}