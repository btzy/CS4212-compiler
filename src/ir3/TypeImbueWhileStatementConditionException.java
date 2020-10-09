package ir3;

import util.LocationRange;
import util.Errors;
import java.io.PrintStream;

/**
 * Subclasses should extend this class to print a note that explains why the imbued type is required.
 */
public class TypeImbueWhileStatementConditionException extends TypeImbueException {
    private BinOp op;
    private TypeName expected_type;
    public TypeImbueWhileStatementConditionException(NullableTypeName arg_type, LocationRange range) {
        super(arg_type, range, TypeName.BOOL);
    }

    @Override
    public void printNiceMessage(PrintStream out, String filename) {
        super.printNiceMessage(out, filename);
        out.println("Note: Condition of while-statement must have type \"Bool\"");
    }
}
