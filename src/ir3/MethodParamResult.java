public class MethodParamResult {
	public final TypeName result;
	public final ArrayList<TypeName> param_types;
	public final ArrayList<String> param_names;

	public MethodParamResult(TypeName result, ArrayList<TypeName> param_types, ArrayList<String> param_names) {
		this.result = result;
		this.param_types = param_types;
		this.param_names = param_names;
	}
}