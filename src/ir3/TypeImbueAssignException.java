package ir3;

import util.LocationRange;
import util.Errors;
import java.io.PrintStream;

/**
 * Subclasses should extend this class to print a note that explains why the imbued type is required.
 */
public class TypeImbueAssignException extends TypeImbueException {
    private TypeName declared_type;
    private String assignee_name;
    private LocationRange declaration_range;
    public TypeImbueAssignException(NullableTypeName arg_type, LocationRange range, TypeName declared_type, String assignee_name, LocationRange declaration_range) {
        super(arg_type, range, declared_type);
        this.declared_type = declared_type;
        this.assignee_name = assignee_name;
        this.declaration_range = declaration_range;
    }

    @Override
    public void printNiceMessage(PrintStream out, String filename) {
        super.printNiceMessage(out, filename);
        out.println("Note: Variable \"" + assignee_name + "\" was declared as type \"" + declared_type.name + "\" here ( " + Errors.getErrorLocationString(declaration_range) + " ) :");
        Errors.printErrorSourceCode(out, filename, declaration_range);
    }
}
