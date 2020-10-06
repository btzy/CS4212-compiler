package util;

import java.io.FileReader;
import java.io.BufferedReader;

public class Errors {
	public static void printErrorLocation(String filename, int left_line, int left_col, int right_line, int right_col) {
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
                for (int i=line_str.length(); i<number_len; ++i) System.err.print(' ');
                System.err.print(line_str);
                System.err.print(" | ");
                System.err.println(s);
                int start_col = (tmp_line == left_line ? Math.max(0, Math.min(left_col, s.length())) : 0);
                int end_col = (tmp_line == right_line ? Math.min(s.length(), Math.max(0, right_col)) : s.length());
                if (end_col <= start_col) end_col = start_col + 1;
                StringBuilder sb = new StringBuilder();
                for (int i=0; i<number_len; ++i) sb.append(' ');
                sb.append(" | ");
                for (int i=0; i<start_col; ++i) sb.append(' ');
                for (int i=start_col; i<end_col; ++i) sb.append('^');
                System.err.println(sb.toString());
            }
        } catch (Exception e) {System.err.println(e);}
    }
}