package main.model.parser;
public enum Mnemon {
    // Unary
    M_ASLA, M_ASLX, M_ASRA, M_ASRX, M_ROLA, M_ROLX, M_RORA, M_RORX, M_NOTA, M_NOTX, M_NEGA, M_NEGX,
    M_MOVSPA, M_MOVFLGA, M_MOVAFLG,M_RETTR, M_RET, M_STOP, 

    // Dot Command
    M_BLOCK, M_END,  
     
    // Non-Unary
    M_BR, M_BRLE, M_BRLT, M_BREQ, M_BRNE, M_BRGE, M_BRGT, M_BRV, M_BRC, M_CALL,               // non addressed
    M_DECI, M_DECO, M_HEXO, M_STRO,                                                           // addressed
    M_ADDSP, M_SUBSP,
    M_ADDA, M_ADDX,  M_SUBA, M_SUBX, M_ANDA, M_ANDX, M_ORA, M_ORX,
    M_CPWA, M_STWA, M_LDWA, 
}