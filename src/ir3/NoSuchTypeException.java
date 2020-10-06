package ir3;

import util.LocationRange;

public class NoSuchTypeException extends SemanticException {
    private final LocationRange previous;
    public NoSuchTypeException(String name, LocationRange range, LocationRange previous) {
        super("No such type \"" + name + "\"", range);
        this.previous = previous;
    }
}
