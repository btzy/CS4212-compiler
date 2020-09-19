
CS4212 Programming Assignment 1
Bernard Teo Zhi Yi

================================

# Introduction

This submission is a lexer and parser for the Jlite language specified in the assignment brief.  You can input a Jlite source file, and it will produce a parse tree from the source file, and print it with neat formatting and proper indentation.  It actually produces something more like an abstract syntax tree, but prints it out in a readable form that adheres to the requirements.

Errors will be detected, and my program will print out details about the error, including highlighting the relevant text from the source file.  In most situations, the parser can recover from an error and continue parsing the rest of the Jlite source file.

# Content of the submission
The `src` directory contains all the files of my submission, including appropriate versions of the jflex and cup binaries.

Subdirectories:
- `src`: code that drives the lexer/parser program
- `src/tree`: classes to represent the tree structure of the program
- `src/test`: contains tests
- `src/test/errors`: contains tests that are parse errors
- `src/jflex`: contains jflex spec and jflex binaries
- `src/cup`: contains cup spec and cup binaries

**Important!: Before continuing any further, you must go into the directory first:**
```
cd src
```

## Compile
Firstly, we need to chmod the java archives for jflex and cup, and the scripts:
```
chmod u+x cup/*.jar
chmod u+x jflex/jflex-1.8.2/lib/*.jar
chmod u+x *.sh
```

Then type `make`, like this command:
```
make
```

Then it should be done after a while.

## Run
You can use the `run.sh` script, which will use the specified Jlite source file from the test folder.  For example to run the `test/test-all-grammar.j` file, you can do:
```
./run.sh test-all-grammar
```

Remember that you do not add the file extension here.

Alternatively, you may type in the command manually (see the content of `run.sh` for the exact syntax).

# Tests
All the successful tests are in `src/test`.  Those tests meant to be parse errors are in `src/test/errors`.

There are 6 successful tests (apart from the default), and 5 parse error tests.

To run all the successful tests, do:
```
./test.sh
```

You should get an output like this:
```
Testing test/default1 ... OK
Testing test/default2 ... OK
Testing test/fibonacci ... OK
Testing test/many-func ... OK
Testing test/nest ... OK
Testing test/operator ... OK
Testing test/string-literal ... OK
Testing test/test-all-grammar ... OK
```

The `default1` and `default2` tests are those that came with the assignment.

The `fibonacci` test is a small test that does a bit of recursive function calls.  The `many-func` test has a lot of methods, and a few of the `new X()` syntax, which is a place in which Jlite differs from Java.  The `nest` test does many tested if-statements.  The `operator` test tries all the binary operators, and sprinkles some unary minus too.  The `string-literal` test is for things like "\032" and "\x08".  The `test-all-grammar` test is a catch-all that tests almost every production in the grammar.

Note: When printing string literals, we do not add back the '\' characters.  For example, `println("\\")` will turn into `Println("\")`.  This is deliberate -- so that you can see that I actually have rules to recognise the escape characters.  (If not, I could have treated "\n", "\r", "\\" etc as two separate characters.)

# Parse errors

To test the parse errors, you can run them like this (for example, we use `test/errors/error-recover.j`:
```
./run.sh errors/error-recover
```

We will get a message like this (in stderr):

```
Syntax error ( line 12 col 17 - line 12 col 18 ) :
12 |     z = z + z +  + "123";
   |                  ^
instead expected token classes are [TRUE, FALSE, NEW, IDENTIFIER, INTEGER_LITERAL, STRING_LITERAL, MINUS, NEGATION, LPAREN]
Syntax error integer_literal( line 14 col 21 - line 14 col 24 ) :
14 |     z = z + z + "123"123;
   |                      ^^^
instead expected token classes are [EQ, NE, LT, LE, GT, GE, PLUS, MINUS, TIMES, DIVIDE, DOT, CONJUNCTION, DISJUNCTION, LPAREN, SEMI]
Syntax error integer_literal( line 22 col 13 - line 22 col 16 ) :
22 |     x = 1000.123;
   |              ^^^
instead expected token classes are [IDENTIFIER]
Syntax error ( line 34 col 51 - line 34 col 52 ) :
34 | Void f2(Int x, String y) { this.field2 = x; return;;}
   |                                                    ^
instead expected token classes are [error, NULL, TRUE, FALSE, NEW, RETURN, IF, WHILE, READLN, PRINTLN, IDENTIFIER, INTEGER_LITERAL, STRING_LITERAL, MINUS, NEGATION, LPAREN, RCURLY]
Syntax error class_name( line 36 col 18 - line 36 col 24 ) :
36 | Int corrupt(Int x String y) { x = x; return x;}
   |                   ^^^^^^
instead expected token classes are [COMMA, RPAREN]
Parse aborted due to above error.  To skip over error, try using lenient mode ('-lenient').
```


The above error message demonstrates a few things:
1. Firstly, error messages will print out the line and column number, and will print out the offending line, and place '^' under the offending token.  The number on the left hand side is the line number.  This is a powerful feature that allows the user to directly see why the parser cannot parse the code, and what they should change to make their code work.  All parser and lexer errors come with such error messages.  This is possible by imbuing each symbol with the location information.
2. Secondly, the parser can still continue after it encounters an error.  In the above example, 5 different errors are detected.  After each error, the parser tries to recover and continue to parse (by skipping over one or more tokens), and hence is able to find all the syntax errors.  This is helpful so the user does not need to re-compile after fixing the error one-by-one.  This is done with judicious use of the error token in Cup.
3. Thirdly, there is an option to parse the program in "lenient" mode (see the last line of the error message above).  When using lenient mode, the parser will still produce an AST, but error tokens will either be removed or replaced with `<<Error Expression>>`, so that the rest of the tree remains valid.  See the next section about lenient mode.

There are 5 tests that will lead to parse error.  They demonstrate the resilience of my parser to syntax errors, and the friendly output that is produced.  Each can be run separately using the `run.sh` script, e.g.:
```
./run.sh errors/error-recover
./run.sh errors/expr-error
./run.sh errors/lexer-error
./run.sh errors/capital-error
./run.sh errors/capital-error2
```

The expected output is also available in the `test/errors` directory.

## Lenient mode

Lenient mode should be used when we still want to construct a best-effort tree from a input Jlite source file that has syntax errors.

For the above example, part of the tree could look like this when parsed in lenient mode:

```
x=1000;
x=(-)[1000];
x=<<Error Expression>>;
x=[[[x,x](*),[x,x](/)](+),x](-);
```

To use lenient mode, you should use the given script, for example:
```
./lenient.sh errors/error-recover
```

It is also possible to pass the flag manually -- please look inside `lenient.sh` if you want to do that.

# Other notes

## Typechecking

There is no typechecking in my program, because that is not part of parsing.  It should probably be done in the next assignment when we have typechecking.  So something like `1 + "a"` currently parses successfully.

## Primitive types

We demand that a class name cannot be `Int`, `Bool`, `String`, or `Void`, so as to make it unambiguous when used as type names.  Hence, doing `new Int()` will be a parse error.  This is deliberate and is part of the grammar given, once we accept that a primitive type cannot be reused as a class name.
