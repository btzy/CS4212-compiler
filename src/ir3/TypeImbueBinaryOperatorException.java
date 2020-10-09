package ir3;

import util.LocationRange;
import util.Errors;
import java.io.PrintStream;

/**
 * Subclasses should extend this class to print a note that explains why the imbued type is required.
 */
public class TypeImbueBinaryOperatorException extends TypeImbueException {
    private BinOp op;
    private TypeName expected_type;
    public TypeImbueBinaryOperatorException(NullableTypeName arg_type, LocationRange range, BinOp op, TypeName expected_type) {
        super(arg_type, range, expected_type);
        this.op = op;
        this.expected_type = expected_type;
    }

    @Override
    public void printNiceMessage(PrintStream out, String filename) {
        super.printNiceMessage(out, filename);
        out.print("Note: Arguments of binary operator \"");
        op.print(out);
        out.println("\" must have type \"" + expected_type.name + "\"");
    }
}
