package ir3;

import java.util.ArrayList;
import java.io.PrintStream;

/**
 * Static class to emit ARM assembly instructions.
 */

public class AsmEmitter {
	public static void emitAddImm(PrintStream w, int dest, int src, int imm) {
		w.print("add ");
		w.print(reg_names[dest]);
		w.print(',');
		w.print(reg_names[src]);
		w.print(",#");
		w.println(imm);
	}
	public static void emitAddReg(PrintStream w, int dest, int src1, int src2) {
		w.print("add ");
		w.print(reg_names[dest]);
		w.print(',');
		w.print(reg_names[src1]);
		w.print(',');
		w.println(reg_names[src2]);
	}
	public static void emitSubImm(PrintStream w, int dest, int src, int imm) {
		w.print("sub ");
		w.print(reg_names[dest]);
		w.print(',');
		w.print(reg_names[src]);
		w.print(",#");
		w.println(imm);
	}
	public static void emitSubReg(PrintStream w, int dest, int src1, int src2) {
		w.print("sub ");
		w.print(reg_names[dest]);
		w.print(',');
		w.print(reg_names[src1]);
		w.print(',');
		w.println(reg_names[src2]);
	}
	public static void emitRsbImm(PrintStream w, int dest, int src, int imm) {
		w.print("rsb ");
		w.print(reg_names[dest]);
		w.print(',');
		w.print(reg_names[src]);
		w.print(",#");
		w.println(imm);
	}
	public static void emitRsbReg(PrintStream w, int dest, int src1, int src2) {
		w.print("rsb ");
		w.print(reg_names[dest]);
		w.print(',');
		w.print(reg_names[src1]);
		w.print(',');
		w.println(reg_names[src2]);
	}
	public static void emitMulReg(PrintStream w, int dest, int src1, int src2) {
		w.print("mul ");
		w.print(reg_names[dest]);
		w.print(',');
		w.print(reg_names[src1]);
		w.print(',');
		w.println(reg_names[src2]);
	}
	public static void emitSDivReg(PrintStream w, int dest, int src1, int src2) {
		w.print("sdiv ");
		w.print(reg_names[dest]);
		w.print(',');
		w.print(reg_names[src1]);
		w.print(',');
		w.println(reg_names[src2]);
	}
	public static void emitAndImm(PrintStream w, int dest, int src, int imm) {
		w.print("and ");
		w.print(reg_names[dest]);
		w.print(',');
		w.print(reg_names[src]);
		w.print(",#");
		w.println(imm);
	}
	public static void emitAndReg(PrintStream w, int dest, int src1, int src2) {
		w.print("and ");
		w.print(reg_names[dest]);
		w.print(',');
		w.print(reg_names[src1]);
		w.print(',');
		w.println(reg_names[src2]);
	}
	public static void emitOrrImm(PrintStream w, int dest, int src, int imm) {
		w.print("orr ");
		w.print(reg_names[dest]);
		w.print(',');
		w.print(reg_names[src]);
		w.print(",#");
		w.println(imm);
	}
	public static void emitOrrReg(PrintStream w, int dest, int src1, int src2) {
		w.print("orr ");
		w.print(reg_names[dest]);
		w.print(',');
		w.print(reg_names[src1]);
		w.print(',');
		w.println(reg_names[src2]);
	}
	public static void emitEorImm(PrintStream w, int dest, int src, int imm) {
		w.print("eor ");
		w.print(reg_names[dest]);
		w.print(',');
		w.print(reg_names[src]);
		w.print(",#");
		w.println(imm);
	}
	public static void emitEorReg(PrintStream w, int dest, int src1, int src2) {
		w.print("eor ");
		w.print(reg_names[dest]);
		w.print(',');
		w.print(reg_names[src1]);
		w.print(',');
		w.println(reg_names[src2]);
	}
	public static void emitMovReg(PrintStream w, int dest, int src) {
		w.print("mov ");
		w.print(reg_names[dest]);
		w.print(",");
		w.println(reg_names[src]);
	}
	public static void emitMovImm(PrintStream w, int dest, int imm) {
		w.print("mov ");
		w.print(reg_names[dest]);
		w.print(",#");
		w.println(imm);
	}
	public static void emitMovCondImm(PrintStream w, Cond cond, int dest, int imm) {
		w.print("mov");
		w.print(cond.text);
		w.print(' ');
		w.print(reg_names[dest]);
		w.print(",#");
		w.println(imm);
	}
	public static void emitCmpReg(PrintStream w, int src1, int src2) {
		w.print("cmp ");
		w.print(reg_names[src1]);
		w.print(',');
		w.println(reg_names[src2]);
	}
	public static void emitCmpImm(PrintStream w, int src, int imm) {
		w.print("cmp ");
		w.print(reg_names[src]);
		w.print(",#");
		w.println(imm);
	}
	public static void emitStr(PrintStream w, int dest_reg, int offset, int source_reg) {
		w.print("str ");
		w.print(reg_names[source_reg]);
		w.print(",[");
		w.print(reg_names[dest_reg]);
		w.print(",#");
		w.print(offset);
		w.println(']');
	}
	public static void emitStrb(PrintStream w, int dest_reg, int offset, int source_reg) {
		w.print("str ");
		w.print(reg_names[source_reg]);
		w.print(",[");
		w.print(reg_names[dest_reg]);
		w.print(",#");
		w.print(offset);
		w.println(']');
	}
	public static void emitStrPreOffset(PrintStream w, int dest_reg, int offset, int source_reg) {
		w.print("str ");
		w.print(reg_names[source_reg]);
		w.print(",[");
		w.print(reg_names[dest_reg]);
		w.print(",#");
		w.print(offset);
		w.println("]!");
	}
	public static void emitStrbPreOffset(PrintStream w, int dest_reg, int offset, int source_reg) {
		w.print("str ");
		w.print(reg_names[source_reg]);
		w.print(",[");
		w.print(reg_names[dest_reg]);
		w.print(",#");
		w.print(offset);
		w.println("]!");
	}
	public static void emitLdr(PrintStream w, int dest_reg, int source_reg, int offset) {
		w.print("ldr ");
		w.print(reg_names[dest_reg]);
		w.print(",[");
		w.print(reg_names[source_reg]);
		w.print(",#");
		w.print(offset);
		w.println(']');
	}
	public static void emitLdrb(PrintStream w, int dest_reg, int source_reg, int offset) {
		w.print("ldrb ");
		w.print(reg_names[dest_reg]);
		w.print(",[");
		w.print(reg_names[source_reg]);
		w.print(",#");
		w.print(offset);
		w.println(']');
	}
	public static void emitLdrPreOffset(PrintStream w, int dest_reg, int source_reg, int offset) {
		w.print("ldr ");
		w.print(reg_names[dest_reg]);
		w.print(",[");
		w.print(reg_names[source_reg]);
		w.print(",#");
		w.print(offset);
		w.println("]!");
	}
	public static void emitLdrbPreOffset(PrintStream w, int dest_reg, int source_reg, int offset) {
		w.print("ldrb ");
		w.print(reg_names[dest_reg]);
		w.print(",[");
		w.print(reg_names[source_reg]);
		w.print(",#");
		w.print(offset);
		w.println("]!");
	}
	public static void emitLdrPostOffset(PrintStream w, int dest_reg, int source_reg, int offset) {
		w.print("ldr ");
		w.print(reg_names[dest_reg]);
		w.print(",[");
		w.print(reg_names[source_reg]);
		w.print("],#");
		w.println(offset);
	}
	public static void emitLdrLitAddr(PrintStream w, int dest_reg, String name) {
		w.print("ldr ");
		w.print(reg_names[dest_reg]);
		w.print(",=");
		w.println(name);
	}
	public static void emitLdrCondLitAddr(PrintStream w, Cond cond, int dest_reg, String name) {
		w.print("ldr");
		w.print(cond.text);
		w.print(' ');
		w.print(reg_names[dest_reg]);
		w.print(",=");
		w.println(name);
	}


