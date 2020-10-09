package ir3;

import util.LocationRange;
import util.Errors;
import java.io.PrintStream;

/**
 * Special exception for operator+ overload resolution.
 */
public class ReadStatementException extends SemanticException {
    private final TypeName arg_type;
    private final String arg_name;
    private final LocationRange declared_range;
    public ReadStatementException(TypeName arg_type, LocationRange range, String arg_name, LocationRange declared_range) {
        super("Type \"" + arg_type.name + "\" cannot be the argument of a readln-statement", range);
        this.arg_type = arg_type;
        this.arg_name = arg_name;
        this.declared_range = declared_range;
    }

    @Override
    public void printNiceMessage(PrintStream out, String filename) {
        super.printNiceMessage(out, filename);
        out.println("Note: The allowed argument types for readln-statement are \"Int\", \"Bool\" or \"String\"");
        out.println("Note: Variable \"" + arg_name + "\" is declared as type \"" + arg_type.name + "\" here ( " + Errors.getErrorLocationString(declared_range) + " ) :");
        Errors.printErrorSourceCode(out, filename, declared_range);
    }
}
