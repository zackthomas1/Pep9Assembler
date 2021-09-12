package main.model.parser.instrs;

import main.model.parser.Maps;
import main.model.parser.Mnemon;

public class UnaryInstr extends AInstr {
    
    private final Mnemon mnemonic;

    public UnaryInstr(Mnemon mn)
    {
        mnemonic = mn; 
        byteSize = 1;
    }
    
    public String generateCode()
    {
        switch(mnemonic)
        {
            case M_STOP:
                return "00"; 
            case M_RET: 
                return "01";
            case M_RETTR: 
                return "02";
            case M_MOVSPA: 
                return "03"; 
            case M_MOVFLGA: 
                return "04";
            case M_MOVAFLG: 
                return "05";
            case M_NOTA: 
                return "06";
            case M_NOTX: 
                return "07";    
            case M_NEGA: 
                return "08";
            case M_NEGX: 
                return "09";
            case M_RORA: 
                return "10";
            case M_RORX: 
                return "11"; 
            case M_ASLA: 
                return "0A";
            case M_ASLX: 
                return "0B";    
            case M_ASRA: 
                return "0C";
            case M_ASRX: 
                return "0D"; 
            case M_ROLA: 
                return "0E";
            case M_ROLX: 
                return "0F";    
     
            default: 
                return "";  //Should not occur.
        }
    } 

    public String generateListing()
    {
        return Maps.mnemonStringTable.get(mnemonic);
    } 

}
