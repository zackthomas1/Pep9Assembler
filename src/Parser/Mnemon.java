package Parser;
public enum Mnemon {
    // Unary
    M_ASLA, M_ASRA, M_STOP, 
    M_BLOCK, M_END,   // dot command
    // Non-Unary
    M_BR, M_BRLT, M_BREQ, M_BRLE, M_CALL,       // single-argument
    M_CPWA, M_DECI, M_DECO, M_ADDA, M_SUBA, M_STWA, M_LDWA, // double-argument
}