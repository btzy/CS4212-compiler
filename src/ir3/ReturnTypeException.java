package ir3;

import util.LocationRange;
import util.Errors;
import java.io.PrintStream;

public class ReturnTypeException extends SemanticException {
    private final TypeName declared_return_type;
    private final LocationRange declared_return_range;
    public ReturnTypeException(NullableTypeName arg_type, LocationRange range, TypeName declared_return_type, LocationRange declared_return_range) {
        super(((!arg_type.isNull() && arg_type.getTypeName() == TypeName.VOID)
            ? "Return statement with no value is not allowed in method that returns \""
            : ("Returning a value of type \"" + arg_type.getNullableName() + "\" is not allowed in method that returns \""))
            + declared_return_type.name + "\"", range);
        
        this.declared_return_type = declared_return_type;
        this.declared_return_range = declared_return_range;
    }

    @Override
    public void printNiceMessage(PrintStream out, String filename) {
        super.printNiceMessage(out, filename);
        out.println("Note: Method declared to return \"" + declared_return_type.name + "\" here ( " + Errors.getErrorLocationString(declared_return_range) + " ) :");
        Errors.printErrorSourceCode(out, filename, declared_return_range);
    }
}
