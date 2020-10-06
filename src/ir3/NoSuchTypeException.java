package ir3;

import util.LocationRange;

public class NoSuchTypeException extends SemanticException {
    public NoSuchTypeException(String name, LocationRange range) {
        super("No such type \"" + name + "\"", range);
    }
}
