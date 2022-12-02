import java.util.ArrayList;
import java.util.function.Function;
import java.util.HashMap;

public class Repl {
    public final static Function<ArrayList<Expr>, ProgramObject> add = new Function<ArrayList<Expr>, ProgramObject>() {
        public ProgramObject apply(ArrayList<Expr> args) {
            Double sum = 0.0;
            for (Expr t : args) {
                try {
                    sum+=Double.parseDouble(t.getValue());
                } catch(Exception e) {
                    Jlisp.failGracefully("Syntax Error: + takes only numeric values as arguments", t.getLineNumber());
                }
            }
            return new ProgramNumber(sum);
        }
    };
    public final static Function<ArrayList<Expr>, ProgramObject> subtract = new Function<ArrayList<Expr>, ProgramObject>() {
        public ProgramObject apply(ArrayList<Expr> args) {
            Double cur = null;
            int i = 0;
            try {
                cur = Double.parseDouble(args.get(0).getValue());
                for(i = 1; i < args.size(); i++) {
                    cur-=Double.parseDouble(args.get(i).getValue());
                }
            } catch(Exception e) {
                Jlisp.failGracefully("Syntax Error: - takes only numeric values as arguments", args.get(i).getLineNumber());   
            }
            return new ProgramNumber(cur);
        }
    };
    public final static Function<ArrayList<Expr>, ProgramObject> divide = new Function<ArrayList<Expr>, ProgramObject>() {
        public ProgramNumber apply(ArrayList<Expr> args) {
            Double cur = null;
            int i = 0;
            try {
                cur = Double.parseDouble(args.get(0).getValue());
                for(i = 1; i < args.size(); i++) {
                    cur/=Double.parseDouble(args.get(i).getValue());
                }
            } catch(Exception e) {
                Jlisp.failGracefully("Syntax Error: / takes only numeric values as arguments", args.get(i).getLineNumber());   
            }
            return new ProgramNumber(cur);
        }
    };
    public final static Function<ArrayList<Expr>, ProgramObject> multiply = new Function<ArrayList<Expr>, ProgramObject>() {
        public ProgramObject apply(ArrayList<Expr> args) {
            Double cur = null;
            int i = 0;
            try {
                cur = Double.parseDouble(args.get(0).getValue());
                for(i = 1; i < args.size(); i++) {
                    cur*=Double.parseDouble(args.get(i).getValue());
                }
            } catch(Exception e) {
                Jlisp.failGracefully("Syntax Error: * takes only numeric values as arguments", args.get(i).getLineNumber());   
            }
            return new ProgramNumber(cur);
        }
    };

    public final static Function<ArrayList<Expr>, ProgramObject> gt = new Function<ArrayList<Expr>, ProgramObject>() {
        public ProgramObject apply(ArrayList<Expr> args) {
            try {
            Double a = Double.parseDouble(args.get(0).getValue());
            Double b = Double.parseDouble(args.get(1).getValue());
            return new ProgramBool(a > b);
            } catch (Exception e) {
                Jlisp.failGracefully("Syntax Error: > takes only two numberic values", args.get(0).getLineNumber());   
            }
            return new ProgramBool(false);
        }
    };

    public final static Function<ArrayList<Expr>, ProgramObject> eq = new Function<ArrayList<Expr>, ProgramObject>() {
        public ProgramObject apply(ArrayList<Expr> args) {
            try {
                Double a = Double.parseDouble(args.get(0).getValue());
                Double b = Double.parseDouble(args.get(1).getValue());
                return new ProgramBool(Math.abs(a - b) < 0.0001);
            } catch (Exception e) {
                Jlisp.failGracefully("Syntax Error: = takes only two numberic values", args.get(0).getLineNumber());
            }
            return new ProgramBool(false);
        }
    };

    public final static Function<ArrayList<Expr>, ProgramObject> lt = new Function<ArrayList<Expr>, ProgramObject>() {
        public ProgramObject apply(ArrayList<Expr> args) {
            try {
                Double a = Double.parseDouble(args.get(0).getValue());
                Double b = Double.parseDouble(args.get(1).getValue());
                return new ProgramBool(a < b);
            } catch (Exception e) {
                Jlisp.failGracefully("Syntax Error: < takes only two numberic values", args.get(0).getLineNumber());
            }
            return new ProgramBool(false);
        }
    };

    public HashMap<TokType, Function<ArrayList<Expr>, ProgramObject>> map;

    public Repl() {
        map = new HashMap<TokType, Function<ArrayList<Expr>, ProgramObject>>();
        map.put(TokType.PLUS, add);
        map.put(TokType.MINUS, subtract);
        map.put(TokType.DIVIDE, divide);
        map.put(TokType.TIMES, multiply);
        map.put(TokType.LESS_THAN, lt);
        map.put(TokType.EQUALS, eq);
        map.put(TokType.GREATER_THAN, gt);
    }

    public HashMap<TokType, Function<ArrayList<Expr>, ProgramObject>> getMap() {
        return map;
    }

}

