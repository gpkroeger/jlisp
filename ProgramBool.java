public class ProgramBool extends ProgramObject {
    private boolean value;

    public ProgramBool() {
        this(false);
    }

    public ProgramBool(boolean v) {
        this.value = v;
    }

    public boolean getBoolean() {
        return this.value;
    }

    public Object getValue() {
        return Boolean.valueOf(this.value);
    }

    public ProgramObjectType getType() {
        return ProgramObjectType.BOOL;
    }

    public String toString() {
        return "" + this.value;
    }
}
