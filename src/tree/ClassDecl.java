package tree;


import java.util.ArrayList;

public class ClassDecl extends Node {
	private final String name;
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
	public void print(NestedPrintStream w) {
		w.print("class ");
		w.print(name);
		w.print("{");
		w.enterContext();
		w.println();
		for (VarDecl v : vars) {
			v.print(w);
			w.println(';');
		}
		boolean old = !vars.isEmpty();
		for (Method m : methods) {
			if (old) w.println();
			m.print(w);
			old = true;
		}
		w.leaveContext();
		w.print("}");
		w.println();
		w.println(); // extra println to conform to examples
	}

	public static ClassDecl makeMainClass(String name, ArrayList<VarDecl> sig, ArrayList<VarDecl> locals, ArrayList<Stmt> stmts) {
		final ClassDecl ret = new ClassDecl(name);
		final Method m = new Method("Void", "main", sig, locals, stmts);
		ret.methods.add(m);
		return ret;
	}

	public String getName() { return name; }

	public ir3.ClassDescriptor makeClassDescriptor() {
		final ir3.ClassDescriptor ret = new ir3.ClassDescriptor();
		for (VarDecl vdecl : vars) {
			final TypeName type = ir3.TypeName.getType(vdecl.getType());
			if (type == null) throw new NoSuchTypeException(vdecl.getType());
			ret.addField(type, vdecl.getName());
		}
		for (Method mtd : methods) {
			final TypeName type = ir3.TypeName.getType(mtd.getType());
			if (type == null) throw new NoSuchTypeException(mtd.getType());
			ArrayList<TypeName> param_types = new ArrayList<>();
			ArrayList<String> param_names = new ArrayList<>();
			for (VarDecl vdecl : mtd.getSignature()) {
				final TypeName ptype = ir3.TypeName.getType(vdecl.getType());
				if (ptype == null) throw new NoSuchTypeException(vdecl.getType());
				param_types.add(ptype);
				param_names.add(vdecl.getName());
			}
			ret.addMethod(type, mtd.getName(), param_types, param_names);
		}
		return ret;
	}
	
	/**
	 * Typecheck and emit IR3 code for this node.
	 */
	public ArrayList<ir3.Method> typeCheckAndEmitIR3(ArrayList<ir3.ClassDescriptor> cds) {
		// TODO
	}
}