public class CommandArgumentException extends Exception {
	CommandArgumentException(int position, String arg_name) {
		super("Missing command line argument, expected " + arg_name + " at position " + position);
	}
}
