package ir3;

import util.LocationRange;
import util.Errors;
import java.io.PrintStream;

public class DuplicateLocalVariableException extends SemanticException {
    private final LocationRange previous;
    public DuplicateLocalVariableException(String name, LocationRange range, LocationRange previous) {
        super("Duplicate declaration of local variable \"" + name + "\"", range);
        this.previous = previous;
    }

    @Override
    public void printNiceMessage(PrintStream out, String filename) {
        super.printNiceMessage(out, filename);
        out.println("Note: Previously declared here ( " + Errors.getErrorLocationString(previous) + " ) :");
        Errors.printErrorSourceCode(out, filename, previous);
    }
}
