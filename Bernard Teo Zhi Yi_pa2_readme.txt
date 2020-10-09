
CS4212 Programming Assignment 2
Bernard Teo Zhi Yi



# Introduction

This submission builds on the parser from assignment 1, and processes the syntax tree with a type checker and a distinct-name checker.  The type-checked syntax tree is then converted into IR3 code as specified in the task statement.  You can input a Jlite source file, and it will produce the IR3 three-address code, and print it with neat formatting and proper indentation.  There are no known bugs --- it produces the correct IR3 code on all valid inputs, and it identifies all syntactic and semantic errors correctly.

The implementation of the type checker and IR3 generator goes beyond the requirement for assignment 2.  Firstly, **overloaded methods** are allowed to occur, and they are handled in a similar way to Java.  Secondly, each local variable is given an integer id in increasing order (unique within a function), allowing efficient manipulation and representation, and perhaps making it easier to do optimisations and generate assembly at a later stage.  The ids are mapped back to strings when printing the IR3 code.

All kinds of errors (e.g. syntax errors, type errors, duplicate name checking, etc) will be detected, and my program will **print out details about the error, including highlighting the relevant text from the source file**.  In most situations, my compiler will also provide more information, such as the relevant declaration and its location.  After an error, the compiler will continue processing the rest of the program, allowing multiple errors to be reported at one go.

# Content of the submission
The `src` directory contains all the files of my submission, including appropriate versions of the jflex and cup binaries.

Subdirectories:
- `src`: code that drives the compiler
- `src/tree`: classes to represent the syntax tree of the program
- `src/ir3`: classes to represent the IR3 code and type-checking functions
- `src/util`: some utility classes for error reporting
- `src/test`: contains tests
- `src/test/errors`: contains tests that are semantic errors
- `src/test/asg1`: contains tests from assignment 1 (you can ignore this)
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

Then will take a while to compile.  Be patient, and it should be done after a while.

## Run
You can use the `run.sh` script, which will use the specified Jlite source file from the test folder.  For example to run the `test/test-all-grammar.j` file, you can do:
```
./run.sh test-all-grammar
```

Remember that you do not add the file extension here.

Alternatively, you may type in the command manually (see the content of `run.sh` for the exact syntax).

# Tests
TODO
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

# Brief overview of the compiler
The following is a brief overview of how the compiler works.

We start with a syntax tree (that was produced by the parser).

Semantic checking and IR3 codegen is done together.  The complier processes the syntax tree, generating type information along the way (stored in a separate data structure), and uses that information to generate IR3 code.

A first pass is done on the classes to generate the metadata for each class (containing the list of fields and their types, as well as the list of functions and their signatures).  It keeps the fields in an ordered list (ordered within the class), and the methods are extracted from the class and stored as a global list (with the extra 'this' parameter prepended).  Overload resolution tables are also generated at this stage.  We can think of the metadata as an extended version of class descriptors (it is the class "ClassDescriptor" in my code).  Since fields and methods are in lists, they are implicitly indexed, allowing the second pass to look up names and refer to fields and methods by their indices.  Duplicate class names, field names, and method names are checked at this point.

A second pass is done over each of the methods of each class, doing type-checking and then emitting the IR3 code for each method.  Type-checking is done on every statement and expression in the method, and it verifies whether each operator or function call can be done with the given argument types.  The type-checking is done from the leaf nodes up to the entire statement (with the exception of "null", where the type is "coerced" by the parent, see the next section for more information).  Because we have access to the class descriptors from the first pass, we can lookup the type of each variable we encounter.

Each expression (or sub-expression) has its IR3 code generated immediately after it is type-checked.  It is actually a more compact form or IR3 code, where fields and methods are referenced by index instead of by name.

If both passes succeeds, my compiler will then print out the IR3 code, converting the indices back to human-readable field/method names to comply with the output requirements.

In both passes, the compiler tries to recover from semantic errors as soon as possible (by ignoring the offending item), and so is able to report multiple errors in a single execution.  However, if the first pass has errors, the compiler will not go on to the second pass.  This is because a failure of the first pass means that some fields or methods might be missing, and this will lead to many errors on the second pass if we still attempt to proceed on.  See the error handling section for more details.

# Features
Some of the more interesting features of my compiler are highlighted here, in the hope of gaining me more marks :)

Of course, my compiler does produce correct IR3 code for all valid Jlite programs, but that should be expected of everybody.  This section is for the more interesting features.

## Handling of "null"
As required by the Jlite specification, null represents two possible things depending on the context:
- Indicating the lack of an object, or a "null object", which is usual Java behaviour
- Indicating an empty string.

In particular, this means that `null == ""` should return `true`, and `null + null` should return `null` (or `""`) (since string concatenation is allowed with the `+` operator.

My compiler treats the `null` expression as a special kind of type (which I call the "null type").  The null type can be present in the syntax tree, but cannot exist after type-checking as completed.  Specifically, as part of type-checking, the actual type of each `null` expression is determined, and the actual type is attached to the expression (the actual type can be either `String` or some class type).

The actual type is determined via a type coercion process, which depends on the context that the value is used in.  These are some examples:
- When assigning to an variable (e.g. `x = null;`), the `null` is coerced to the type of the variable being assigned to
- When used as an argument to an operator (e.g. `null + "test"`), the `null` is coerced to the type required by the operator
- When used as an argument to a method call (e.g. `f(null)`), the `null` is coerced to the type required by the method being called (see the method overloading section for specifics)
- When used in a return statement (e.g. `return null;`), the `null` is coerced to the return type of the method

The coercion process works in all cases where it is reasonable to do so.  (If you want to look at the compiler code, search for the "imbueType" function.)

## Method overloading
My compiler implements the bonus requirement of proper method overloading.

Method overloading works like how it does in Java.  For example, we could have the following class definition:
```
class A {
    Void f(Bool b) { /* some code */ }
    Void f(String s) { /* some code */ }
    Void f(String s1, String s2) { /* some code */ }
    Void f(A a) { /* some code */ }
    Void f(Main m) { /* some code */ }
}
```

Suppose `x` is an object of type `A`.  Then, `x.f(true)` will call the first overload, `x.f("test")` will call the second overload, etc.  We could also have overloads with different number of arguments, as per the example class above.

In a scenario without overloading, we might distinguish methods by just their class and name.  However, to make overloading work, we distinguish methods by the types of their arguments as well.  For example, the first overload would be treated as something like "A.f(Bool)", or equivalently, after conversion to regular free functions, it is "f(A,Bool)".  As another example, the third overload above would become "f(A,String,String)".  This transformation is done in the first pass.

In the second pass, when we actually encounter call expressions and call statements, we look at the list of actual argument types (some of which could be `null`), and find the list of matching function declarations (also considering null coercions where possible).  If there is exactly one match, then the call is binded to that unique matching function.  If there are no matches or multiple matches, then it is an error and the compiler reports the appropriate error.  Error information is detailed, and will display the list of matching candidates and their locations in the source file.

TODO: which file has overloading

### Name mangling
A side effect of allowing method overloading is that we need to mangle function names in the IR3 code, in order to ensure that the function names are unique.  We use a simple mangling scheme, separating the class name, method name, and parameters types by `%`.  For ease of human consumption, the parameter types are separated from the method name by `%%%`.  For example, `A.f(String, String)` is mangled as `%A%f%%%A%String%String` (note: the additional `A` is the implicit "this" parameter; it may be redundant but it makes it easier to understand the actual list of function parameters, and it does not cause any harm).

## Implicit "this"

## Fields and methods with the same name

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
