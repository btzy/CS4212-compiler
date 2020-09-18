public class CommandArgumentMissingException extends CommandArgumentException {
	CommandArgumentMissingException(String arg_name) {
		super("Missing command line argument, expected argument -" + arg_name);
	}
}
