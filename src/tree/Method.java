package tree;

import java.util.ArrayList;
import ir3.Context;
import util.LocationRange;

public class Method extends Node implements ClassItem {
	private String type;
	private String name;
	private ArrayList<VarDecl> signature;
	private ArrayList<VarDecl> locals;
	private ArrayList<Stmt> stmts;
	public Method(LocationRange range, String type, String name, ArrayList<VarDecl> signature, ArrayList<VarDecl> locals, ArrayList<Stmt> stmts) {
		super(range);	
		this.type = type;
		this.name = name;
		this.signature = signature;
		this.locals = locals;
		this.stmts = stmts;
	}
	public String getType() { return this.type; }
	public String getName() { return this.name; }
	public ArrayList<VarDecl> getSignature() { return this.signature; }
	public void print(NestedPrintStream w) {
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
	}
	
	/**
	 * Typecheck and emit IR3 code for this node.
	 */
	public ir3.MethodBody typeCheckAndEmitIR3(ir3.ClassDescriptor this_ctx, ArrayList<ir3.ClassDescriptor> cds) {
		final ir3.LocalEnvironment env = new ir3.LocalEnvironment();
		// add 'this'
		env.add(this_ctx.getTypeName(), "this");
		// add all the params
		for (VarDecl vdecl : signature) {
			env.add(ir3.TypeName.getType(vdecl.getType()), vdecl.getName());
		}
		// add all the locals
		for (VarDecl vdecl : locals) {
			env.add(ir3.TypeName.getType(vdecl.getType()), vdecl.getName());
		}
		final Context ctx = new Context(env, this_ctx, cds);
		final ArrayList<ir3.Instruction> insts = new ArrayList<>();
		for (Stmt s : stmts) {
			s.typeCheckAndEmitIR3(ctx, insts::add);
		}
		return new ir3.MethodBody(ctx.getLocalEnvironment(), signature.size() + 1, insts);
	}
}