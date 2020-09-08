// JFlex example from the user Manual

import java_cup.runtime.Symbol;

// Issues:
// - not sure if we need to accept escaped double quote
// - decimal and hexadecimal char literals - need leading '0'? Is there a precise spec for this?
// - hex literal - [a-f] or [A-F]?

/** Lexer of a very minimal version of the Java programming language. */

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
<YYINITIAL> "abstract"           { return symbol(sym.ABSTRACT); }
<YYINITIAL> "boolean"            { return symbol(sym.BOOLEAN); }
<YYINITIAL> "break"              { return symbol(sym.BREAK); }


<YYINITIAL> {
  /* identifiers */
  {Identifier}                   { return symbol(sym.IDENTIFIER, yytext().toString()); }

  /* literals */
  {IntegerLiteral}               { return symbol(sym.INTEGER_LITERAL); }
  \"                             { string.setLength(0); yybegin(STRING); }

  /* operators */
  "="                            { return symbol(sym.EQ); }
  "=="                           { return symbol(sym.EQEQ); }
  "+"                            { return symbol(sym.PLUS); }

  /* comments */
  {Comment}                      { /* ignore */ }

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }
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
