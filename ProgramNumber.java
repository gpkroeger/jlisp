public class ProgramNumber extends ProgramObject {
    private double value;

    public ProgramNumber() {
        this(0.0);
    }

    public ProgramNumber(double val) {
        this.value = val;
    }

    public double getNumber() {
        return this.value;
    }

    public double plus(double d) {
        return this.value+=d;
    }

    public double minus(double d) {
        return this.value-=d;
    }

    public double times(double d) {
        return this.value*=d;
    }

    public double divide(double d) {
        return this.value/=d;
    }

    public Object getValue() {
        return Double.valueOf(value);
    }

    public ProgramObjectType getType() {
        return ProgramObjectType.NUMBER;
    }

    public String toString() {
        return "" + value;
    }
}
