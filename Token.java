import java.util.ArrayList;

public class Token extends Expr {
    private TokType type;
    private String lexeme;
    private String value;
    private int lineNum;
    private ArrayList<Expr> children;

    public Token(TokType t, String l, int line) {
        this(t, l, null, line);
    }

    public Token(TokType t, String l, String v, int line) {
        this.type = t;
        this.lexeme = l;
        this.value = v;
        this.lineNum = line;
        this.children = new ArrayList<Expr>(0);
    }

    public TokType getType() {
        return this.type;
    }

    public String getLexeme() {
        return this.lexeme;
    }

    public String getValue() {
        return this.value;
    }

    public int getLineNumber() {
        return this.lineNum;
    }

    public String toString() {
        return String.format("Type: %s, Lexeme: %s, Value: %s, LineNum: %d", this.type, this.lexeme, this.value,
                this.lineNum);
    }

    public ArrayList<Expr> getChildren() {
        return this.children;
    }

    public boolean hasChildren() {
        return this.children.size() > 0;
    }

    public void addChild(Token child) {
        this.children.add(child);
    }

    public void addChildren(ArrayList<Token> children) {
        this.children.addAll(children);
    }

    public boolean isList() {
        return false;
    }

    public Token copy() {
        return new Token(type, lexeme, value, lineNum);
    }
}

//     //TODO: ADD CHECKER FOR MISMATCHED PARENTHESIS
//     public static ArrayList<Token> astify(ArrayList<Token> list) {
//         int index = 0;
//         Token cur;
//         ArrayList<Token> result = new ArrayList<Token>(0);
//         while (index < list.size()) {
//             cur = list.get(index).copy();
//             if (cur.getType() == TokType.OPEN_PAREN) {
//                 Token parent = list.get(++index).copy();
//                 ArrayList<Token> children = findChildren(list, ++index);
//                 index += children.size() + 1;
//                 children = astify(children);
//                 parent.addChildren(children);
//                 result.add(parent);
//             } else {
//                 result.add(cur);
//             }
//             index++;
//         }
//         return result;
//     }

//     public static ArrayList<Token> findChildren(ArrayList<Token> list, int index) {
//         ArrayList<Token> result = new ArrayList<Token>(0);
//         int open = 1;
//         int closed = 0;
//         while (index < list.size()) {
//             Token cur = list.get(index).copy();
//             if (cur.getType() == TokType.OPEN_PAREN) {
//                 open++;
//             } else if (cur.getType() == TokType.CLOSE_PAREN) {
//                 closed++;
//             }
//             if (closed == open) {
//                 break;
//             } else {
//                 result.add(cur);
//             }

//             index++;
//         }
//         return result;
//     }
// }