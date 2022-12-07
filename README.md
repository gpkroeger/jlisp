### This is a small lisp interpreter made in java for a school project

### Authors: Gabe Kroeger, Owen Russell, Riley Parker

### USAGE:
- compile: javac *.java
- run tests: java Tests
- cleanup: rm -f *.class

### FUNCTIONALITY:
- (+ arg1 arg2 .. argN)
- (* arg1 arg2 .. argN)
- (- arg1 arg2 .. argN)
- (/ arg1 arg2 .. argN)
- (= arg1 arg2)
- (< arg1 arg2)
- (> arg1 arg2)
- (cond condition expr ... condition expr)
- (set varName varValue)
- (define funName (args) expr)
- (cons item1 item2)
- (car lis)
- (cdr lis)
- (num? val)
- (sym? val)
- (nil? val)
- (lis? val)

### To Run Your Own Tests from the cmd line:
- compile with javac *.java
- run: java interactiveTest.java
- interact with the program by entering lisp commands from the command line

### Sample Test: Here is a sample result from 30 test cases run

Running Test #1 of 30
Input: (+ 2 4)
Expected output: [6.0]
Act Output: [6.0]
TEST PASSED, CONTINUING TO NEXT TEST

Running Test #2 of 30
Input: (+ 1 2 3 4)
Expected output: [10.0]
Act Output: [10.0]
TEST PASSED, CONTINUING TO NEXT TEST

Running Test #3 of 30
Input: (+ 1.25 1.25 2.5)
Expected output: [5.0]
Act Output: [5.0]
TEST PASSED, CONTINUING TO NEXT TEST

Running Test #4 of 30
Input: (- 3 2 1)
Expected output: [0.0]
Act Output: [0.0]
TEST PASSED, CONTINUING TO NEXT TEST

Running Test #5 of 30
Input: (- 5.5 4.5)
Expected output: [1.0]
Act Output: [1.0]
TEST PASSED, CONTINUING TO NEXT TEST

Running Test #6 of 30
Input: (- 105030.123 105029.123)
Expected output: [1.0]
Act Output: [1.0]
TEST PASSED, CONTINUING TO NEXT TEST

Running Test #7 of 30
Input: (* 2 5 1)
Expected output: [10.0]
Act Output: [10.0]
TEST PASSED, CONTINUING TO NEXT TEST

Running Test #8 of 30
Input: (/ 10 2)
Expected output: [5.0]
Act Output: [5.0]
TEST PASSED, CONTINUING TO NEXT TEST

Running Test #9 of 30
Input: (= 10.234 10.321)
Expected output: [false]
Act Output: [false]
TEST PASSED, CONTINUING TO NEXT TEST

Running Test #10 of 30
Input: (= 5 5)
Expected output: [true]
Act Output: [true]
TEST PASSED, CONTINUING TO NEXT TEST

Running Test #11 of 30
Input: (< 2.31 4.21)
Expected output: [true]
Act Output: [true]
TEST PASSED, CONTINUING TO NEXT TEST

Running Test #12 of 30
Input: (< 4.5 0.1234567)
Expected output: [false]
Act Output: [false]
TEST PASSED, CONTINUING TO NEXT TEST

Running Test #13 of 30
Input: (> 1000.231 1000.230)
Expected output: [true]
Act Output: [true]
TEST PASSED, CONTINUING TO NEXT TEST

Running Test #14 of 30
Input: (> 1000.234 1000.235)
Expected output: [false]
Act Output: [false]
TEST PASSED, CONTINUING TO NEXT TEST

Running Test #15 of 30
Input: (= 10 5) (+ 1 5) (- 5 2.3)
Expected output: [false, 6.0, 2.7]
Act Output: [false, 6.0, 2.7]
TEST PASSED, CONTINUING TO NEXT TEST

Running Test #16 of 30
Input: (set var1 5)(set var2 8)(+ var1 var2)
Expected output: [5.0, 8.0, 13.0]
Act Output: [5.0, 8.0, 13.0]
TEST PASSED, CONTINUING TO NEXT TEST

Running Test #17 of 30
Input: (cons 1 5)
Expected output: [[1.0, 5.0]]
Act Output: [[1.0, 5.0]]
TEST PASSED, CONTINUING TO NEXT TEST

