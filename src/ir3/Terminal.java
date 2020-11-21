package ir3;

import util.LocationRange;
import java.util.function.Consumer;
import java.util.ArrayList;
import java.util.OptionalInt;

/**
 * Represents a local varname or Literal with real type (cannot be nullptr_t).
 * In IR3, all terminals are typed.
 */
public abstract class Terminal extends Expr {
	public Terminal(TypeName type) { super(type); }

	@Override
	public Terminal makeTerminalByMaybeEmitIR3(LocationRange virtual_range, Context ctx, Consumer<? super ir3.Instruction> out) {
		return this;
	}

	@Override
	public Terminal makeRelExp3ByMaybeEmitIR3(LocationRange virtual_range, Context ctx, Consumer<? super ir3.Instruction> out) {
		return this;
	}

	@Override
	public ArrayList<VarRegPair> getRegPreferences() {
		return new ArrayList<>();
	}

	@Override
	public OptionalInt getOutputRegPreference() {
		return OptionalInt.empty();
	}
}