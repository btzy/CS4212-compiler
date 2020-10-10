package tree;


import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import util.LocationRange;
import ir3.SemanticException;

public class ClassDecl extends Node {
	private LocationRange report_range; // the range that just says "class <name>"
	private final String name;
	private ArrayList<VarDecl> vars;
	private ArrayList<Method> methods;

	public static class ClassItemOrderException extends FrontendException {
		ClassItemOrderException(String message) {
			super(message);
		}
	}

	public ClassDecl(LocationRange range, LocationRange report_range, String name) {
		super(range);
		this.report_range = report_range;
		this.name = name;
		this.vars = new ArrayList<VarDecl>();
		this.methods = new ArrayList<Method>();
	}
	public ClassDecl(LocationRange range, LocationRange report_range, String name, ArrayList<ClassItem> items) throws ClassItemOrderException {
		this(range, report_range, name);
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

	public static ClassDecl makeMainClass(
			LocationRange range,
			LocationRange report_range,
			LocationRange main_func_range,
			LocationRange main_func_declaration_range,
			LocationRange main_func_type_range,
			LocationRange main_func_name_range,
			String name,
			ArrayList<VarDecl> sig,
			ArrayList<VarDecl> locals,
			ArrayList<Stmt> stmts) {
		final ClassDecl ret = new ClassDecl(range, report_range, name);
		final Method m = new Method(
			main_func_range, main_func_declaration_range, main_func_type_range, main_func_name_range, 
			"Void", "main", sig, locals, stmts);
		ret.methods.add(m);
		return ret;
	}

	public String getName() { return name; }

	public void addThisType() throws ir3.SemanticException {
		final ir3.TypeName this_type = ir3.TypeName.addType(name, report_range);
		if (this_type == null) throw new ir3.DuplicateClassDeclException(name, report_range, ir3.TypeName.getType(name).report_range);
	}

	public ir3.ClassDescriptor makeClassDescriptor(ArrayList<ir3.FuncSpec> out_func_specs, ArrayList<LocationRange> out_func_locations, boolean isMainClass) throws ir3.SemanticException, ir3.SilentException {
		final ir3.TypeName this_type = ir3.TypeName.getType(name);
		if (this_type == null) throw new ir3.SilentException(); // if we throw, it means that 'addThisType' failed
		final ir3.ClassDescriptor ret = new ir3.ClassDescriptor(this_type);
		for (VarDecl vdecl : vars) {
			SemanticException.bound(() -> {
				final ir3.TypeName type = ir3.TypeName.getType(vdecl.getType());
				if (type == null) throw new ir3.NoSuchTypeException(vdecl.getType(), vdecl.getTypeRange());
				if (type == ir3.TypeName.VOID) throw new ir3.VoidTypeException(vdecl.getTypeRange());
				ret.addField(vdecl.range, type, vdecl.getName());
			});
		}
		for (Method mtd : methods) {
			SemanticException.bound(() -> {
				final ir3.TypeName return_type = ir3.TypeName.getType(mtd.getType());
				if (return_type == null) throw new ir3.NoSuchTypeException(mtd.getType(), mtd.getTypeRange());
				ArrayList<ir3.TypeName> param_types = new ArrayList<>();
				param_types.add(this_type); // the 'this' parameter
				for (VarDecl vdecl : mtd.getSignature()) {
					SemanticException.bound(() -> {
						final ir3.TypeName ptype = ir3.TypeName.getType(vdecl.getType());
						if (ptype == null) throw new ir3.NoSuchTypeException(vdecl.getType(), vdecl.getTypeRange());
						if (ptype == ir3.TypeName.VOID) throw new ir3.VoidTypeException(vdecl.getTypeRange());
						param_types.add(ptype);
					});
				}
				final int funcidx = out_func_specs.size();
				final ir3.FuncSpec fspec = isMainClass
					? new ir3.FuncSpec(return_type, param_types, mtd.getName())
					: new ir3.FuncSpec(return_type, this_type, mtd.getName(), param_types);
				out_func_specs.add(fspec);
				out_func_locations.add(mtd.getDeclarationRange());
				ret.addMethod(mtd.getDeclarationRange(), out_func_specs, out_func_locations, funcidx, mtd.getName(), param_types);
			});
		}
		return ret;
	}
	
	/**
	 * Typecheck and emit IR3 code for this node.
	 */
	public ArrayList<ir3.FuncBody> typeCheckAndEmitIR3(ir3.ClassDescriptor this_ctx, ArrayList<ir3.ClassDescriptor> cds, ArrayList<ir3.FuncSpec> func_specs, ArrayList<LocationRange> func_locations) {
		ArrayList<ir3.FuncBody> ret = new ArrayList<ir3.FuncBody>();
		for (Method method : methods) {
			ir3.SemanticException.bound(() -> {
				ret.add(method.typeCheckAndEmitIR3(this_ctx, cds, func_specs, func_locations));
			});
		}
		return ret;
	}
}