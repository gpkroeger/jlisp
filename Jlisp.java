import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

public class Jlisp {
    public static void main(String[] args) {
        // Lexer lexie = new Lexer("(define findInTree (x tree)\n(cond\n(nil? tree) ()\n(eq? x (car tree)) t\n(< x (car tree)) (findInTree x (car (cdr tree)))\n(> x (car tree)) (findInTree x (cdr (cdr tree)))\nt ()\n)\n)");
        Lexer lexie = new Lexer("(+ 1 2 3) (/ 12 2) (* 12 0.5) (- 13 7) (< 1 2) (> 2 1) (= 1 1)");
        ArrayList<Expr> lis = lexie.lex();
        ArrayList<Expr> asts = new ArrayList<Expr>(0);

        for(Expr t : lis) {
            System.out.println(t);
        }
        try {
            asts = Lexer.astify(lis);
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }

        for(Expr child : asts) {
            System.out.println(child);
        }

        eval(asts);
    }

    public static void eval(ArrayList<Expr> asts) {
        HashMap<TokType, Function<ArrayList<Expr>, Object>> map = new Repl().getMap();

        for(Expr item : asts) {
            Function<ArrayList<Expr>, Object> func = map.get(item.getType());
            ArrayList<Expr> args = item.getChildren();
            System.out.println(func.apply(args));
        }
    }
}