public class CommandArgumentNeedsValueException extends CommandArgumentException {
	CommandArgumentNeedsValueException(String arg_name) {
		super("Argument -" + arg_name + " needs a value");
	}
}
