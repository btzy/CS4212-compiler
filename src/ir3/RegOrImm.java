package ir3;

import java.io.PrintStream;
import java.util.OptionalInt;
import java.util.ArrayList;
import java.util.function.IntConsumer;

public class RegOrImm {
	private final boolean is_reg;
	private final int val;
	private RegOrImm(boolean is_reg, int val) { this.is_reg = is_reg; this.val = val; }

	public static RegOrImm makeReg(int reg) { return new RegOrImm(true, reg); }
	public static RegOrImm makeImm(int imm) { return new RegOrImm(false, imm); }

	public void ifRegOrElse(IntConsumer creg, IntConsumer cimm) {
		if (is_reg) creg.accept(val);
		else cimm.accept(val);
	}

	public void ifReg(IntConsumer creg) {
		if (is_reg) creg.accept(val);
	}

	public void ifImm(IntConsumer cimm) {
		if (!is_reg) cimm.accept(val);
	}

	public boolean isReg() { return is_reg; }
}