import java.util.ArrayList;

public class Lexer {
    private String s;
    private int lineNum;
    private int length;
    private int index;
    private ArrayList<Expr> tokens;

    public Lexer(String s) {
        this.s = s;
        this.lineNum = 1;
        this.length = s.length();
        this.index = 0;
        this.tokens = new ArrayList<Expr>(0);
    }

    public ArrayList<Expr> lex() {
        while (this.index < this.length) {
            this.tokens.add(this.getNextToken());
        }
        return this.tokens;
    }

    public Token getNextToken() {
        ignoreWhitespace();

        TokType type;
        String lexeme;
        int line = this.lineNum;
        String literal = null;

        char c = consumeNextChar();

        switch (c) {
            case '(':
                type = TokType.OPEN_PAREN;
                lexeme = "(";
                break;
            case ')':
                type = TokType.CLOSE_PAREN;
                lexeme = ")";
                break;
            case '+':
                type = TokType.PLUS;
                lexeme = "+";
                break;
            case '-':
                type = TokType.MINUS;
                lexeme = "-";
                break;
            case '/':
                type = TokType.DIVIDE;
                lexeme = "/";
                break;
            case '*':
                type = TokType.TIMES;
                lexeme = "*";
                break;
            case '=':
                type = TokType.EQUALS;
                lexeme = "=";
                break;
            case '<':
                type = TokType.LESS_THAN;
                lexeme = "<";
                break;
            case '>':
                type = TokType.GREATER_THAN;
                lexeme = ">";
                break;
            case '\'':
            case '"':
                type = TokType.STRING;
                lexeme = getString(c);
                literal = lexeme.replaceAll(""+c, "");
                break;
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            case '0':
                type = TokType.NUM;
                lexeme = getNumber(c);
                literal = lexeme;
            default:
                String word = c + getWord();
                if (word.toUpperCase().equals("DEFINE")) {
                    type = TokType.DEFINE;
                    lexeme = word;
                } else if (word.toUpperCase().equals("SET")) {
                    type = TokType.SET;
                    lexeme = word;
                } else if (word.toUpperCase().equals("CONS")) {
                    type = TokType.CONS;
                    lexeme = word;
                } else if (word.toUpperCase().equals("COND")) {
                    type = TokType.COND;
                    lexeme = word;
                } else if (word.toUpperCase().equals("CAR")) {
                    type = TokType.CAR;
                    lexeme = word;
                } else if (word.toUpperCase().equals("CDR")) {
                    type = TokType.CDR;
                    lexeme = word;
                } else if (word.toUpperCase().equals("NUMBER?")) {
                    type = TokType.NUMBER_QUES;
                    lexeme = word;
                } else if (word.toUpperCase().equals("SYMBOL?")) {
                    type = TokType.SYMBOL_QUES;
                    lexeme = word;
                } else if (word.toUpperCase().equals("LIST?")) {
                    type = TokType.LIST_QUES;
                    lexeme = word;
                } else if (word.toUpperCase().equals("NIL?")) {
                    type = TokType.NIL_QUES;
                    lexeme = word;
                } else if (word.toUpperCase().equals("EQ?")) {
                    type = TokType.EQ_QUES;
                    lexeme = word;
                } else {
                    type = TokType.LITERAL;
                    lexeme = word;
                    literal = word;
                }
                break;
        }

        return new Token(type, lexeme, literal, line);
    }

    public void ignoreWhitespace() {
        char c = s.charAt(index);
        while (c == ' ' || c == '\n') {
            if (c == '\n')
                lineNum++;
            index++;
            c = s.charAt(index);
        }
    }

    public char consumeNextChar() {
        return s.charAt(index++);
    }

    public char peek() {
        return s.charAt(index);
    }

    public String getWord() {
        int i = index;
        while (index < length) {
            char c = s.charAt(index);
            if (c == ' ' || c == '\n' || c == '(' || c == ')')
                break;
            index++;
        }
        return s.substring(i, index);
    }

    public String getString(char c) {
        int i = index;
        while(index < length) {
            char cur = s.charAt(index);
            if(cur == c) {
                break;
            }
            index++;
        }
        return c + s.substring(i, index);
    }

    public String getNumber(char c) {
        String res = "" + c;
        while(index < length) {
            char cur = s.charAt(index);
            if((cur >= '0' && cur <= '9') || cur == '.')
                index++;
            else
                break;
        }
        return res;
    }

    public static ArrayList<Expr> astify(ArrayList<Expr> lis) throws Exception {
        ArrayList<Expr> res = new ArrayList<Expr>(0);
        int index = 0;
        int len = lis.size();
        Expr cur;

        while (index < len) {
            if (lis.get(index).getType() == TokType.OPEN_PAREN) {
                int closeIndex = List.findCloser(lis, index, len);
                cur = List.listify(lis, index + 1, closeIndex - 1);
                index = closeIndex;
            } else {
                cur = lis.get(index);
            }

            res.add(cur);
            index++;
        }

        return res;
    }
}