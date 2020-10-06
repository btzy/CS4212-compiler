package ir3;

import util.LocationRange;

public class SemanticException extends Exception {
    public final LocationRange range;
    public SemanticException(String message, LocationRange range) {
        super(message);
        this.range = range;
    }
}
