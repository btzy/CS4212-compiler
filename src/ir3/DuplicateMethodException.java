package ir3;

import util.LocationRange;

public class DuplicateMethodException extends SemanticException {
    private final LocationRange previous;
    public DuplicateMethodException(String methodname, String classname, LocationRange range, LocationRange previous) {
        super("Duplicate declaration of method \"" + methodname + "\" in class " + classname, range);
        this.previous = previous;
    }
}
