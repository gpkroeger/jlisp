import java.util.ArrayList;
import java.util.function.Function;
import java.util.HashMap;

public class Repl {
    public final static Function<ArrayList<Expr>, Object> add = new Function<ArrayList<Expr>, Object>() {
        public Double apply(ArrayList<Expr> args) {
            Double sum = 0.0;
            for (Expr t : args) {
                sum+=Double.parseDouble(t.getValue());
            }
            return sum;
        }
    };
    public final static Function<ArrayList<Expr>, Object> subtract = new Function<ArrayList<Expr>, Object>() {
        public Double apply(ArrayList<Expr> args) {
            Double cur = Double.parseDouble(args.get(0).getValue());
            for(int i = 1; i < args.size(); i++) {
                cur-=Double.parseDouble(args.get(i).getValue());
            }
            return cur;
        }
    };
    public final static Function<ArrayList<Expr>, Object> divide = new Function<ArrayList<Expr>, Object>() {
        public Double apply(ArrayList<Expr> args) {
            Double cur = Double.parseDouble(args.get(0).getValue());
            for(int i = 1; i < args.size(); i++) {
                cur/=Double.parseDouble(args.get(i).getValue());
            }
            return cur;
        }
    };
    public final static Function<ArrayList<Expr>, Object> multiply = new Function<ArrayList<Expr>, Object>() {
        public Double apply(ArrayList<Expr> args) {
            Double cur = Double.parseDouble(args.get(0).getValue());
            for(int i = 1; i < args.size(); i++) {
                cur*=Double.parseDouble(args.get(i).getValue());
            }
            return cur;
        }
    };

    public final static Function<ArrayList<Expr>, Object> gt = new Function<ArrayList<Expr>, Object>() {
        public Boolean apply(ArrayList<Expr> args) {
            Double a = Double.parseDouble(args.get(0).getValue());
            Double b = Double.parseDouble(args.get(1).getValue());
            return a > b;
        }
    };

    public final static Function<ArrayList<Expr>, Object> eq = new Function<ArrayList<Expr>, Object>() {
        public Boolean apply(ArrayList<Expr> args) {
            Double a = Double.parseDouble(args.get(0).getValue());
            Double b = Double.parseDouble(args.get(1).getValue());
            return Math.abs(a - b) < 0.0001;
        }
    };

    public final static Function<ArrayList<Expr>, Object> lt = new Function<ArrayList<Expr>, Object>() {
        public Boolean apply(ArrayList<Expr> args) {
            Double a = Double.parseDouble(args.get(0).getValue());
            Double b = Double.parseDouble(args.get(1).getValue());
            return a < b;
        }
    };

    public HashMap<TokType, Function<ArrayList<Expr>, Object>> map;

    public Repl() {
        map = new HashMap<TokType, Function<ArrayList<Expr>, Object>>();
        map.put(TokType.PLUS, add);
        map.put(TokType.MINUS, subtract);
        map.put(TokType.DIVIDE, divide);
        map.put(TokType.TIMES, multiply);
        map.put(TokType.LESS_THAN, lt);
        map.put(TokType.EQUALS, eq);
        map.put(TokType.GREATER_THAN, gt);
    }

    public HashMap<TokType, Function<ArrayList<Expr>, Object>> getMap() {
        return map;
    }

}

