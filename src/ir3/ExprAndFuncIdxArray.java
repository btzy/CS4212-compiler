package ir3;

import util.LocationRange;
import ir3.Context;
import ir3.SemanticException;
import java.util.ArrayList;
import java.util.function.Consumer;

public class ExprAndFuncIdxArray {
	public final Expr expr;
	public final ArrayList<Integer> idxs;
	public ExprAndFuncIdxArray(Expr expr, ArrayList<Integer> idxs) { this.expr = expr; this.idxs = idxs; }
}