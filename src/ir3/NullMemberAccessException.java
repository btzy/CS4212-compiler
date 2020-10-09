package ir3;

import util.LocationRange;
import util.Errors;
import java.io.PrintStream;

/**
 * Special exception doing "null.member".
 */
public class NullMemberAccessException extends SemanticException {
    public NullMemberAccessException(LocationRange range) {
        super("Cannot access member on null object", range);
    }
}
