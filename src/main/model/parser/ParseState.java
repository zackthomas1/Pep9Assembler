package main.model.parser;

public enum ParseState {
    PS_START,
    PS_SYMBOL,
    PS_UNARY, 
    PS_FUNCTION, 
    PS_NON_UNARY1,
    PS_NON_UNARY2,
    PS_DOT1, 
    PS_DOT2, 
    PS_COMMENT,
    PS_FINISH
}
