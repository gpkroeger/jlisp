import java.util.ArrayList;

public class ProgramFunction extends ProgramObject {
    private String key;
    private ArrayList<String> args;
    private ArrayList<Expr> toks;
    private Repl scope;

    public ProgramFunction(String s, Repl r) {
        key = s;
        scope = r;
        args = new ArrayList<String>(0);
        toks = new ArrayList<Expr>(0);
    }

    public ProgramFunction(String s, ArrayList<String> args, ArrayList<Expr> tok, Repl r) {
        this.key = s;
        this.args = args;
        this.toks = tok;
        this.scope = r;
    }

    public void addArg(String s) {
        args.add(s);
    }

    public void addTok(Expr e) {
        toks.add(e);
    }

    public ProgramObjectType getType() {
        return ProgramObjectType.FUNCTION;
    }

    public Object getValue() {
        return null;
    }

    public String getKey() {
        return key;
    }

    public ProgramObject run(ArrayList<ProgramObject> arguments) {
        Jlisp j = new Jlisp();
        Repl repl = j.repl;
        try {
            assert(arguments.size() == args.size());
        } catch(Exception e) {
            Jlisp.failGracefully("Error, function: "+ key +" has mismatched number of args from declaration to call", 0);
        }
        for(int i = 0; i < arguments.size(); i++) {
            repl.addLit(args.get(i), arguments.get(i));
        }
        repl.putAll(scope.litsMap);
        ArrayList<ProgramObject> res = new ArrayList<ProgramObject>(0);
        for(int i = 0; i < toks.size(); i++) {
            if(toks.get(i).getType() == TokType.COND) {
                ArrayList<ProgramObject> lis = new ArrayList<ProgramObject>(1);
                lis.add(j.lazyProgrammifyCond(toks.get(i), repl));
                res.add(j.eval(toks.get(i), lis, repl));
            } else {
                res.add(j.eval(toks.get(i), j.programmify(toks.get(i).getChildren(), repl), repl));
            }
        }
        return res.get(0);
    }

    public String toString() {
        return "Function: " + key;
    }
}