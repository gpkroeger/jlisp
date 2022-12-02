import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

public class Jlisp {
    public static String run(String input) {
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

        String str = eval(asts);
        return str;
    }

    public static String eval(ArrayList<Expr> asts) {
        HashMap<TokType, Function<ArrayList<Expr>, ProgramObject>> map = new Repl().getMap();
        String str = "";
        for(Expr item : asts) {
            Function<ArrayList<Expr>, ProgramObject> func = map.get(item.getType());
            ArrayList<Expr> args = item.getChildren();
            str+=(func.apply(args));
        }
        return str;
    }

    public static void failGracefully(String errorMsg, int lineNum) {
        System.out.println(errorMsg + " Line Number: " + lineNum);
        System.exit(1);
    }
}