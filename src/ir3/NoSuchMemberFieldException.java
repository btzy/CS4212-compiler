package ir3;

import util.LocationRange;
import java.io.PrintStream;

public class NoSuchMemberFieldException extends SemanticException {
    private int hintMethodCount;
    public NoSuchMemberFieldException(String name, String classname, LocationRange range, int hintMethodCount) {
        super("Type " + classname + " does not have field \"" + name + "\"", range);
        this.hintMethodCount = hintMethodCount;
    }

    @Override
    public void printNiceMessage(PrintStream out, String filename) {
        super.printNiceMessage(out, filename);
        if (hintMethodCount == 1) {
            out.println("Hint: There is a method of the same name, did you intend to refer to it instead?");
        }
        else if (hintMethodCount > 1) {
            out.println("Hint: There are " + String.valueOf(hintMethodCount) + " method overloads of the same name, did you intend to refer to one of them instead?");
        }
    }
}
