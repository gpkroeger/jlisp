import java.util.ArrayList;

public class ProgramCond extends ProgramObject {
    private ArrayList<Expr> conds;
    private ArrayList<Expr> evals;
    Repl r;
    Jlisp j;

    public ProgramCond(Jlisp j) {
        this.j = j;
        r = j.repl;
        conds = new ArrayList<>(0);
        evals = new ArrayList<>(0);
    }

    public void addCondEval(Expr c, Expr e) {
        conds.add(c);
        evals.add(e);
    }

    public ProgramObject run() {
        ProgramObject result=null;
        for(int i = 0; i < conds.size(); i++) {
            ArrayList<ProgramObject> args = j.programmify(conds.get(i).getChildren(), r);
            ProgramObject condRes = j.eval(conds.get(i), args, r);
            if(condRes != null && !(condRes.isNil())) {
                args = j.programmify(evals.get(i).getChildren(), r);
                if(args.size() == 0) {
                    ArrayList<Expr> ffs = new ArrayList<>(1);
                    Expr evalExpr = evals.get(i);
                    if(evalExpr.getType() == TokType.LIST) {
                        ffs.add(((List)evalExpr).getLeft());
                    } else {
                        ffs.add(evalExpr);
                    }
                    args = j.programmify(ffs, r);
                }
                result = j.eval(evals.get(i), args, r);
                break;
            }
        }
        if(result == null) {
            result = new ProgramBool(false);
        }
        
        return result;
    }

    public ProgramObjectType getType() {
        return this.run().getType();
    }

    public String toString() {
        return this.run().toString();
    }

    public Object getValue() {
        return this.run().getValue();
    }
}
