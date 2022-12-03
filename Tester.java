import java.util.ArrayList;

public class Tester {
    public ArrayList<TestObject> tests;

    public Tester() {
        tests = new ArrayList<TestObject>(0);
    }

    public Tester(ArrayList<TestObject> t) {
        tests = t;
    }

    public void addTest(String i, String o) {
        tests.add(new TestObject(i, o));
    }

    public void addTest(TestObject t) {
        tests.add(t);
    }

    public void runTests() {
        int testsFailed = 0;
        int testsPassed = 0;

        for(int i = 0; i < tests.size(); i++) {
            String input = tests.get(i).getInput();
            String eOut = tests.get(i).getOutput();
            System.out.println("Running Test #" + i+1 + " of " + tests.size());
            System.out.println("Input: " + input);
            System.out.println("Expected output: "+ eOut);
            String actOut = Jlisp.run(input).toString();
            boolean testPassed = tests.get(i).compareToOutput(actOut);
            System.out.println("Act Output: " + actOut);
            if(testPassed) {
                testsPassed++;
                System.out.println("TEST PASSED, CONTINUING TO NEXT TEST");
            } else {
                testsFailed++;
                System.out.println("TEST FAILED, CONTINUING TO NEXT TEST");
            }
            System.out.println();
        }

        System.out.println("FINAL STATS: ");
        System.out.println("Tests Run: " + tests.size());
        System.out.println("Tests Passed: " + testsPassed);
        System.out.println("Tests Failed: " + testsFailed);
    }
}
