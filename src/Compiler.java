import java.io.FileReader;
import java.util.HashMap;
import tree.Program;
import tree.NestedPrintStream;
import java_cup.runtime.SymbolFactory;
import java_cup.runtime.ComplexSymbolFactory;
import ir3.SemanticException;
import util.Errors;

public class Compiler {
    public static void main(String[] args) {
        final HashMap<String, String> argmap = parseArgs(args);
        //final boolean lenient = hasArg(argmap, "lenient");
        try {
            final ComplexSymbolFactory sf = new ComplexSymbolFactory();
            final String filename = getArgWithValue(argmap, "i");
            parser p = new parser(new Lexer(new FileReader(filename), sf, filename), sf, (int left_line, int left_col, int right_line, int right_col) -> {
                Errors.printErrorSourceCode(System.err, filename, left_line, left_col, right_line, right_col);
            });
            Object program_obj = p.parse().value;
            if (p.userHasFatalError) System.exit(1);
            Program program = (Program) program_obj;
            //if (p.userHasError && !lenient) throw new Exception("Parse aborted due to above error.  To skip over error, try using lenient mode ('-lenient').");
            if (p.userHasError) throw new Exception("Parse aborted due to above error.");
            // program.print(new NestedPrintStream(System.out));
            ir3.Program ir3_program = null;
            try {
                ir3_program = program.typeCheckAndEmitIR3();
            }
            catch (SemanticException e) {
                e.printNiceMessage(System.err, filename);
                System.exit(1);
            }
            ir3_program.print(System.out);
        }
        catch (CommandArgumentException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        catch (UnknownCharacterException e) {
            System.err.println("Error: " + e.getMessage() + " :");
            Errors.printErrorSourceCode(System.err, e.filename, e.left_line, e.left_col, e.right_line, e.right_col);
            System.err.println("Parse aborted due to above error from lexer.");
            System.exit(1);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
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
}