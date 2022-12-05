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

