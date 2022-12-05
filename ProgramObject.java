public abstract class ProgramObject {
    public abstract Object getValue();
    public abstract ProgramObjectType getType();
    public abstract String toString();

    public boolean isNil() {
        return this.getValue() == null || (this.getType() == ProgramObjectType.VARIABLE && ((ProgramVariable)this).getObject().isNil());
    }

    public boolean isNumber() {
        return this.getType() == ProgramObjectType.NUMBER || (this.getType() == ProgramObjectType.VARIABLE && ((ProgramVariable)this).getObject().getType() == ProgramObjectType.NUMBER);
    }

    public boolean isList() {
        return this.getType() == ProgramObjectType.LIST || (this.getType() == ProgramObjectType.VARIABLE && ((ProgramVariable)this).getObject().getType() == ProgramObjectType.LIST);
    }

    public boolean isSymbol() {
        return this.getType() == ProgramObjectType.LITERAL || this.getType() == ProgramObjectType.VARIABLE || this.getType() == ProgramObjectType.FUNCTION;
    }
}
