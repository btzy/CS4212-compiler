package tree;

import java.util.ArrayList;
import java.io.PrintStream;

public class Program extends Node {
	private ArrayList<ClassDecl> classes;
	public Program(ArrayList<ClassDecl> classes) {
		this.classes = classes;
	}
	public void print(PrintStream w) {
		for (ClassDecl cd : classes) {
			cd.print(w);
		}
	}
}