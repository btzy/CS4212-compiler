package ir3;

import java.util.ArrayList;
import java.io.PrintStream;

public class FuncSpec {
	public final TypeName result_type;
	public final ArrayList<TypeName> param_types; // includes 'this''
	private final String mangled_name;

	public FuncSpec(TypeName result_type, ArrayList<TypeName> param_types, String mangled_name) {
		this.result_type = result_type;
		this.param_types = param_types;
		this.mangled_name = mangled_name;
	}

	public FuncSpec(TypeName result_type, TypeName class_type, String method_name, ArrayList<TypeName> param_types) {
		this.result_type = result_type;
		this.param_types = param_types;
		StringBuilder sb = new StringBuilder();
		sb.append('%');
		sb.append(class_type.name);
		sb.append('%');
		sb.append(method_name);
		sb.append("%%");
		for (TypeName t : param_types) {
			sb.append('%');
			sb.append(t.name);
		}
		this.mangled_name = sb.toString();
	}

	public String getMangledName() {
		return mangled_name;
	}

	public void print(PrintStream w) {
		w.print(result_type.name);
		w.print(' ');
		w.print(mangled_name);
	}
}