package main.model.parser;
public enum Mnemon {
    // Unary
    M_ASLA, M_ASRA, M_STOP, 

    // Dot Command
    M_BLOCK, M_END,  
     
    // Non-Unary
    M_BR, M_BRLT, M_BREQ, M_BRLE, M_CALL,       // single-argument
    M_CPWA, M_DECI, M_DECO, M_ADDA, M_SUBA, M_STWA, M_LDWA, // double-argument
}