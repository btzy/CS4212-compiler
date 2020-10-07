package ir3;

import util.LocationRange;
import java.io.PrintStream;

public class AmbiguousCallException extends SemanticException {
    // TODO
    //private boolean hintField;
    public AmbiguousCallException(/*String name, String classname, */LocationRange range/*, boolean hintField*/) {
        super("Method call is ambiguous", range/*"Method " + name + " does not exist in class \"" + classname + "\"", range*/);
        //this.hintField = hintField;
    }
    /*
    @Override
    public void printNiceMessage(PrintStream out, String filename) {
        super.printNiceMessage(out, filename);
        if (hintField) {
            out.println("Hint: There is a field of the same name");
        }
    }*/
}
