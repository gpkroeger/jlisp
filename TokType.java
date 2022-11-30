
public enum TokType {
    // SINGLE CHAR TOKENS
    PLUS, // +,
    MINUS, // -,
    TIMES, // *,
    DIVIDE, // /,
    EQUALS, // =,
    LESS_THAN, // <,
    GREATER_THAN, // >,
    OPEN_PAREN, // (
    CLOSE_PAREN, // )

    // WORD TOKENS
    DEFINE, // DEFINE,
    SET, // SET,
    CONS, // CONS,
    COND, // COND,
    CAR, // CAR,
    CDR, // CDR,

    //QUES TOKENS
    NUMBER_QUES, // NUMBER?,
    SYMBOL_QUES, // SYMBOL?,
    LIST_QUES, // LIST?,
    NIL_QUES, // NIL?,
    EQ_QUES, // EQ?

    //LITERAL
    LITERAL,

    //STRING AND NUM
    STRING,
    NUM,

    //LIST
    LIST
}