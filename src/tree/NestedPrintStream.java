package tree;

import java.io.PrintStream;
import java.io.OutputStream;

public class NestedPrintStream extends PrintStream {
	private static final int NUM_SPACES = 2;
	private int curr_spaces = 0;
	private boolean printed_spaces = false;

	public NestedPrintStream(OutputStream out) {
		super(out);
	}
	private void printSpacesIfNeeded() {
		if (!printed_spaces) {
			for (int i = 0; i < curr_spaces; ++i) {
				super.print(' ');
			}
			printed_spaces = true;
		}
	}
	private void resetprintedSpaces() {
		printed_spaces = false;
	}
	public void enterContext() {
		curr_spaces += NUM_SPACES;
	}
	public void leaveContext() {
		curr_spaces -= NUM_SPACES;
	}
	public void print(boolean v) {
		printSpacesIfNeeded();
		super.print(v);
	}
	public void print(char v) {
		printSpacesIfNeeded();
		super.print(v);
	}
	public void print(char[] v) {
		printSpacesIfNeeded();
		super.print(v);
	}
	public void print(double v) {
		printSpacesIfNeeded();
		super.print(v);
	}
	public void print(float v) {
		printSpacesIfNeeded();
		super.print(v);
	}
	public void print(int v) {
		printSpacesIfNeeded();
		super.print(v);
	}
	public void print(long v) {
		printSpacesIfNeeded();
		super.print(v);
	}
	public void print(Object v) {
		printSpacesIfNeeded();
		super.print(v);
	}
	public void print(String v) {
		printSpacesIfNeeded();
		super.print(v);
	}
	public void println() {
		super.println();
		resetprintedSpaces();
	}
	public void println(boolean v) {
		printSpacesIfNeeded();
		super.println(v);
		resetprintedSpaces();
	}
	public void println(char v) {
		printSpacesIfNeeded();
		super.println(v);
		resetprintedSpaces();
	}
	public void println(char[] v) {
		printSpacesIfNeeded();
		super.println(v);
		resetprintedSpaces();
	}
	public void println(double v) {
		printSpacesIfNeeded();
		super.println(v);
		resetprintedSpaces();
	}
	public void println(float v) {
		printSpacesIfNeeded();
		super.println(v);
		resetprintedSpaces();
	}
	public void println(int v) {
		printSpacesIfNeeded();
		super.println(v);
		resetprintedSpaces();
	}
	public void println(long v) {
		printSpacesIfNeeded();
		super.println(v);
		resetprintedSpaces();
	}
	public void println(Object v) {
		printSpacesIfNeeded();
		super.println(v);
		resetprintedSpaces();
	}
	public void println(String v) {
		printSpacesIfNeeded();
		super.println(v);
		resetprintedSpaces();
	}
}