package ir3;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.PrintStream;

public class EmitContext {
	public final ArrayList<FuncSpec> func_specs;
	public final HashMap<TypeName, EmitClass> cd_lookup;
	private final HashMap<String, String> literal_lookup; // maps strings to names (names are '.LC#') where # is an integer
	private final ArrayList<String> literals;
	private final ArrayList<String> literal_names;
	private final HashMap<String, String> cstrliteral_lookup; // maps c-strings to names (names are '.LZ#') where # is an integer
	private final ArrayList<String> cstrliterals;
	private final ArrayList<String> cstrliteral_names;
	private final HashMap<String, String> var_lookup; // maps vars to names (names are '.LN#') where # is an integer
	private final ArrayList<String> vars;
	private final ArrayList<String> var_names;
	private final HashMap<NamespaceIndexPair, String> label_lookup; // maps pairs to names (names are '.L#') where # is an integer
	private final ArrayList<NamespaceIndexPair> labels;
	private final ArrayList<String> label_names;

	private static class NamespaceIndexPair {
		private final Object namespace;
		private final int index;
		public NamespaceIndexPair(Object namespace, int index) {
			this.namespace = namespace;
			this.index = index;
		}
		@Override
		public boolean equals(Object o) { 
			if (!(o instanceof NamespaceIndexPair)) return false;
			final NamespaceIndexPair nip = (NamespaceIndexPair)o;
			return this.namespace == nip.namespace && this.index == nip.index;
		}
		@Override
		public int hashCode() { 
			return System.identityHashCode(namespace) * 31 + index;
		}
	}

	public EmitContext(ArrayList<FuncSpec> func_specs, ArrayList<EmitClass> cds) {
		this.func_specs = func_specs;
		this.cd_lookup = new HashMap<>();
		for (EmitClass cd : cds) {
			cd_lookup.put(cd.this_type, cd);
		}
		this.literal_lookup = new HashMap<>();
		this.literals = new ArrayList<>();
		this.literal_names = new ArrayList<>();
		this.cstrliteral_lookup = new HashMap<>();
		this.cstrliterals = new ArrayList<>();
		this.cstrliteral_names = new ArrayList<>();
		this.var_lookup = new HashMap<>();
		this.vars = new ArrayList<>();
		this.var_names = new ArrayList<>();
		this.label_lookup = new HashMap<>();
		this.labels = new ArrayList<>();
		this.label_names = new ArrayList<>();
	}
	public EmitClass getEmitClass(TypeName type) {
		return cd_lookup.get(type);
	}

	public String addStringLiteral(String value) {
		String name = literal_lookup.get(value);
		if (name == null) {
			name = ".LC" + String.valueOf(literals.size());
			literals.add(value);
			literal_names.add(name);
			literal_lookup.put(value, name);
		}
		return name;
	}

	public String addCStringLiteral(String value) {
		String name = cstrliteral_lookup.get(value);
		if (name == null) {
			name = ".LZ" + String.valueOf(cstrliterals.size());
			cstrliterals.add(value);
			cstrliteral_names.add(name);
			cstrliteral_lookup.put(value, name);
		}
		return name;
	}

	public String addExternWord(String varname) {
		String name = var_lookup.get(varname);
		if (name == null) {
			name = ".LN" + String.valueOf(vars.size());
			vars.add(varname);
			var_names.add(name);
			var_lookup.put(varname, name);
		}
		return name;
	}

	public String addLabel(Object namespace, int index) {
		final NamespaceIndexPair value = new NamespaceIndexPair(namespace, index);
		String name = label_lookup.get(value);
		if (name == null) {
			name = ".L" + String.valueOf(labels.size());
			labels.add(value);
			label_names.add(name);
			label_lookup.put(value, name);
		}
		return name;
	}

	public void emitPreCode(PrintStream w) {
		w.println(".text");
		w.println(".global main");
	}

	public void emitPostCode(PrintStream w) {
		for (int i=0; i!=vars.size(); ++i) {
			w.print(var_names.get(i));
			w.println(':');
			w.print(".word ");
			w.println(vars.get(i));
		}
		w.println(".data");
		for (int i=0; i!=literals.size(); ++i) {
			w.println(".align 2");
			w.print(literal_names.get(i));
			w.println(':');
			w.print(".ascii \"");
			final String text = literals.get(i);
			int len = text.length();
			for (int j=0; j!=4; ++j) {
				octalPrint(w, len % 256);
				len /= 256;
			}
			for (byte b : text.getBytes()) {
				final int ch = Byte.toUnsignedInt(b);
				if (needsEscape(ch)) {
					octalPrint(w, ch);
				}
				else {
					w.print((char)ch);
				}
			}
			w.println('\"');
		}
		for (int i=0; i!=cstrliterals.size(); ++i) {
			w.print(cstrliteral_names.get(i));
			w.println(':');
			w.print(".asciz \"");
			for (byte b : cstrliterals.get(i).getBytes()) {
				final int ch = Byte.toUnsignedInt(b);
				if (needsEscape(ch)) {
					octalPrint(w, ch);
				}
				else {
					w.print((char)ch);
				}
			}
			w.println('\"');
		}
	}

	private static void octalPrint(PrintStream w, int val) {
		w.print('\\');
		final int l1 = val % 8;
		val /= 8;
		final int l2 = val % 8;
		val /= 8;
		w.print(val);
		w.print(l2);
		w.print(l1);
	}

	private static boolean needsEscape(int val) {
		return val < 32 || val == 34 || val == 35 || val == 39 || val == 92 || val == 127;
	}
}
