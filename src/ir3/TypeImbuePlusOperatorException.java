package ir3;

import util.LocationRange;
import util.Errors;
import java.io.PrintStream;

/**
 * Special exception for operator+ overload resolution.
 */
public class TypeImbuePlusOperatorException extends SemanticException {
    public TypeImbuePlusOperatorException(NullableTypeName arg_type_1, NullableTypeName arg_type_2, LocationRange range) {
        super("Invalid argument types for operator \"+\", found types \"" + arg_type_1.getNullableName() + "+" + arg_type_2.getNullableName() + "\"", range);
    }

    @Override
    public void printNiceMessage(PrintStream out, String filename) {
        super.printNiceMessage(out, filename);
        out.println("Note: Expected arguments of binary operator \"+\" to be \"Int+Int\" or \"String+String\"");
    }
}
