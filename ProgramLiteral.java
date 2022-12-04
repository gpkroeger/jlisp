public class ProgramLiteral extends ProgramObject {
    private String value;

    public ProgramLiteral(String s) {
        value = s;
    }

    public ProgramObjectType getType() {
        return ProgramObjectType.LITERAL;
    }

    public Object getValue() {
        return value;
    }

    public String toString() {
        return value;
    }
}
