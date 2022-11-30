import java.util.ArrayList;

public class Jlisp {
    public static void main(String[] args) {
        Lexer lexie = new Lexer("(define findInTree (x tree)\n(cond\n(nil? tree) ()\n(eq? x (car tree)) 't\n(< x (car tree)) (findInTree x (car (cdr tree)))\n(> x (car tree)) (findInTree x (cdr (cdr tree)))\n't ()\n)\n)");
        // Lexer lexie = new Lexer("(define helloWorld x (print 'Hello World x'))");
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
    }
}