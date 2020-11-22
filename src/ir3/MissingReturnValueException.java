package ir3;

import util.LocationRange;
import util.Errors;
import java.io.PrintStream;

public class MissingReturnValueException extends SemanticException {
    private final TypeName declared_return_type;
    private final LocationRange declared_return_range;
    public MissingReturnValueException(LocationRange range, TypeName declared_return_type, LocationRange declared_return_range) {
        super("Method must return a value", range);
        
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