	public static void emitStmfd(PrintStream w, int reg, ArrayList<Integer> reg_arr) {
		assert!(!reg_arr.isEmpty());
		w.print("stmfd ");
		w.print(reg_names[reg]);
		w.print("!,{");
		int i=0;
		w.print(reg_names[reg_arr.get(i++)]);
		while(i!=reg_arr.size()){
			w.print(',');
			w.print(reg_names[reg_arr.get(i++)]);
		}
		w.println('}');
	}
	public static void emitLdmfd(PrintStream w, int reg, ArrayList<Integer> reg_arr) {
		assert!(!reg_arr.isEmpty());
		w.print("ldmfd ");
		w.print(reg_names[reg]);
		w.print("!,{");
		int i=0;
		w.print(reg_names[reg_arr.get(i++)]);
		while(i!=reg_arr.size()){
			w.print(',');
			w.print(reg_names[reg_arr.get(i++)]);
		}
		w.println('}');
	}


	
	public static void emitB(PrintStream w, String name) {
		w.print("b ");
		w.println(name);
	}
	public static void emitBCond(PrintStream w, Cond cond, String name) {
		w.print("b");
		w.print(cond.text);
		w.print(' ');
		w.println(name);
	}

	
	public static void emitBl(PrintStream w, String name) {
		w.print("bl ");
		w.println(name);
	}
	public static void emitBlPlt(PrintStream w, String name) {
		w.print("bl ");
		w.print(name);
		w.println("(PLT)");
	}


	
	public static void emitPseudoStoreVariable(PrintStream w, EmitFunc.StorageLocation sl, int reg, TypeName type) {
		if (sl.isRegister) {
			if (sl.value != reg) emitMovReg(w, sl.value, reg);
		}
		else {
			emitPseudoStr(w, EmitFunc.Registers.SP, sl.value, reg, type);
		}
	}
	public static int emitPseudoLoadVariable(PrintStream w, int hint_reg, EmitFunc.StorageLocation sl, TypeName type) {
		if (sl.isRegister) {
			return sl.value;
		}
		else {
			emitPseudoLdr(w, hint_reg, EmitFunc.Registers.SP, sl.value, type);
			return hint_reg;
		}
	}
	public static void emitPseudoStr(PrintStream w, int dest_reg, int offset, int source_reg, TypeName type) {
		if (type.size == 1) emitStrb(w, dest_reg, offset, source_reg);
		assert(type.size == 4);
		emitStr(w, dest_reg, offset, source_reg);
	}
	public static void emitPseudoStrPreOffset(PrintStream w, int dest_reg, int offset, int source_reg, TypeName type) {
		if (type.size == 1) emitStrbPreOffset(w, dest_reg, offset, source_reg);
		assert(type.size == 4);
		emitStrPreOffset(w, dest_reg, offset, source_reg);
	}
	public static void emitPseudoLdr(PrintStream w, int dest_reg, int source_reg, int offset, TypeName type) {
		if (type.size == 1) emitLdrb(w, dest_reg, source_reg, offset);
		assert(type.size == 4);
		emitLdr(w, dest_reg, source_reg, offset);
	}


