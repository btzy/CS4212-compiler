package tree;


public class Utils {
	public static <T extends Node> void commaSeparatedPrint(Iterable<T> iterable, NestedPrintStream w) {
		boolean old = false;
		for (T el : iterable) {
			if (old) w.print(", ");
			el.print(w);
			old = true;
		}
	}
}