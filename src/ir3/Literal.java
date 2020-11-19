package ir3;

import java.util.OptionalInt;
import java.util.ArrayList;

/**
 * Represents a Literal with real type (cannot be nullptr_t).
 * In IR3, all terminals are typed.
 */
public abstract class Literal extends Terminal {
	public Literal(TypeName type) { super(type); }

	@Override
	public ArrayList<Integer> getUses() {
		return new ArrayList<Integer>();
	}
	
	@Override
	public ArrayList<Integer> getClobberedRegs() {
		return new ArrayList<Integer>();
	}
}