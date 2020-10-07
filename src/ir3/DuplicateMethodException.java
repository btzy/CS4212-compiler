package ir3;

import util.LocationRange;
import util.Errors;
import java.io.PrintStream;

public class DuplicateMethodException extends SemanticException {
    private final LocationRange previous;
    public DuplicateMethodException(String methodname, String classname, LocationRange range, LocationRange previous) {
        super("Duplicate declaration of method \"" + methodname + "\" in class " + classname, range);
        this.previous = previous;
    }

    @Override
    public void printNiceMessage(PrintStream out, String filename) {
        super.printNiceMessage(out, filename);
        out.println("Note: Previously declared here ( " + Errors.getErrorLocationString(previous) + " ) :");
        Errors.printErrorSourceCode(out, filename, previous);
    }
}
