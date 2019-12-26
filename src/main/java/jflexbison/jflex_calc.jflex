package jflexbison;

%%
%implements MyCalcBison.Lexer
%class MyCalc
%standalone
%line
%column

%{
  @Override
  public void yyerror(String msg) {
    System.err.println(msg);
  }

    @Override
    public Object getLVal() {
      return yytext();
    }

    public boolean isZzAtBOL() {
    return zzAtBOL;
  };
%}
escaped_newline = \\\n

%%

^#(.|{escaped_newline})*\n  {}
{escaped_newline}            {}
[[:blank:]]+ 	{}
[0-9]+	        { return NUMBER; }
"sqrt"		    { return SQRT;}
"+"             { return PLUS; }
"-"             { return MINUS; }
"*"             { return MULTIPLICATION; }
"/"             { return DIVIDE; }
"%"             { return MODULO; }
"^"             { return POWER; }
\(              { return LEFT_BRACKET; }
\)              { return RIGHT_BRACKET; }
\n              { return END_OF_LINE; }
.               { return ERROR; }