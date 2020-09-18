import java.io.FileReader;
import java.io.BufferedReader;
import java.util.HashMap;
import tree.Program;
import tree.NestedPrintStream;
import java_cup.runtime.SymbolFactory;
import java_cup.runtime.ComplexSymbolFactory;

/*
Note: need to change to cup actions, like:
expr ::= expr:e1 PLUS expr:e2
    {: RESULT = new Integer(e1.intValue() + e2.intValue()); :}
*/

public class Compiler {
    public static void main(String[] args) {
        final HashMap<String, String> argmap = parseArgs(args);
        boolean lenient = hasArg(argmap, "lenient");
        try {
            final ComplexSymbolFactory sf = new ComplexSymbolFactory();
            final String filename = getArgWithValue(argmap, "i");
            parser p = new parser(new Lexer(new FileReader(filename), sf), sf, (int left_line, int left_col, int right_line, int right_col) -> {
                printErrorLocation(filename, left_line, left_col, right_line, right_col);
            });
            Object program_obj = p.parse().value;
            if (p.userHasFatalError) System.exit(1);
            Program program = (Program) program_obj;
            if (p.userHasError && !lenient) throw new Exception("Parse aborted due to above error.  To skip over error, try using lenient mode ('-lenient').");
            program.print(new NestedPrintStream(System.out));
            /*parser p = new parser(new Lexer(new FileReader(tryGetArg(args, 0, "input file"))));
            Program program = (Program) p.parse().value;
            program.print(System.out);*/
            /*Lexer l = new Lexer(new FileReader(tryGetArg(args, 0, "input file")));
            System.out.println(l.next_token());
            System.out.println(l.next_token());
            System.out.println(l.next_token());
            System.out.println(l.next_token());
            System.out.println(l.next_token());*/
        }
        catch (CommandArgumentException e) {
            System.err.println(e.toString());
            System.exit(1);
        }
        catch (Exception e) {
            System.err.println(e.toString());
            System.exit(1);
        }
    }

    private static HashMap<String, String> parseArgs(String[] args) {
        HashMap<String, String> ret = new HashMap<String, String>();
        int i=0;
        while (i < args.length) {
            String key = args[i];
            if (!key.startsWith("-")) {
                System.err.println("Argument error... all flags/arguments must be prefixed by '-'.  See README for details.");
                System.exit(1);
            }
            key = key.substring(1);
            ++i;
            if (i < args.length && !args[i].startsWith("-")) {
                String value = args[i];
                ret.put(key, value);
                ++i;
            }
            else {
                ret.put(key, null);
            }
        }
        return ret;
    }

    private static String getArgWithValue(HashMap<String, String> args, String arg_name) throws CommandArgumentException {
        if (!args.containsKey(arg_name)) throw new CommandArgumentMissingException(arg_name);
        String ret = args.get(arg_name);
        if (ret == null) throw new CommandArgumentNeedsValueException(arg_name);
        return ret;
    }

    private static boolean hasArg(HashMap<String, String> args, String arg_name) {
        return args.containsKey(arg_name);
    }

    private static void printErrorLocation(String filename, int left_line, int left_col, int right_line, int right_col) {
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