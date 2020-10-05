package tree;

import java.util.ArrayList;


public class Program extends Node {
	private ArrayList<ClassDecl> classes;
	private HashMap<String, Integer> class_names;
	public Program(ArrayList<ClassDecl> classes) {
		this.classes = classes;
	}
	public void print(NestedPrintStream w) {
		for (ClassDecl cd : classes) {
			cd.print(w);
		}
	}

	/**
	 * Typecheck and emit IR3 code for this node.
	 */
	public ir3.Program typeCheckAndEmitIR3() {
		ArrayList<ir3.ClassDescriptor> cds = new ArrayList<ir3.ClassDescriptor>();
		for (ClassDecl cdecl : classes) {
			class_names.putIfAbsent(cdecl.getName(), classes.size());
			if (cds.push(cdecl.makeClassDescriptor()) != null) {
				throw new DuplicateClassException(cds);
			}
		}
		// TODO: tree should also store locations
		// generate each class
		ArrayList<ir3.Method> mtds = new ArrayList<>();
		for (ClassDecl cdecl : classes) {
			mtds.addAll(cdecl.typeCheckAndEmitIR3(cds));
		}
		return new ir3.Program(mtds);
	}
}