package Parser.Code;

import Parser.Maps;
import Parser.Mnemon;

public class UnaryInstr extends ACode {
    
    private final Mnemon mnemonic;

    public UnaryInstr(Mnemon mn)
    {
        mnemonic = mn; 
    }
    
    public String generateCode()
    {
        switch(mnemonic)
        {
            case M_ASLA: 
                return "asla\n";
            case M_ASRA: 
                return "asra\n";
            case M_STOP:
                return "stop\n"; 
            case M_BLOCK:
                return "block\n";
            case M_END: 
                return "";
            default: 
                return "";  //Should not occur.
        }
    } 

    public String generateListing()
    {
        return Maps.mnemonStringTable.get(mnemonic) + "\n";
    } 

}
