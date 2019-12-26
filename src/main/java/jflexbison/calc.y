%language "Java"
%define api.prefix {MyCalcBison}
%define api.parser.class {MyCalcBison}
%define api.parser.public
%define package {jflexbison}

%code{
    private String notation ="";
    private boolean error = false;

    public int divide (int a, int b){return (int)Math.floor((double) a / (double) b);}
    public int modulo (int a, int b){return a - (int)Math.floor(divide(a,b)) * b;}

    public static void main(String argv[]) {
            if (argv.length == 0) {
                System.out.println("Usage : java MyCalc [ --encoding <name> ] <inputfile(s)>");
            }
            else {
                int firstFilePos = 0;
                String encodingName = "UTF-8";
                if (argv[0].equals("--encoding")) {
                    firstFilePos = 2;
                    encodingName = argv[1];
                    try {
                        java.nio.charset.Charset.forName(encodingName); // Side-effect: is encodingName valid?
                    } catch (Exception e) {
                        System.out.println("Invalid encoding '" + encodingName + "'");
                        return;
                    }
                }
                for (int i = firstFilePos; i < argv.length; i++) {
                    MyCalc scanner = null;
                    try {
                        java.io.FileInputStream stream = new java.io.FileInputStream(argv[i]);
                        java.io.Reader reader = new java.io.InputStreamReader(stream, encodingName);
                        scanner = new MyCalc(reader);
                        MyCalcBison myCalcBison = new MyCalcBison(scanner);
                        myCalcBison.parse();
                    }
                    catch (java.io.FileNotFoundException e) {
                        System.out.println("File not found : \""+argv[i]+"\"");
                    }
                    catch (java.io.IOException e) {
                        System.out.println("IO error scanning file \""+argv[i]+"\"");
                        System.out.println(e);
                    }
                    catch (Exception e) {
                        System.out.println("Unexpected exception:");
                        e.printStackTrace();
                    }
                }
            }
        }
}


%token ERROR
%token END_OF_LINE
%token LEFT_BRACKET
%token RIGHT_BRACKET
%token  NUMBER
%left PLUS MINUS
%left MULTIPLICATION DIVIDE MODULO SQRT
%precedence NEG
%right POWER

%%
input:
    %empty
    | input line
;
line: EXPRT END_OF_LINE 	{if (!error)  System.out.println(" "+notation+" \033[1;32m = "+yyval+"\033[0m"); error = false; notation = "";}
    | error END_OF_LINE	{ System.out.println("err"); error = false; notation = "";}
;
EXPRT: NUMBER                   { notation += $1 + " "; $$ = $1; }
    | SQRT EXPRT           	 { $$ = Math.sqrt(Integer.parseInt(""+$2)) ; }
    | EXPRT PLUS EXPRT     { notation += "+ "; $$ = Integer.parseInt(""+$1) + Integer.parseInt(""+$3); }
    | EXPRT MINUS EXPRT           { notation += "- "; $$ = Integer.parseInt(""+$1) - Integer.parseInt(""+$3); }
    | EXPRT MULTIPLICATION EXPRT  {notation += "* "; $$ = Integer.parseInt(""+$1) * Integer.parseInt(""+$3); }
    | EXPRT DIVIDE EXPRT          {notation += "/ "; if (Integer.parseInt(""+$3) == 0) { System.out.print("You can't div by 0"); } else {$$ = divide(Integer.parseInt(""+$1), Integer.parseInt(""+$3)); }}
    | EXPRT MODULO EXPRT          {notation += "% "; if (Integer.parseInt(""+$3) == 0) {System.out.print("You can't mod 0"); } else {$$ = modulo(Integer.parseInt(""+$1), Integer.parseInt(""+$3));}}
    | MINUS EXPRT %prec NEG            {notation += "- "; $$ = 0-Integer.parseInt(""+$2);}
    | EXPRT POWER EXPRT           { notation += "^ "; $$ = (int)Math.pow(Integer.parseInt(""+$1), Integer.parseInt(""+$3)); }
    | LEFT_BRACKET EXPRT RIGHT_BRACKET { $$ = $2; }
%%

