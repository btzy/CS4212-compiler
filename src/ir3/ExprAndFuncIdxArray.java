package ir3;

import util.LocationRange;
import ir3.Context;
import ir3.SemanticException;
import java.util.ArrayList;
import java.util.function.Consumer;

public class ExprAndFuncIdxArray {
	public final Expr expr;
	public final ArrayList<Integer> idxs;
	public final TypeName class_name;
	public final String method_name;
	public ExprAndFuncIdxArray(Expr expr, ArrayList<Integer> idxs, TypeName class_name, String method_name) { this.expr = expr; this.idxs = idxs; this.class_name = class_name; this.method_name = method_name; }
}