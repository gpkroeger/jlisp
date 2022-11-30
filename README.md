This is a small lisp interpreter made in java for a school project

Authors: Gabe Kroeger, Owen Russell, Riley Parker

KNOWN ISSUES:
    using a single quote to signify true as is often done in lisp is not currently working:
        example 't
        reason: the lexer sees this as an opening quote
    list class doesn't compile with the rest of the program
        example: running "javac Jlisp.java" compiles the program but not List.java
        reason: unknown