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
            Double diff = 0.0;
            double sign = 1.0;
            for (Expr t : args) {
                diff+=sign*Double.parseDouble(t.getValue());
                sign = 1.0;
            }
            return diff;
        }
    };
    public final static Function<ArrayList<Expr>, Object> divide = new Function<ArrayList<Expr>, Object>() {
        public Double apply(ArrayList<Expr> args) {
            double start = Double.parseDouble(args.get(0).getValue());
            for(int i = 1; i < args.size(); i++) {
                start /= Double.parseDouble(args.get(i).getValue());
            }
            return start;
        }
    };
    public final static Function<ArrayList<Expr>, Object> multiply = new Function<ArrayList<Expr>, Object>() {
        public Double apply(ArrayList<Expr> args) {
            double start = Double.parseDouble(args.get(0).getValue());
            for(int i = 1; i < args.size(); i++) {
                start *= Double.parseDouble(args.get(i).getValue());
            }
            return start;
        }
    };

    public HashMap<TokType, Function<ArrayList<Expr>, Object>> map;

    public Repl() {
        map = new HashMap<TokType, Function<ArrayList<Expr>, Object>>();
        map.put(TokType.PLUS, add);
        map.put(TokType.MINUS, subtract);
        map.put(TokType.DIVIDE, divide);
        map.put(TokType.TIMES, multiply);
    }

    public HashMap<TokType, Function<ArrayList<Expr>, Object>> getMap() {
        return map;
    }

}

