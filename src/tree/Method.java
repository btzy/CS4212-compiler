package tree;

import java.util.ArrayList;

public class Method extends Node implements ClassItem {
	private String type;
	private String name;
	private ArrayList<VarDecl> signature;
	private ArrayList<VarDecl> locals;
	private ArrayList<Stmt> stmts;
	public Method(String type, String name, ArrayList<VarDecl> signature, ArrayList<VarDecl> locals, ArrayList<Stmt> stmts) {
		this.type = type;
		this.name = name;
		this.signature = signature;
		this.locals = locals;
		this.stmts = stmts;
	}
	public void print(NestedPrintStream w) {
		w.println(); // extra println to conform to examples
		w.print(type);
		w.print(' ');
		w.print(name);
		w.print('(');
		Utils.commaSeparatedPrint(signature, w);
		w.print(')');
		w.println('{');
		w.enterContext();
		for (VarDecl v : locals) {
			v.print(w);
			w.println(';');
		}
		for (Stmt s : stmts) {
			s.print(w);
		}
		w.leaveContext();
		w.println('}');
		w.println(); // extra println to conform to examples
	}
}