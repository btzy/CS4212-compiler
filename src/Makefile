ifdef OS
	JFLAGS = -g -cp .;cup/java-cup-11b-runtime.jar
	RM = del
else
	JFLAGS = -g -cp .:cup/java-cup-11b-runtime.jar
	RM = rm -f
endif

JC = javac

default: classes

CLASSES = \
	Compiler.java \
	Lexer.java \
	UnknownCharacterException.java \
	parser.java \
	sym.java \
	CommandArgumentException.java \
	$(wildcard util/*.java) \
	$(wildcard ir3/*.java) \
	$(wildcard tree/*.java)

%.class: %.java Lexer.java parser.java sym.java $(wildcard util/*.java) $(wildcard ir3/*.java) $(wildcard tree/*.java)
	$(JC) $(JFLAGS) $<

tree/%.class: tree/%.java $(wildcard ir3/*.java) $(wildcard util/*.java)
	$(JC) $(JFLAGS) $<

ir3/%.class: ir3/%.java $(wildcard util/*.java)
	$(JC) $(JFLAGS) $<

util/%.class: util/%.java
	$(JC) $(JFLAGS) $<

Lexer.java: jflex/jlang.flex
	java -Xmx128m -jar "jflex/jflex-1.8.2/lib/jflex-full-1.8.2.jar" -nobak -d . jflex/jlang.flex

parser.java sym.java: cup/jlang.cup
	java -jar cup/java-cup-11b.jar -locations -destdir . cup/jlang.cup

# sym.java: parser.java

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
	$(RM) "tree/*.class"
	$(RM) ir3/*.class
	$(RM) util/*.class
	$(RM) Lexer.java
	$(RM) parser.java
	$(RM) sym.java
