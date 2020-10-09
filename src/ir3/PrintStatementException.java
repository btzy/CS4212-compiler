package ir3;

import util.LocationRange;
import util.Errors;
import java.io.PrintStream;

/**
 * Special exception for operator+ overload resolution.
 */
public class PrintStatementException extends SemanticException {
    public PrintStatementException(NullableTypeName arg_type, LocationRange range) {
        super("Type \"" + arg_type.getNullableName() + "\" cannot be the argument of a println-statement", range);
    }

    @Override
    public void printNiceMessage(PrintStream out, String filename) {
        super.printNiceMessage(out, filename);
        out.println("Note: The allowed argument types for println-statement are \"Int\", \"Bool\" or \"String\"");
    }
}
