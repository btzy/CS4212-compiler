import java_cup.runtime.Symbol;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;
import java_cup.runtime.ComplexSymbolFactory.Location;
import java.io.InputStreamReader;


%%

%public
%class Lexer
%unicode
%cup
%line
%column
%throws UnknownCharacterException

%{
  public Lexer(InputStreamReader reader, ComplexSymbolFactory sf, String filename) {
	this(reader);
	this.sf = sf;
    this.filename = filename;
  }

  private ComplexSymbolFactory sf;
  private StringBuffer string = new StringBuffer();
  private String filename;

  private Symbol symbol(String name, int type) {
    return sf.newSymbol(name, type, new Location(yyline+1, Math.max(0, yycolumn)), new Location(yyline+1, yycolumn+yylength()));
  }
  private Symbol symbol(String name, int type, Object value) {
    return sf.newSymbol(name, type, new Location(yyline+1, Math.max(0, yycolumn)), new Location(yyline+1, yycolumn+yylength()), value);
  }

  private static char decToChar(String value) {
    return (char) Integer.parseUnsignedInt(value, 1, value.length(), 10);
  }
  private static char hexToChar(String value) {
    return (char) Integer.parseUnsignedInt(value, 2, value.length(), 16);
  }
%}



LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]

Identifier = [a-z] [A-Za-z0-9_]*

ClassName = [A-Z] [A-Za-z0-9_]*

IntegerLiteral = [0-9]+

DecCharLiteral = [0-9]{3}
HexCharLiteral = x [0-9a-fA-F]{2}

Comment = {BlockComment} | {NormalComment}
BlockComment = "/*" ~"*/"
NormalComment = "//" {InputCharacter}* {LineTerminator}?


%state STRING

%%


/* keywords */
<YYINITIAL> "null"               { return symbol("null", sym.NULL); }
<YYINITIAL> "true"               { return symbol("true", sym.TRUE); }
<YYINITIAL> "false"              { return symbol("false", sym.FALSE); }
<YYINITIAL> "class"              { return symbol("class", sym.CLASS); }
<YYINITIAL> "this"               { return symbol("new", sym.THIS); }
<YYINITIAL> "new"                { return symbol("new", sym.NEW); }
<YYINITIAL> "return"             { return symbol("return", sym.RETURN); }
<YYINITIAL> "if"                 { return symbol("if", sym.IF); }
<YYINITIAL> "else"               { return symbol("else", sym.ELSE); }
<YYINITIAL> "while"              { return symbol("while", sym.WHILE); }
<YYINITIAL> "readln"             { return symbol("readln", sym.READLN); }
<YYINITIAL> "println"            { return symbol("println", sym.PRINTLN); }
<YYINITIAL> "Int"                { return symbol("Int", sym.INT); }
<YYINITIAL> "Bool"               { return symbol("Bool", sym.BOOL); }
<YYINITIAL> "String"             { return symbol("String", sym.STRING); }
<YYINITIAL> "Void"               { return symbol("Void", sym.VOID); }


<YYINITIAL> {
  /* identifiers */
  {Identifier}                   { return symbol("identifier", sym.IDENTIFIER, yytext()); }
  
  /* class name (we treat primitive types as classes for now) */
  {ClassName}                    { return symbol("class_name", sym.CLASSNAME, yytext()); }
  
  /* literals */
  {IntegerLiteral}               { return symbol("integer_literal", sym.INTEGER_LITERAL, Integer.valueOf(yytext())); }
  \"                             { string.setLength(0); yybegin(STRING); }

  /* operators */
  "="                            { return symbol("\"=\"", sym.ASSIGN); }
  "=="                           { return symbol("\"==\"", sym.EQ); }
  "!="                           { return symbol("\"!=\"", sym.NE); }
  "<"                            { return symbol("\"<\"", sym.LT); }
  "<="                           { return symbol("\"<=\"", sym.LE); }
  ">"                            { return symbol("\">\"", sym.GT); }
  ">="                           { return symbol("\">=\"", sym.GE); }
  "+"                            { return symbol("\"+\"", sym.PLUS); }
  "-"                            { return symbol("\"-\"", sym.MINUS); }
  "*"                            { return symbol("\"*\"", sym.TIMES); }
  "/"                            { return symbol("\"/\"", sym.DIVIDE); }
  "."                            { return symbol("\".\"", sym.DOT); }
  ","                            { return symbol("\",\"", sym.COMMA); }
  "!"                            { return symbol("\"!\"", sym.NEGATION); }
  "&&"                           { return symbol("\"&&\"", sym.CONJUNCTION); }
  "||"                           { return symbol("\"||\"", sym.DISJUNCTION); }
  "("                            { return symbol("\"(\"", sym.LPAREN); }
  ")"                            { return symbol("\")\"", sym.RPAREN); }
  "{"                            { return symbol("\"{\"", sym.LCURLY); }
  "}"                            { return symbol("\"}\"", sym.RCURLY); }

  /* semicolon */
  ";"                            { return symbol("\";\"", sym.SEMI); }

  /* comment */
  {Comment}                      {}

  /* whitespace */
  {WhiteSpace}                   {}
}


<STRING> {
  \"                             { yybegin(YYINITIAL);
                                   return symbol("", sym.STRING_LITERAL,
                                   string.toString()); }
  [^\n\r\"\\]+                   { string.append( yytext() ); }
  \\t                            { string.append('\t'); }
  \\n                            { string.append('\n'); }
  \\b                            { string.append('\b'); }
  \\r                            { string.append('\r'); }
  \\\"                           { string.append('\"'); }
  \\\\                           { string.append('\\'); }
  \\ {DecCharLiteral}            { string.append( decToChar(yytext()) ); }
  \\ {HexCharLiteral}            { string.append( hexToChar(yytext()) ); }
}


/* error fallback */
[^]                              { throw new UnknownCharacterException(yytext(), filename, yyline + 1, yycolumn, yyline + 1, yycolumn + yylength()); }

/* EOF */
<<EOF>>                          { return symbol("eof", sym.EOF); }