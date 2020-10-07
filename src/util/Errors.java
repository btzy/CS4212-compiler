package util;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class Errors {
    
    public static String getErrorLocationString(LocationRange range) {
        return getErrorLocationString(range.left, range.right);
    }
    public static String getErrorLocationString(Location left, Location right) {
        return getErrorLocationString(left.getLine(), left.getColumn(), right.getLine(), right.getColumn());
    }
	public static String getErrorLocationString(int left_line, int left_col, int right_line, int right_col) {
        // left_col is added by one for human readability (because it includes start and end and is 1-indexed)
        return "line " + left_line + " col " + (left_col + 1) + " - line " + right_line + " col " + right_col;
    }

    public static void printErrorSourceCode(PrintStream out, String filename, LocationRange range) {
        printErrorSourceCode(out, filename, range.left, range.right);
    }
    public static void printErrorSourceCode(PrintStream out, String filename, Location left, Location right) {
        printErrorSourceCode(out, filename, left.getLine(), left.getColumn(), right.getLine(), right.getColumn());
    }
	public static void printErrorSourceCode(PrintStream out, String filename, int left_line, int left_col, int right_line, int right_col) {
        try {
            final int number_len = String.valueOf(right_line).length(); 
            int curr_line = 1;
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            while (curr_line <= right_line) {
                final int tmp_line = curr_line;
                ++curr_line;
                String s = reader.readLine();
                if (s == null) return;
                if (tmp_line < left_line) continue;
                String line_str = String.valueOf(tmp_line);
                for (int i=line_str.length(); i<number_len; ++i) out.print(' ');
                out.print(line_str);
                out.print(" | ");
                out.println(s);
                int start_col = (tmp_line == left_line ? Math.max(0, Math.min(left_col, s.length())) : 0);
                int end_col = (tmp_line == right_line ? Math.min(s.length(), Math.max(0, right_col)) : s.length());
                if (end_col <= start_col) end_col = start_col + 1;
                StringBuilder sb = new StringBuilder();
                for (int i=0; i<number_len; ++i) sb.append(' ');
                sb.append(" | ");
                for (int i=0; i<start_col; ++i) sb.append(' ');
                for (int i=start_col; i<end_col; ++i) sb.append('^');
                out.println(sb.toString());
            }
        } catch (Exception e) {
            out.println(e);
        }
    }
}