

public class Token {
    private TokType type;
    private String lexeme;
    private String value;
    private int lineNum;

    public Token(TokType t, String l, int line) {
        this(t, l, null, line)
    }

    public Token(TokType t, String l, String v, int line) {
        this.type = t;
        this.lexeme = l;
        this.value = v;
        this.lineNum = line;
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

    public int getLineNum() {
        return this.lineNum;
    }

    public String toString() {
        return String.format("Type: %s, Lexeme: %s, Value: %s, LineNum: %d", this.type, this.lexeme, this.value, this.lineNum)
    }
}