Running Test #18 of 30
Input: (cons 4 (cons 4 (cons 4 4)))
Expected output: [[4.0, 4.0, 4.0, 4.0]]
Act Output: [[4.0, 4.0, 4.0, 4.0]]
TEST PASSED, CONTINUING TO NEXT TEST

Running Test #19 of 30
Input: (cons 'hello world' 4)
Expected output: [["hello world", 4.0]]
Act Output: [["hello world", 4.0]]
TEST PASSED, CONTINUING TO NEXT TEST

Running Test #20 of 30
Input: (set lis1 (cons 4 4)) (set lis2 (cons 5 5)) (cons lis1 lis2)
Expected output: [[4.0, 4.0, 5.0, 5.0], [5.0, 5.0], [4.0, 4.0, 5.0, 5.0]]
Act Output: [[4.0, 4.0, 5.0, 5.0], [5.0, 5.0], [4.0, 4.0, 5.0, 5.0]]
TEST PASSED, CONTINUING TO NEXT TEST

Running Test #21 of 30
Input: (car (cons 3.0 5.0))
Expected output: [3.0]
Act Output: [3.0]
TEST PASSED, CONTINUING TO NEXT TEST

Running Test #22 of 30
Input: (cdr (cons 4.0 (cons 5.0 6.0)))
Expected output: [[5.0, 6.0]]
Act Output: [[5.0, 6.0]]
TEST PASSED, CONTINUING TO NEXT TEST

Running Test #23 of 30
Input: (define sum2 (x y) (+ x y)) (sum2 5 10)
Expected output: [Function: sum2, 15.0]
Act Output: [Function: sum2, 15.0]
TEST PASSED, CONTINUING TO NEXT TEST

Running Test #24 of 30
Input: (define average (x y z) (/ (+ x y z) 3)) (average 2 4 6) (average 3 6 9)
Expected output: [Function: average, 4.0, 6.0]
Act Output: [Function: average, 4.0, 6.0]
TEST PASSED, CONTINUING TO NEXT TEST

Running Test #25 of 30
Input: (define sumIfEqOrElseSub (x y) (cond (= x y) (+ x y) t (- x y))) (sumIfEqOrElseSub 1 1) (sumIfEqOrElseSub 6 2)
Expected output: [Function: sumIfEqOrElseSub, 2.0, 4.0]
Act Output: [Function: sumIfEqOrElseSub, 2.0, 4.0]
TEST PASSED, CONTINUING TO NEXT TEST

Running Test #26 of 30
Input: (cond (= 1 2)('first path')(= 2 4)('second path') t ('third path'))
Expected output: [third path]
Act Output: [third path]
TEST PASSED, CONTINUING TO NEXT TEST

Running Test #27 of 30
Input: (set x ()) (cond (nil? x) ('x is nil') t ('x is not nil') )
Expected output: [[], x is nil]
Act Output: [[], x is nil]
TEST PASSED, CONTINUING TO NEXT TEST

Running Test #28 of 30
Input: (set x 4.0) (cond (num? x) ('x is num') t ('x is not num') )
Expected output: [4.0, x is num]
Act Output: [4.0, x is num]
TEST PASSED, CONTINUING TO NEXT TEST

Running Test #29 of 30
Input: (set x 4.0) (cond (sym? x) ('x is sym') t ('x is not sym') )
Expected output: [4.0, x is sym]
Act Output: [4.0, x is sym]
TEST PASSED, CONTINUING TO NEXT TEST

Running Test #30 of 30
Input: (set x (cons 3 4)) (cond (lis? x) ('x is lis') t ('x is not lis') )
Expected output: [[3.0, 4.0], x is lis]
Act Output: [[3.0, 4.0], x is lis]
TEST PASSED, CONTINUING TO NEXT TEST

FINAL STATS: 
Tests Run: 30
Tests Passed: 30
Tests Failed: 0

### KNOWN ISSUES:
- using a single quote to signify true as is often done in lisp is not currently working:
    - example 't
    - reason: the lexer sees this as an opening quote
- numbers are limited by java's double library
    - example: (- 0.234567 0.123456) doesn't return 0.111111 as expected, rather a slightly off value
    - reason: same as java
- negative numbers are not yet supported
    - example: (+ 1 -2) causes failure
    - reason: difficult to implement difference between subtract function and negative

