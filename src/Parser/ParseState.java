package Parser;

public enum ParseState {
    PS_START,
    PS_UNARY, 
    PS_FUNCTION, 
    PS_1ST_OPEND, 
    PS_NON_UNARY1,
    PS_COMMA,
    PS_2ND_OPRND, 
    PS_NON_UNARY2, 
    PS_FINISH
}
