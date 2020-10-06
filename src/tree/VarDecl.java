package tree;

import util.LocationRange;

public class VarDecl extends Node implements ClassItem {
	private String type;
	private String name;
	private LocationRange type_range;
	private LocationRange name_range;
	public VarDecl(LocationRange range, LocationRange type_range, LocationRange name_range, String type, String name) {
		super(range); this.type_range = type_range; this.name_range = name_range; this.type = type; this.name = name;
	}
	public String getType() { return this.type; }
	public String getName() { return this.name; }
	public LocationRange getTypeRange() { return this.type_range; }
	public LocationRange getNameRange() { return this.name_range; }
	public void print(NestedPrintStream w) {
		w.print(type);
		w.print(" ");
		w.print(name);
	}
}