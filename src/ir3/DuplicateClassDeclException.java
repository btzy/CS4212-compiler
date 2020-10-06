package ir3;

import util.LocationRange;

public class DuplicateClassDeclException extends SemanticException {
    private final LocationRange previous;
    public DuplicateClassDeclException(String classname, LocationRange range, LocationRange previous) {
        super("Duplicate declaration of class \"" + classname + "\"", range);
        this.previous = previous;
    }
}
