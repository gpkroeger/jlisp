public class Tests {
    public static void main(String args[]) {
        Tester t = new Tester();
        
        TestObject additionTest = new TestObject("(+ 2 4)", "[6.0]");
        t.addTest(additionTest);

        TestObject add4Test = new TestObject("(+ 1 2 3 4)", "[10.0]");
        t.addTest(add4Test);

        TestObject addDecTest = new TestObject ("(+ 1.25 1.25 2.5)", "[5.0]");
        t.addTest(addDecTest);

        TestObject subTest = new TestObject ("(- 3 2 1)", "[0.0]");
        t.addTest(subTest);

        TestObject subTestDec = new TestObject("(- 5.5 4.5)", "[1.0]");
        t.addTest(subTestDec);

        TestObject subTestLong = new TestObject("(- 105030.123 105029.123)", "[1.0]");
        t.addTest(subTestLong);

        TestObject multTest = new TestObject("(* 2 5 1)", "[10.0]");
        t.addTest(multTest);

        TestObject divideTest = new TestObject("(/ 10 2)", "[5.0]");
        t.addTest(divideTest);

        TestObject equalsTestFalse = new TestObject("(= 10.234 10.321)", "[false]");
        t.addTest(equalsTestFalse);

        TestObject equalsTestTrue = new TestObject("(= 5 5)", "[true]");
        t.addTest(equalsTestTrue);

        TestObject lessThanTestTrue = new TestObject("(< 2.31 4.21)", "[true]");
        t.addTest(lessThanTestTrue);

        TestObject lessThanTestFAlse = new TestObject("(< 4.5 0.1234567)", "[false]");
        t.addTest(lessThanTestFAlse);

        TestObject greaterThanTestTrue = new TestObject("(> 1000.231 1000.230)", "[true]");
        t.addTest(greaterThanTestTrue);

        TestObject greaterThanTestFAlse = new TestObject ("(> 1000.234 1000.235)", "[false]");
        t.addTest(greaterThanTestFAlse);

        TestObject testMultipleCommands = new TestObject ("(= 10 5) (+ 1 5) (- 5 2.3)", "[false, 6.0, 2.7]");
        t.addTest(testMultipleCommands);

        TestObject testVariables = new TestObject ("(set var1 5)(set var2 8)(+ var1 var2)","[5.0, 8.0, 13.0]");
        t.addTest(testVariables);

        TestObject testCons = new TestObject("(cons 1 5)", "[[1.0, 5.0]]");
        t.addTest(testCons);

        TestObject testConsList = new TestObject("(cons 4 (cons 4 (cons 4 4)))", "[[4.0, 4.0, 4.0, 4.0]]");
        t.addTest(testConsList);

        t.runTests();
    }
}