	public static enum Cond {
		EQ("eq"), NE("ne"), CS("cs"), CC("cc"), MI("mi"), PL("pl"), VS("vs"), VC("vc"), HI("hi"), LS("ls"), GE("ge"), LT("lt"), GT("gt"), LE("le");

		public final String text;

		private Cond(String text) { this.text = text; }
	} 


	private static String[] reg_names = new String[]{
		"a1",
		"a2",
		"a3",
		"a4",
		"v1",
		"v2",
		"v3",
		"v4",
		"v5",
		"v6",
		"v7",
		"fp",
		"ip",
		"sp",
		"lr",
		"pc"};

	public static void emitPrologue(PrintStream w, EmitFunc ef) {
		ArrayList<Integer> combined_regs = new ArrayList<>(ef.saved_regs);
		combined_regs.addAll(ef.saved_params);
		if (!combined_regs.isEmpty()) AsmEmitter.emitStmfd(w, EmitFunc.Registers.SP, combined_regs);
		final int diff = ef.stack_space - combined_regs.size() * 4;
		if (diff != 0) AsmEmitter.emitSubImm(w, EmitFunc.Registers.SP, EmitFunc.Registers.SP, diff);
	}

	public static void emitEpilogue(PrintStream w, EmitFunc ef) {
		final int diff = ef.stack_space - ef.saved_regs.size() * 4;
		if (diff != 0) AsmEmitter.emitAddImm(w, EmitFunc.Registers.SP, EmitFunc.Registers.SP, diff);
		if (!ef.saved_regs.isEmpty()) AsmEmitter.emitLdmfd(w, EmitFunc.Registers.SP, replaceLRWithPC(ef.saved_regs));
	}

	private static ArrayList<Integer> replaceLRWithPC(ArrayList<Integer> regs) {
		ArrayList<Integer> ret = new ArrayList<>();
		for (Integer reg : regs) {
			if (reg == EmitFunc.Registers.LR) {
				ret.add(EmitFunc.Registers.PC);
			}
			else {
				ret.add(reg);
			}
		}
		return ret;
	}
}

