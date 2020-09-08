import java.io.FileReader;

/*
Note: need to change to cup actions, like:
expr ::= expr:e1 PLUS expr:e2
    {: RESULT = new Integer(e1.intValue() + e2.intValue()); :}
*/

public class Compiler {
    public static void main(String[] args) {
        try {
            parser p = new parser(new Lexer(new FileReader(tryGetArg(args, 0, "input file"))));
            Object result = p.parse().value;
            System.out.println(result.toString());
            /*Lexer l = new Lexer(new FileReader(tryGetArg(args, 0, "input file")));
            System.out.println(l.next_token());
            System.out.println(l.next_token());
            System.out.println(l.next_token());
            System.out.println(l.next_token());
            System.out.println(l.next_token());*/
        }
        catch (Exception e) {
            System.out.println("Exception occured: " + e.toString());
            e.printStackTrace(System.out);
        }
    }

    private static String tryGetArg(String[] args, int position, String arg_name) throws CommandArgumentException {
        if (args.length > position) return args[position];
        throw new CommandArgumentException(position, arg_name);
    }
}