package tree;

import java.util.ArrayList;

public class Program extends Node {
	private ArrayList<ClassDecl> classes;
	public Program(ArrayList<ClassDecl> classes) {
		this.classes = classes;
	}
	public void print(NestedPrintStream w) {
		for (ClassDecl cd : classes) {
			cd.print(w);
		}
	}
}