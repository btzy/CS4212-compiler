package ir3;

import util.LocationRange;
import java.util.ArrayList;

public class OverloadResolver {
	/**
	 * Performs overload resolution.
	 * Ignores the first variable in each function, because that will be the type of 'this'.
	 */
	public static int resolveOverload(LocationRange range, Context ctx, ArrayList<NullableTypeName> arg_types, ArrayList<Integer> funcidxs) throws SemanticException {
		int num_matches = 0;
		int last_match = -1;
		for (Integer i : funcidxs) {
			if (matches(arg_types, ctx.getFunc(i).param_types)) {
				++num_matches;
				last_match = i;
			}
		}
		if (num_matches == 1) return last_match;
		if (num_matches == 0) throw new NoSuchMemberMethodException(range);
		throw new AmbiguousCallException(range);
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
}