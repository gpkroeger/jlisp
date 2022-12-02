public class TestObject {
    private String input;
    private String expectedOutput;

    public TestObject(String i, String o) {
        this.input = i;
        this.expectedOutput = o;
    }

    public String getInput() {
        return this.input;
    }

    public String getOutput() {
        return this.expectedOutput;
    }

    public boolean compareToOutput(String s) {
        return this.expectedOutput.equals(s);
    }
}
