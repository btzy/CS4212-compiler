package ir3;

import util.LocationRange;
import util.Errors;
import java.io.PrintStream;

/**
 * Subclasses should extend this class to print a note that explains why the imbued type is required.
 */
public class TypeImbueException extends SemanticException {
    public TypeImbueException(NullableTypeName arg_type, LocationRange range, TypeName imbued_type) {
        super("Expected expression to have type \"" + imbued_type.name + "\", but got type \"" + arg_type.getNullableName() + "\" instead", range);
    }
}
