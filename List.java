import java.util.ArrayList;

public class List extends Expr {
    private Expr left;
    private ArrayList<Expr> others;
    private int lineNum;

    public List() {
        this(null, new ArrayList<Expr>(0));
    }

    public List(Expr l) {
        this(l, new ArrayList<Expr>(0));
    }

    public List(Expr l, ArrayList<Expr> lis) {
        left = l;
        others = lis;
        if(l != null)
            lineNum = l.getLineNumber();
        else
            lineNum = 1;
    }

    public int getLineNumber() {
        return this.lineNum;
    }

    public String getValue() {
        return this.left.getValue();
    }

    public TokType getType() {
        return this.left.getType();
    }

    public boolean isList() {
        return true;
    }

    public Expr getLeft() {
        return left;
    }

    public ArrayList<Expr> getChildren() {
        return others;
    }

    public boolean isNil() {
        return (others.size() == 0 && left == null);
    }

    public ArrayList<String> getAll() {
        ArrayList<String> res = new ArrayList<String>(1+others.size());
        res.add(left.getValue());
        for(Expr e : others) {
            res.add(e.getValue());
        }
        return res;
    }

    public String toString() {
        return String.format("Type: %s, Left: %s, Others: %s", "LIST", left, others);
    }

    // prereq: the parameter arraylist is between one open paren and one
    // closed paren which are not present wihtin the indices at the time of passing
    // through
    public static Expr listify(ArrayList<Expr> t, int left, int right) throws Exception {
        Expr l;
        ArrayList<Expr> others = new ArrayList<Expr>(0);
        Expr cur;
        if (left == right) {
            return new List(t.get(left));
        }
        if (left > right) {
            return new Token(TokType.EMPTY_LIST, "()", -1);
        }

        l = t.get(left++);

        while (left <= right) {
            if (t.get(left).getType() == TokType.OPEN_PAREN) {
                int closeIndex = findCloser(t, left, right);
                cur = listify(t, left + 1, closeIndex - 1);
                left = closeIndex;
            } else {
                cur = t.get(left);
            }
            others.add(cur);
            left++;
        }

        return new List(l, others);
    }

    // this is called with the first index as an open paren and by the closing index
    // should contain the
    // closing parenthesis, otherwise raises an exception
    // returns the index of the matching closing parenthesis
    public static int findCloser(ArrayList<Expr> t, int left, int right) throws Exception {
        int open = 0;
        int close = 0;
        while (true) {
            if (left > right) {
                throw new Exception("Mismatched parenthesis");
            }

            if (t.get(left).getType() == TokType.OPEN_PAREN) {
                open++;
            }
            if (t.get(left).getType() == TokType.CLOSE_PAREN) {
                close++;
            }

            if (open == close)
                break;
            left++;
        }

        return left;
    }
}
