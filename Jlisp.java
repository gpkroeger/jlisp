import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

public class Jlisp {
    public Repl repl;
    
    public Jlisp() {
        repl = new Repl();
    }

    public ArrayList<ProgramObject> run(String input) {
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

        // FOR DEBUG USE, UNCOMMENT TO SEE OUTPUT OF AST
        // for(Expr child : asts) {
        //     System.out.println(child);
        // }

        this.repl = new Repl();
        ArrayList<ProgramObject> programOutput = new ArrayList<ProgramObject>(0);
        for(Expr e : asts) {
            ArrayList<Expr> children = e.getChildren();
            ArrayList<ProgramObject> args = null;
            if(e.getType() == TokType.DEFINE) {
                ProgramFunction f = lazyProgrammifyFunc(e, repl);
                args = new ArrayList<ProgramObject>(1);
                args.add(f);
            }
            else if(e.getType() == TokType.COND) {
                args = new ArrayList<ProgramObject>(1);
                args.add(lazyProgrammifyCond(e, repl));
            }
            else {
                args = programmify(children, repl);
            }
            programOutput.add(eval(e, args, repl));
        }

        return programOutput;
    }

    public ProgramObject eval(Expr expr, ArrayList<ProgramObject> args, Repl repl) {
        HashMap<TokType, Function<ArrayList<ProgramObject>, ProgramObject>> map = repl.getMap();
        ProgramObject res;
        TokType fun = expr.getType();
        Function<ArrayList<ProgramObject>, ProgramObject> func = map.get(fun);
        if(func == null) {
            if(repl.litsMap.containsKey(expr.getValue()))
                return ((ProgramFunction)repl.litsMap.get(expr.getValue())).run(args);
            else
                return new ProgramLiteral(expr.getValue());
        }
        res = func.apply(args);
        if(res.getType() == ProgramObjectType.VARIABLE) {
            repl.addLit(((ProgramVariable)res).getKey(), res);
        } else if (res.getType() == ProgramObjectType.FUNCTION) {
            repl.addLit(((ProgramFunction)res).getKey(), res);
        }
        return res;
    }

    public ArrayList<ProgramObject> programmify(ArrayList<Expr> toks, Repl repl) {
        ArrayList<ProgramObject> res = new ArrayList<ProgramObject>(0);
        for(int i = 0; i < toks.size(); i++) {
            Expr e = toks.get(i);
            if(e.isList()) {
                ProgramObject value = repl.getLit(e.getValue());
                if(value != null) {
                    ArrayList<ProgramObject> args = programmify(e.getChildren(), repl);
                    res.add(((ProgramFunction)(value)).run(args));
                } else {
                    ArrayList<ProgramObject> args = programmify(e.getChildren(), repl);
                    res.add(eval(e, args, repl));
                }
            } else {
                switch(e.getType()) {
                    case CLOSE_PAREN:
                    case OPEN_PAREN:
                        failGracefully("programming error, parens still present after astify", e.getLineNumber());
                        break;
                    case EMPTY_LIST:
                        res.add(new ProgramList());
                        break;
                    case NUM:
                        try {
                            res.add(new ProgramNumber(Double.parseDouble(e.getValue())));
                        } catch(Exception f) {
                            failGracefully(f.getMessage(), -1);
                        }
                        break;
                    case STRING:
                        res.add(new ProgramString(e.getValue()));
                        break;
                    case LITERAL:
                        ProgramObject value = repl.getLit(e.getValue());
                        if(value != null)
                            res.add(value);
                        else {
                            // failGracefully("undeclared variable or function encountered while parsing", -1);
                            res.add(new ProgramLiteral(e.getValue()));
                        }
                        break;
                    default:
                        failGracefully("type: " + e.getType() + " was not accounted for in programmify", e.getLineNumber());
                }
            }
        }
        return res;
    }

    public ProgramFunction lazyProgrammifyFunc(Expr lis, Repl repl) {
        ArrayList<Expr> children = lis.getChildren();
        String key = null;
        ArrayList<String> args = null;
        ArrayList<Expr> expr = new ArrayList<Expr>(0);

        try {
            key = children.get(0).getValue();
            args = (((List)children.get(1)).getAll());
            for(int i = 2; i < children.size(); i++) {
                expr.add(children.get(i));
            }
        } catch(Exception e) {
            failGracefully("Invalid Function Definition", 0);
        }

        return new ProgramFunction(key, args, expr, repl);
    }

    public ProgramCond lazyProgrammifyCond(Expr lis, Repl repl) {
        ArrayList<Expr> children = lis.getChildren();
        ProgramCond cond = new ProgramCond(this);

        try {
            assert(children.size() % 2 == 0);

            for(int i = 0; i < children.size(); i+=2) {
                cond.addCondEval(children.get(i), children.get(i+1));
            }

        } catch(Exception e) {
            failGracefully("cond takes even number of args lineNum" + lis.getLineNumber() , 0);
        }

        return cond;
    }

    public static void failGracefully(String errorMsg, int lineNum) {
        System.out.println(errorMsg);
        System.exit(1);
    }
}