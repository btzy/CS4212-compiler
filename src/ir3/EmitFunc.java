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

public class EmitFunc {
	public final int stack_space;
	public final ArrayList<Integer> saved_regs;
	public final ArrayList<Integer> saved_params;
	public final ArrayList<StorageLocation> storage_locations;
	public final LocalEnvironment env;

	/**
	 * Describes where to store a local variable.
	 * If isRegister is true, then value represents the register index.
	 * Otherwise, value represents the offset from SP.
	 */
	public static class StorageLocation {
		public final boolean isRegister;
		public final int value;

		private StorageLocation(boolean isRegister, int value) {this.isRegister = isRegister; this.value = value;}
	
		public static StorageLocation makeRegister(int reg) {
			return new StorageLocation(true, reg);
		}
		public static StorageLocation makeMemLocal(int offset) { // offset from sp
			return new StorageLocation(false, offset);
		}
	}

	public static class Registers {
		public static final int A1 = 0;
		public static final int A2 = 1;
		public static final int A3 = 2;
		public static final int A4 = 3;
		public static final int V1 = 4;
		public static final int V2 = 5;
		public static final int V3 = 6;
		public static final int V4 = 7;
		public static final int V5 = 8;
		public static final int V6 = 9;
		public static final int V7 = 10;
		public static final int FP = 11;
		public static final int IP = 12;
		public static final int SP = 13;
		public static final int LR = 14;
		public static final int PC = 15;
	}


	public EmitFunc(int stack_space, ArrayList<Integer> saved_regs, ArrayList<Integer> saved_params, ArrayList<StorageLocation> storage_locations, LocalEnvironment env) {
		this.stack_space = stack_space;
		this.saved_regs = saved_regs;
		this.saved_params = saved_params;
		this.storage_locations = storage_locations;
		this.env = env;
	}
}

