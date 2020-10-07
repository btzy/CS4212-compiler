package ir3;

import util.LocationRange;

public class NoSuchNameException extends SemanticException {
    public NoSuchNameException(String name, LocationRange range) {
        super("Undeclared name \"" + name + "\"", range);
    }
}
