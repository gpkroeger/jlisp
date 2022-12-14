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
                    Jlisp.failGracefully("Syntax Error: + takes only numeric values as arguments, given type " + t.getType(), -1);
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

    public final static Function<ArrayList<ProgramObject>, ProgramObject> set = new Function<ArrayList<ProgramObject>, ProgramObject>() {
        public ProgramObject apply(ArrayList<ProgramObject> args) {
            String key="";
            ProgramObject value=null;
            try {
                assert(args.size() == 2);
                key = (String)args.get(0).getValue();
                value = args.get(1);
            } catch (Exception e) {
                Jlisp.failGracefully("Syntax Error: SET TAKES ONLY 2 VALUES", -1);
            }
            return new ProgramVariable(key, value);
        }
    };

    public final static Function<ArrayList<ProgramObject>, ProgramObject> cons = new Function<ArrayList<ProgramObject>, ProgramObject>() {
        public ProgramObject apply(ArrayList<ProgramObject> args) {
            ProgramObject parent = null;
            ProgramObject child = null;
            try {
                assert(args.size() == 2);
                parent = args.get(0);
                child = args.get(1);
                if(parent.getType() == ProgramObjectType.VARIABLE) {
                    parent = ((ProgramVariable)parent).getObject();
                }
                if(child.getType() == ProgramObjectType.VARIABLE) {
                    child = ((ProgramVariable)child).getObject();
                }
                if(parent.getType() != ProgramObjectType.LIST) {
                    parent = new ProgramList(parent);
                }
                if(child.getType() != ProgramObjectType.LIST) {
                    child = new ProgramList(child);
                }
                ((ProgramList)parent).cons((ProgramList)child);
            } catch (Exception e) {
                Jlisp.failGracefully("Syntax Error: Invalid CONS statement: cons takes 2 arguments", -1);
            }
            return parent;
        }
    };

    public final static Function<ArrayList<ProgramObject>, ProgramObject> car = new Function<ArrayList<ProgramObject>, ProgramObject>() {
        public ProgramObject apply(ArrayList<ProgramObject> args) {
            ProgramObject parent = null;
            ProgramObject result = null;
            try {
                assert(args.size() == 1);
                parent = args.get(0);
                if(parent.getType() == ProgramObjectType.VARIABLE) {
                    parent = ((ProgramVariable)parent).getObject();
                }
                assert(parent.getType() == ProgramObjectType.LIST);
                result = ((ProgramList)parent).car();
            } catch (Exception e) {
                Jlisp.failGracefully("Syntax Error: Invalid CAR statement, takes one statement and it MUST be a list", -1);
            }
            return result;
        }
    };

    public final static Function<ArrayList<ProgramObject>, ProgramObject> cdr = new Function<ArrayList<ProgramObject>, ProgramObject>() {
        public ProgramObject apply(ArrayList<ProgramObject> args) {
            ProgramObject parent = null;
            ProgramList result = null;
            try {
                assert(args.size() == 1);
                parent = args.get(0);
                if(parent.getType() == ProgramObjectType.VARIABLE) {
                    parent = ((ProgramVariable)parent).getObject();
                }
                assert(parent.getType() == ProgramObjectType.LIST);
                result = ((ProgramList)parent).cdr();
            } catch (Exception e) {
                Jlisp.failGracefully("Syntax Error: Invalid CDR statement, takes one statement and it MUST be a list", -1);
            }
            return result;
        }
    };

    public final static Function<ArrayList<ProgramObject>, ProgramObject> cond = new Function<ArrayList<ProgramObject>, ProgramObject>() {
        public ProgramObject apply(ArrayList<ProgramObject> args) {
            return args.get(0);
        }
    };

    public final static Function<ArrayList<ProgramObject>, ProgramObject> def = new Function<ArrayList<ProgramObject>, ProgramObject>() {
        public ProgramObject apply(ArrayList<ProgramObject> args) {
            return args.get(0);
        }
    };

    public final static Function<ArrayList<ProgramObject>, ProgramObject> isNil = new Function<ArrayList<ProgramObject>, ProgramObject>() {
        public ProgramObject apply(ArrayList<ProgramObject> args) {
            return new ProgramBool(args.get(0).isNil());
        }
    };

    public final static Function<ArrayList<ProgramObject>, ProgramObject> isSymbol = new Function<ArrayList<ProgramObject>, ProgramObject>() {
        public ProgramObject apply(ArrayList<ProgramObject> args) {
            return new ProgramBool(args.get(0).isSymbol());
        }
    };

    public final static Function<ArrayList<ProgramObject>, ProgramObject> isList = new Function<ArrayList<ProgramObject>, ProgramObject>() {
        public ProgramObject apply(ArrayList<ProgramObject> args) {
            return new ProgramBool(args.get(0).isList());
        }
    };

    public final static Function<ArrayList<ProgramObject>, ProgramObject> isNumber = new Function<ArrayList<ProgramObject>, ProgramObject>() {
        public ProgramObject apply(ArrayList<ProgramObject> args) {
            return new ProgramBool(args.get(0).isNumber());
        }
    };

    private HashMap<TokType, Function<ArrayList<ProgramObject>, ProgramObject>> map;
    public HashMap<String, ProgramObject> litsMap;

    public void addLit(String s, ProgramObject p) {
        litsMap.put(s, p);
    }

    public ProgramObject getLit(String s) {
        return litsMap.get(s);
    }

    public HashMap<String, ProgramObject> putAll(HashMap<String, ProgramObject> m) {
        litsMap.putAll(m);
        return litsMap;
    }

    // public HashMap<String, ProgramObject> removeAll(ArrayList<String> args) {
    //     for(String s : args) {
    //         litsMap.remove(s);
    //     }
    //     return litsMap;
    // }

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
        map.put(TokType.SET, set);
        map.put(TokType.CONS, cons);
        map.put(TokType.CAR, car);
        map.put(TokType.CDR, cdr);
        map.put(TokType.COND, cond);
        map.put(TokType.DEFINE, def);
        map.put(TokType.NIL_QUES, isNil);
        map.put(TokType.SYMBOL_QUES, isSymbol);
        map.put(TokType.NUMBER_QUES, isNumber);
        map.put(TokType.LIST_QUES, isList);
    }

    public HashMap<TokType, Function<ArrayList<ProgramObject>, ProgramObject>> getMap() {
        return map;
    }

}

