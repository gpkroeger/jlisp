import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

public class Jlisp {
    public static ArrayList<ProgramObject> run(String input) {
        // Lexer lexie = new Lexer("(define findInTree (x tree)\n(cond\n(nil? tree) ()\n(eq? x (car tree)) t\n(< x (car tree)) (findInTree x (car (cdr tree)))\n(> x (car tree)) (findInTree x (cdr (cdr tree)))\nt ()\n)\n)");
        // Lexer lexie = new Lexer("(+ 1 2 3) (/ 12 2) (* 12 0.5) (- 13 7) (< 1 2) (> 2 1) (= 1 1)");
        Lexer lexie = new Lexer(input);
        ArrayList<Expr> lis = lexie.lex();
        ArrayList<Expr> asts = new ArrayList<Expr>(0);

        //FOR DEBUG USE, UNCOMMENT TO SEE TOKENIZER OUTPUT
        // for(Expr t : lis) {
        //     System.out.println(t);
        // }
        try {
            asts = Lexer.astify(lis);
        } catch (Exception e) {
            failGracefully(e.getMessage(), 0);
        }

        //FOR DEBUG USE, UNCOMMENT TO SEE OUTPUT OF AST
        // for(Expr child : asts) {
        //     System.out.println(child);
        // }

        // ProgramObject p = eval();
        // return str;
        ArrayList<ProgramObject> programOutput = new ArrayList<ProgramObject>(0);
        for(Expr e : asts) {
            ArrayList<Expr> children = e.getChildren();
            ArrayList<ProgramObject> args = programmify(children);
            programOutput.add(eval(e.getType(), args));
        }

        return programOutput;
    }

    public static ProgramObject eval(TokType fun, ArrayList<ProgramObject> args) {
        HashMap<TokType, Function<ArrayList<ProgramObject>, ProgramObject>> map = new Repl().getMap();
        ProgramObject res;
        Function<ArrayList<ProgramObject>, ProgramObject> func = map.get(fun);
        res = func.apply(args);
        return res;
    }

    public static ArrayList<ProgramObject> programmify(ArrayList<Expr> toks) {
        ArrayList<ProgramObject> res = new ArrayList<ProgramObject>(0);
        for(Expr e : toks) {
            switch(e.getType()) {
                case CLOSE_PAREN:
                case OPEN_PAREN:
                    failGracefully("programming error, parens still present after astify", e.getLineNumber());
                    break;
                case NUM:
                    try {
                        res.add(new ProgramNumber(Double.parseDouble(e.getValue())));
                    } catch(Exception f) {
                        failGracefully(f.getMessage(), -1);
                    }
                    break;
                // case STRING:
                //     res.add(new ProgramString(e.getValue()));
                default:
                    failGracefully("type was not accounted for in programmify", e.getLineNumber());
            }
        }
        return res;
    }

    public static void failGracefully(String errorMsg, int lineNum) {
        System.out.println(errorMsg);
        System.exit(1);
    }
}