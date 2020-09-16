JFLAGS = -g -cp .;cup/java-cup-11b-runtime.jar
JC = javac

default: classes

CLASSES = \
	Compiler.java \
	Lexer.java \
	UnknownCharacterException.java \
	parser.java \
	sym.java \
	CommandArgumentException.java \
    tree/*.java

%.class: %.java Lexer.java parser.java sym.java
	$(JC) $(JFLAGS) $<

Lexer.java: jflex/jlang.flex
	jflex -nobak -d . jflex/jlang.flex

parser.java sym.java: cup/jlang.cup
	 java -jar cup/java-cup-11b.jar -destdir . cup/jlang.cup

# sym.java: parser.java

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class