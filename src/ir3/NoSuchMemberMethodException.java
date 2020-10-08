package ir3;

import util.LocationRange;
import util.Errors;
import java.io.PrintStream;
import java.util.Optional;

public class NoSuchMemberMethodException extends SemanticException {
    private Optional<LocationRange> hintField;
    public NoSuchMemberMethodException(String name, String classname, LocationRange range, Optional<LocationRange> hintField) {
        super("Method \"" + name + "\" does not exist in class " + classname, range);
        this.hintField = hintField;
    }
    
    @Override
    public void printNiceMessage(PrintStream out, String filename) {
        super.printNiceMessage(out, filename);
        hintField.ifPresent(rangef -> {
            out.println("Hint: There is a field of the same name, did you intend to refer to it instead? ( " + Errors.getErrorLocationString(rangef) + " ) :");
            Errors.printErrorSourceCode(out, filename, rangef);
        });
    }
}
