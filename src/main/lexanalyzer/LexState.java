package main.lexanalyzer;
public enum LexState {
    LS_START, 
    LS_INT1, LS_INT2, LS_SIGN, LS_HEX1, LS_HEX2,
    LS_IDENT, LS_DOT1, LS_DOT2, LS_SYMBOL, 
    LS_ADDR1, LS_ADDR2,
    LS_COMMENT1,
    LS_STRLIT1,
    LS_STOP;
}
