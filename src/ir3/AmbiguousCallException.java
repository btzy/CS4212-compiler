package ir3;

import util.LocationRange;
import util.Errors;
import java.io.PrintStream;
import java.util.ArrayList;

public class AmbiguousCallException extends SemanticException {
    private ArrayList<String> candidates_without_this;
    private ArrayList<LocationRange> candidate_ranges;
    public AmbiguousCallException(String name_with_params, String classname, LocationRange range, ArrayList<String> candidates_without_this, ArrayList<LocationRange> candidate_ranges) {
        super("Ambiguous method call to " + name_with_params + " of class " + classname, range);
        this.candidates_without_this = candidates_without_this;
        this.candidate_ranges = candidate_ranges;
    }
    
    @Override
    public void printNiceMessage(PrintStream out, String filename) {
        super.printNiceMessage(out, filename);
        out.println("Hint: " + candidates_without_this.size() + " candidate overloads matched");
        for (int i=0; i!=candidates_without_this.size(); ++i) {
            out.println("Hint: Candidate overload " + candidates_without_this.get(i) + " matches ( " + Errors.getErrorLocationString(candidate_ranges.get(i)) + " ) :");
            Errors.printErrorSourceCode(out, filename, candidate_ranges.get(i));
        }
    }
}
