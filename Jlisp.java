import java.util.ArrayList;

public class Jlisp {
    public static void main(String[] args) {
        Lexer lexie = new Lexer("(define findInTree (x tree)\n(cond\n(nil? tree) ()\n(eq? x (car tree)) 't\n(< x (car tree)) (findInTree x (car (cdr tree)))\n(> x (car tree)) (findInTree x (cdr (cdr tree)))\n't ()\n)\n)");
        ArrayList<Token> lis = lexie.lex();

        for(Token t : lis) {
            System.out.println(t);
        }
    }
}