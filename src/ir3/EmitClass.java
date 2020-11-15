package ir3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.OptionalInt;
import java.io.PrintStream;
import util.LocationRange;
import ir3.OverloadResolver;

/**
 * Class descriptor for one class.
 */

public class EmitClass {
	public final TypeName this_type;
	public final ArrayList<TypeName> field_types;
	public final ArrayList<Integer> field_offsets;
	public final int size;

	public EmitClass(TypeName this_type, ArrayList<TypeName> field_types, ArrayList<Integer> field_offsets, int size) {
		this.this_type = this_type;
		this.field_types = field_types;
		this.field_offsets = field_offsets;
		this.size = size;
	}
}

