package tree;

import util.LocationRange;

public abstract class Expr extends Node {
	public Expr(LocationRange range) { super(range); }
}