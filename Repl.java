import java.util.ArrayList;
import java.util.function.Function;
import java.util.HashMap;

public class Repl {
    public final static Function<ArrayList<ProgramObject>, ProgramObject> add = new Function<ArrayList<ProgramObject>, ProgramObject>() {
        public ProgramObject apply(ArrayList<ProgramObject> args) {
            Double sum = 0.0;
            for (ProgramObject t : args) {
                try {
                    sum += (Double)(t.getValue());
                } catch(Exception e) {
                    Jlisp.failGracefully("Syntax Error: + takes only numeric values as arguments", -1);
                }
            }
            return new ProgramNumber(sum);
        }
    };
    public final static Function<ArrayList<ProgramObject>, ProgramObject> subtract = new Function<ArrayList<ProgramObject>, ProgramObject>() {
        public ProgramObject apply(ArrayList<ProgramObject> args) {
            Double cur = null;
            int i = 0;
            try {
                cur = (Double)(args.get(0).getValue());
                for(i = 1; i < args.size(); i++) {
                    cur-=(Double)(args.get(i).getValue());
                }
            } catch(Exception e) {
                Jlisp.failGracefully("Syntax Error: - takes only numeric values as arguments", -1);   
            }
            return new ProgramNumber(cur);
        }
    };
    public final static Function<ArrayList<ProgramObject>, ProgramObject> divide = new Function<ArrayList<ProgramObject>, ProgramObject>() {
        public ProgramNumber apply(ArrayList<ProgramObject> args) {
            Double cur = null;
            int i = 0;
            try {
                cur = (Double)(args.get(0).getValue());
                for(i = 1; i < args.size(); i++) {
                    cur/=(Double)(args.get(i).getValue());
                }
            } catch(Exception e) {
                Jlisp.failGracefully("Syntax Error: / takes only numeric values as arguments", -1);   
            }
            return new ProgramNumber(cur);
        }
    };
    public final static Function<ArrayList<ProgramObject>, ProgramObject> multiply = new Function<ArrayList<ProgramObject>, ProgramObject>() {
        public ProgramObject apply(ArrayList<ProgramObject> args) {
            Double cur = null;
            int i = 0;
            try {
                cur = (Double)(args.get(0).getValue());
                for(i = 1; i < args.size(); i++) {
                    cur*=(Double)(args.get(i).getValue());
                }
            } catch(Exception e) {
                Jlisp.failGracefully("Syntax Error: * takes only numeric values as arguments", -1);   
            }
            return new ProgramNumber(cur);
        }
    };

    public final static Function<ArrayList<ProgramObject>, ProgramObject> gt = new Function<ArrayList<ProgramObject>, ProgramObject>() {
        public ProgramObject apply(ArrayList<ProgramObject> args) {
            try {
            Double a = (Double)(args.get(0).getValue());
            Double b = (Double)(args.get(1).getValue());
            return new ProgramBool(a > b);
            } catch (Exception e) {
                Jlisp.failGracefully("Syntax Error: > takes only two numberic values", -1);   
            }
            return new ProgramBool(false);
        }
    };

    public final static Function<ArrayList<ProgramObject>, ProgramObject> eq = new Function<ArrayList<ProgramObject>, ProgramObject>() {
        public ProgramObject apply(ArrayList<ProgramObject> args) {
            try {
                Double a = (Double)(args.get(0).getValue());
                Double b = (Double)(args.get(1).getValue());
                return new ProgramBool(Math.abs(a - b) < 0.0001);
            } catch (Exception e) {
                Jlisp.failGracefully("Syntax Error: = takes only two numberic values", -1);
            }
            return new ProgramBool(false);
        }
    };

    public final static Function<ArrayList<ProgramObject>, ProgramObject> lt = new Function<ArrayList<ProgramObject>, ProgramObject>() {
        public ProgramObject apply(ArrayList<ProgramObject> args) {
            try {
                Double a = (Double)(args.get(0).getValue());
                Double b = (Double)(args.get(1).getValue());
                return new ProgramBool(a < b);
            } catch (Exception e) {
                Jlisp.failGracefully("Syntax Error: < takes only two numberic values", -1);
            }
            return new ProgramBool(false);
        }
    };

    // public final static Function<ArrayList<ProgramObject>, ProgramObject> set = new Function<ArrayList<ProgramObject>, ProgramObject>() {
    //     public ProgramObject apply(ArrayList<ProgramObject> args) {
    //         try {
    //             String key = (String) args.get(0).getValue();
    //             Object value = (String) args.get(1).getValue();
                
    //         } catch (Exception e) {
    //             Jlisp.failGracefully("Syntax Error: < takes only two numberic values", -1);
    //         }
    //         return new ProgramBool(false);
    //     }
    // };

    private HashMap<TokType, Function<ArrayList<ProgramObject>, ProgramObject>> map;
    private HashMap<String, ProgramObject> litsMap;

    public void addLit(String s, ProgramObject p) {
        litsMap.put(s, p);
    }

    public ProgramObject getLit(String s) {
        return litsMap.get(s);
    }

    public Repl() {
        map = new HashMap<TokType, Function<ArrayList<ProgramObject>, ProgramObject>>();
        litsMap = new HashMap<String, ProgramObject>();
        map.put(TokType.PLUS, add);
        map.put(TokType.MINUS, subtract);
        map.put(TokType.DIVIDE, divide);
        map.put(TokType.TIMES, multiply);
        map.put(TokType.LESS_THAN, lt);
        map.put(TokType.EQUALS, eq);
        map.put(TokType.GREATER_THAN, gt);
        // map.put(TokType.SET, set);
    }

    public HashMap<TokType, Function<ArrayList<ProgramObject>, ProgramObject>> getMap() {
        return map;
    }

}

