import java_cup.runtime.Symbol;

// Issues:
// - not sure if we need to accept escaped double quote
// - decimal and hexadecimal char literals - need leading '0'? Is there a precise spec for this?
// - hex literal - [a-f] or [A-F]?
// how can null mean the empty string ("")? since null is also used for class instances


%%

%public
%class Lexer
%unicode
%cup
%line
%column
%throws UnknownCharacterException

%{
  StringBuffer string = new StringBuffer();

  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
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
<YYINITIAL> "null"               { return symbol(sym.NULL); }
<YYINITIAL> "true"               { return symbol(sym.TRUE); }
<YYINITIAL> "false"              { return symbol(sym.FALSE); }
<YYINITIAL> "class"              { return symbol(sym.CLASS); }
<YYINITIAL> "this"               { return symbol(sym.THIS); }
<YYINITIAL> "new"                { return symbol(sym.NEW); }
<YYINITIAL> "return"             { return symbol(sym.RETURN); }
<YYINITIAL> "if"                 { return symbol(sym.IF); }
<YYINITIAL> "else"               { return symbol(sym.ELSE); }
<YYINITIAL> "while"              { return symbol(sym.WHILE); }
<YYINITIAL> "readln"             { return symbol(sym.READLN); }
<YYINITIAL> "println"            { return symbol(sym.PRINTLN); }


<YYINITIAL> {
  /* identifiers */
  {Identifier}                   { return symbol(sym.IDENTIFIER, yytext()); }
  
  /* class name (we treat primitive types as classes for now) */
  {ClassName}                    { return symbol(sym.CLASSNAME, yytext()); }
  
  /* literals */
  {IntegerLiteral}               { return symbol(sym.INTEGER_LITERAL, Integer.valueOf(yytext())); }
  \"                             { string.setLength(0); yybegin(STRING); }

  /* operators */
  "="                            { return symbol(sym.ASSIGN); }
  "=="                           { return symbol(sym.EQ); }
  "!="                           { return symbol(sym.NE); }
  "<"                            { return symbol(sym.LT); }
  "<="                           { return symbol(sym.LE); }
  ">"                            { return symbol(sym.GT); }
  ">="                           { return symbol(sym.GE); }
  "+"                            { return symbol(sym.PLUS); }
  "-"                            { return symbol(sym.MINUS); }
  "*"                            { return symbol(sym.TIMES); }
  "/"                            { return symbol(sym.DIVIDE); }
  "."                            { return symbol(sym.DOT); }
  ","                            { return symbol(sym.COMMA); }
  "!"                            { return symbol(sym.NEGATION); }
  "&&"                           { return symbol(sym.CONJUNCTION); }
  "||"                           { return symbol(sym.DISJUNCTION); }
  "("                            { return symbol(sym.LPAREN); }
  ")"                            { return symbol(sym.RPAREN); }
  "{"                            { return symbol(sym.LCURLY); }
  "}"                            { return symbol(sym.RCURLY); }

  /* semicolon */
  ";"                            { return symbol(sym.SEMI); }

  /* comment */
  {Comment}                      {}

  /* whitespace */
  {WhiteSpace}                   {}
}


<STRING> {
  \"                             { yybegin(YYINITIAL);
                                   return symbol(sym.STRING_LITERAL,
                                   string.toString()); }
  [^\n\r\"\\]+                   { string.append( yytext() ); }
  \\t                            { string.append('\t'); }
  \\n                            { string.append('\n'); }
  \\b                            { string.append('\b'); }
  \\r                            { string.append('\r'); }
  \\\"                           { string.append('\"'); }
  \\                             { string.append('\\'); }
  \\ {DecCharLiteral}            { string.append( decToChar(yytext()) ); }
  \\ {HexCharLiteral}            { string.append( hexToChar(yytext()) ); }
}


/* error fallback */
[^]                              { throw new UnknownCharacterException(yytext()); }
