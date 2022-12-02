public class Tests {
    public static void main(String args[]) {
        Tester t = new Tester();
        
        TestObject additionTest = new TestObject("(+ 2 4)", "6.0");
        t.addTest(additionTest);

        TestObject add4Test = new TestObject("(+ 1 2 3 4)", "10.0");
        t.addTest(add4Test);

        TestObject addDecTest = new TestObject ("(+ 1.25 1.25 2.5)", "5.0");
        t.addTest(addDecTest);

        TestObject subTest = new TestObject ("(- 3 2 1)", "0.0");
        t.addTest(subTest);

        TestObject subTestDec = new TestObject("(- 5.5 4.5)", "1.0");
        t.addTest(subTestDec);

        TestObject subTestLong = new TestObject("(- 105030.123 105029.123)", "1.0");
        t.addTest(subTestLong);

        t.runTests();
    }
}
