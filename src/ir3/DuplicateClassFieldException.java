package ir3;

import util.LocationRange;

public class DuplicateClassFieldException extends SemanticException {
    private final LocationRange previous;
    public DuplicateClassFieldException(String fieldname, String classname, LocationRange range, LocationRange previous) {
        super("Duplicate declaration of field \"" + fieldname + "\" in class " + classname, range);
        this.previous = previous;
    }
}
