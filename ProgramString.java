public class ProgramString extends ProgramObject{
    String value;

    public ProgramString() {
        this("");
    }

    public ProgramString(String str) {
        value = str;
    }

    public Object getValue() {
        return value;
    }

    public ProgramObjectType getType() {
        return ProgramObjectType.STRING;
    }

    public String toString() {
        return '"' + value + '"';
    }
}
