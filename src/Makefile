JFLAGS = -g -cp .;cup/java-cup-11b-runtime.jar -Xlint:deprecation
JC = javac

default: classes

CLASSES = \
	Compiler.java \
	Lexer.java \
	UnknownCharacterException.java \
	parser.java \
	sym.java \
	CommandArgumentException.java

%.class: %.java Lexer.java parser.java sym.java
	$(JC) $(JFLAGS) $<

Lexer.java: jflex/minijava.flex
	jflex -d . jflex/minijava.flex

parser.java sym.java: cup/minijava.cup
	 java -jar cup/java-cup-11b.jar -destdir . cup/minijava.cup

# sym.java: parser.java

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class