public class ProgramVariable extends ProgramObject {
    String key;
    ProgramObject value;

    public ProgramVariable(String s, ProgramObject p) {
        key = s;
        value = p;
    }

    public ProgramObjectType getType() {
        return ProgramObjectType.VARIABLE;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value.getValue();
    }

    public String toString() {
        return value.toString();
    }
}
