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

        TestObject testMisMatchedTypeList = new TestObject("(cons 'hello world' 4)", "[[\"hello world\", 4.0]]");
        t.addTest(testMisMatchedTypeList);
        
        TestObject testConsListVariables = new TestObject("(set lis1 (cons 4 4)) (set lis2 (cons 5 5)) (cons lis1 lis2)", "[[4.0, 4.0, 5.0, 5.0], [5.0, 5.0], [4.0, 4.0, 5.0, 5.0]]");
        t.addTest(testConsListVariables); 
        //since this is evaluated first and variables are objects, lis1 will return the final val when printed

        TestObject testCar = new TestObject("(car (cons 3.0 5.0))", "[3.0]");
        t.addTest(testCar);

        TestObject testCdr = new TestObject("(cdr (cons 4.0 (cons 5.0 6.0)))", "[[5.0, 6.0]]");
        t.addTest(testCdr);

        TestObject testDefine = new TestObject("(define sum2 (x y) (+ x y)) (sum2 5 10)", "[Function: sum2, 15.0]");
        t.addTest(testDefine);

        TestObject testDefine2 = new TestObject("(define average (x y z) (/ (+ x y z) 3)) (average 2 4 6) (average 3 6 9)", "[Function: average, 4.0, 6.0]");
        t.addTest(testDefine2);

        TestObject testCond = new TestObject("(define sumIfEqOrElseSub (x y) (cond (= x y) (+ x y) t (- x y))) (sumIfEqOrElseSub 1 1) (sumIfEqOrElseSub 6 2)", "[Function: sumIfEqOrElseSub, 2.0, 4.0]");
        t.addTest(testCond);

        TestObject testCond2 = new TestObject("(cond (= 1 2)('first path')(= 2 4)('second path') t ('third path'))", "[third path]");
        t.addTest(testCond2);

        TestObject testNil = new TestObject("(set x ()) (cond (nil? x) ('x is nil') t ('x is not nil') )", "[[], x is nil]");
        t.addTest(testNil);

        TestObject testNum = new TestObject("(set x 4.0) (cond (num? x) ('x is num') t ('x is not num') )", "[4.0, x is num]");
        t.addTest(testNil);

        TestObject testSym = new TestObject("(set x 4.0) (cond (sym? x) ('x is sym') t ('x is not sym') )", "[4.0, x is sym]");
        t.addTest(testSym);

        TestObject testLis = new TestObject("(set x (cons 3 4)) (cond (lis? x) ('x is lis') t ('x is not lis') )", "[[3.0, 4.0], x is lis]");
        t.addTest(testLis);

        t.runTests();
    }
}
