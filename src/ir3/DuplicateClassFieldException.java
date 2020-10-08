package ir3;

import util.LocationRange;
import util.Errors;
import java.io.PrintStream;

public class DuplicateClassFieldException extends SemanticException {
    private final LocationRange previous;
    public DuplicateClassFieldException(String fieldname, String classname, LocationRange range, LocationRange previous) {
        super("Duplicate declaration of field \"" + fieldname + "\" in class \"" + classname + "\"", range);
        this.previous = previous;
    }

    @Override
    public void printNiceMessage(PrintStream out, String filename) {
        super.printNiceMessage(out, filename);
        out.println("Note: Previously declared here ( " + Errors.getErrorLocationString(previous) + " ) :");
        Errors.printErrorSourceCode(out, filename, previous);
    }
}
