package ir3;

import util.LocationRange;
import java.util.ArrayList;

public class OverloadResolver {
	/**
	 * Performs overload resolution.
	 * Ignores the first variable in each function, because that will be the type of 'this'.
	 */
	public static int resolveOverload(ArrayList<NullableTypeName> arg_types, ArrayList<Integer> funcidxs, Context ctx, LocationRange call_range, String name, TypeName type) throws SemanticException {
		int num_matches = 0;
		int last_match = -1;
		for (Integer i : funcidxs) {
			if (matches(arg_types, ctx.getFunc(i).param_types)) {
				++num_matches;
				last_match = i;
			}
		}
		if (num_matches == 1) return last_match;
		if (num_matches == 0) throw new NoSuchMemberMethodException(name, type.name, call_range, ctx.lookupField(type, name).map(fe -> ctx.getFieldRange(type, fe.idx)));
		// Prepare the error data
		ArrayList<String> candidates_without_this = new ArrayList<>();
		ArrayList<LocationRange> candidate_ranges = new ArrayList<>();
		for (Integer i : funcidxs) {
			if (matches(arg_types, ctx.getFunc(i).param_types)) {
				candidates_without_this.add(makePrintableSignature(name, ctx.getFunc(i)));
				candidate_ranges.add(ctx.getFuncRange(i));
			}
		}
		// throw the exception
		throw new AmbiguousCallException(makePrintableSignature(name, arg_types), type.name, call_range, candidates_without_this, candidate_ranges);
	}

	private static boolean matches(ArrayList<NullableTypeName> arg_types, ArrayList<TypeName> param_types) {
		if (arg_types.size() + 1 != param_types.size()) return false;
		for (int i=0; i!=arg_types.size(); ++i){
			if (!matches(arg_types.get(i), param_types.get(i+1))) return false;
		}
		return true;
	}

	private static boolean matches(NullableTypeName arg_type, TypeName param_type) {
		if (!arg_type.isNull()) return arg_type.getTypeName() == param_type;
		return param_type == TypeName.STRING || !param_type.isPrimitive();
	}

	private static String makePrintableSignature(String name, FuncSpec spec) {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append('(');
		boolean needComma = false;
		for (int i=1; i<spec.param_types.size(); ++i) { // ignore 'this' parameter
			final TypeName type = spec.param_types.get(i);
			if (needComma) sb.append(',');
			sb.append(type.name);
			needComma = true;
		}
		sb.append(')');
		return sb.toString();
	}

	private static String makePrintableSignature(String name, ArrayList<NullableTypeName> types) {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append('(');
		boolean needComma = false;
		for (NullableTypeName ntype : types) {
			if (needComma) sb.append(',');
			sb.append(ntype.getNullableName());
			needComma = true;
		}
		sb.append(')');
		return sb.toString();
	}
}