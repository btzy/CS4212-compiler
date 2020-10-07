package ir3;

import java.util.Optional;

public class Return extends Instruction {
	public final Optional<LocalVariable> val; // might return nothing
	public Return() { this.val = Optional.empty(); }
	public Return(LocalVariable val) { this.val = Optional.of(val); }
	public Return(Optional<LocalVariable> val) { this.val = val; }
}