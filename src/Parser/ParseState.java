package Parser;

public enum ParseState {
    PS_START,
    PS_UNARY, 
    PS_FUNCTION, 
    PS_NON_UNARY1,
    PS_NON_UNARY2, 
    PS_FINISH
}
