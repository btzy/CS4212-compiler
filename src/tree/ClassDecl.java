package tree;

import java.io.PrintStream;
import java.util.ArrayList;

public class ClassDecl extends Node {
	private String name;
	private ArrayList<VarDecl> vars;
	private ArrayList<Method> methods;

	public static class ClassItemOrderException extends FrontendException {
		ClassItemOrderException(String message) {
			super(message);
		}
	}

	public ClassDecl(String name) {
		this.name = name;
		this.vars = new ArrayList<VarDecl>();
		this.methods = new ArrayList<Method>();
	}
	public ClassDecl(String name, ArrayList<ClassItem> items) throws ClassItemOrderException {
		this(name);
		boolean startMethods = false;
		for (ClassItem c : items) {
			if (!startMethods) {
				if (c instanceof VarDecl) {
					this.vars.add((VarDecl) c);
				}
				else {
					startMethods = true;
					this.methods.add((Method) c);
				}
			}
			else {
				if (c instanceof VarDecl) {
					throw new ClassItemOrderException("VarDecl must come before all methods");
				}
				else {
					this.methods.add((Method) c);
				}
			}
		}
	}
	public void print(PrintStream w) {
		w.print("class ");
		w.print(name);
		w.print("{");
		w.println();
		for (VarDecl v : vars) {
			v.print(w);
		}
		for (Method m : methods) {
			m.print(w);
		}
		w.print("}");
		w.println();
	}

	public static ClassDecl makeMainClass(String name, ArrayList<VarDecl> sig, ArrayList<VarDecl> locals, ArrayList<Stmt> stmts) {
		final ClassDecl ret = new ClassDecl(name);
		final Method m = new Method("Void", "main", sig, locals, stmts);
		ret.methods.add(m);
		return ret;
	}
}