package ir3;

import java.util.ArrayList;

public class MethodSpec {
	public final TypeName result;
	public final TypeName class_type;
	public final String method_name;
	public final ArrayList<TypeName> param_types;
	public final int index; // TODO;
	//public final ArrayList<String> param_names;

	public MethodSpec(TypeName result, TypeName class_type, String method_name, ArrayList<TypeName> param_types/*, ArrayList<String> param_names*/) {
		this.result = result;
		this.class_type = class_type;
		this.method_name = method_name;
		this.param_types = param_types;
		//this.param_names = param_names;
	}

	public String getMangledName() {
		StringBuilder sb = new StringBuilder();
		for (TypeName t : param_types) {
			sb.append(t.name);
			sb.append('%');
		}
		return class_type.name + '%' + method_name + "%%" + sb.toString();
	}
}