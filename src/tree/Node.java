package tree;

import util.LocationRange;

public abstract class Node implements Printable {
	public final LocationRange range;

	public Node(LocationRange range) { this.range = range; }
}