package ir3;

import util.LocationRange;
import java.io.PrintStream;

public class VoidTypeException extends SemanticException {
    public VoidTypeException(LocationRange range) {
        super("Void type not allowed here", range);
    }

    @Override
    public void printNiceMessage(PrintStream out, String filename) {
        super.printNiceMessage(out, filename);
        out.println("Note: The Void type is only allowed in the return type of a method");
    }
}
