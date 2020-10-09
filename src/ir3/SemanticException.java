package ir3;

import util.LocationRange;
import util.Errors;
import java.io.PrintStream;

public class SemanticException extends Exception {
    public final LocationRange range;
    public static PrintStream printer;
    public static String filename;
    public static boolean previouslyHandled;

    public SemanticException(String message, LocationRange range) {
        super(message);
        this.range = range;
    }

    /**
     * Print a nicely formatted error message with source location.
     * Subclasses can override this method if they want to print additional hints or multiple source locations.
     */
    public void printNiceMessage(PrintStream out, String filename) {
        out.println("Error: " + super.getMessage() + " ( " + Errors.getErrorLocationString(range) + " ) :");
        Errors.printErrorSourceCode(out, filename, range);
    }

    public void handle() {
        printNiceMessage(printer, filename);
        previouslyHandled = true;
    }

    @FunctionalInterface
    public interface SemanticTask {
        void run() throws SemanticException;
    }

    public static void bound(SemanticTask task) {
        try {
            task.run();
        }
        catch (SemanticException ex) {
            ex.handle();
        }
    }
}
