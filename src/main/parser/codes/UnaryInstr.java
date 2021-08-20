package main.parser.codes;

import main.parser.Maps;
import main.parser.Mnemon;

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
                return "0A\n";
            case M_ASRA: 
                return "0C\n";
            case M_STOP:
                return "00\n"; 
            case M_BLOCK:
                return "00\n";
            case M_END: 
                return "zz";
            default: 
                return "";  //Should not occur.
        }
    } 

    public String generateListing()
    {
        return Maps.mnemonStringTable.get(mnemonic) + "\n";
    } 

}
