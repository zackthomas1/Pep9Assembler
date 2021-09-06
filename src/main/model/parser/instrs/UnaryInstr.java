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
            case M_ASLA: 
                return "0A";
            case M_ASRA: 
                return "0C";
            case M_STOP:
                return "00"; 
            default: 
                return "";  //Should not occur.
        }
    } 

    public String generateListing()
    {
        return Maps.mnemonStringTable.get(mnemonic);
    } 

}
