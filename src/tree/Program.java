package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import util.LocationRange;


public class Program extends Node {
	private ArrayList<ClassDecl> classes;
	public Program(LocationRange range, ArrayList<ClassDecl> classes) {
		super(range);	
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
	public ir3.Program typeCheckAndEmitIR3() throws ir3.SemanticException {
		ArrayList<ir3.ClassDescriptor> cds = new ArrayList<ir3.ClassDescriptor>();
		for (ClassDecl cdecl : classes) {
			cds.add(cdecl.makeClassDescriptor());
		}
		// TODO: tree should also store locations
		// Note: null == "".  Convert all "" to null.
		// ARMv7
		// generate each class
		ArrayList<ir3.MethodBody> mtds = new ArrayList<>();
		for (int i=0; i!=classes.size(); ++i) {
			mtds.addAll(classes.get(i).typeCheckAndEmitIR3(cds.get(i), cds));
		}
		return new ir3.Program(cds, mtds,
			cds
				.stream()
				.flatMap(cd -> cd.getMethodSpecs().stream().map(ir3.MethodSpec::getMangledName))
				.collect(Collectors.toCollection(ArrayList::new))
		);
	}
}