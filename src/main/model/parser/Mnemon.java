package main.model.parser;
public enum Mnemon {
    // Unary
    M_ASLA, M_ASLX, M_ASRA, M_ASRX, M_ROLA, M_ROLX, M_RORA, M_RORX, M_NOTA, M_NOTX, M_NEGA, M_NEGX,
    M_MOVSPA, M_MOVFLGA, M_MOVAFLG,M_RETTR, M_RET, M_STOP, 

    // Dot Command
    M_BLOCK, M_END,  
     
    // Non-Unary
    M_BR, M_BRLT, M_BREQ, M_BRLE, M_CALL,       // single-argument
    M_CPWA, M_DECI, M_DECO, M_ADDA, M_SUBA, M_STWA, M_LDWA, // double-argument
}