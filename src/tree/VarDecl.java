package tree;



public class VarDecl extends Node implements ClassItem {
	private String type;
	private String name;
	public VarDecl(String type, String name) { this.type = type; this.name = name; }
	public String getType() { return this.type; }
	public String getName() { return this.name; }
	public void print(NestedPrintStream w) {
		w.print(type);
		w.print(" ");
		w.print(name);
	}
}