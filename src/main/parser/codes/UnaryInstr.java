package main.parser.codes;

import main.parser.Maps;
import main.parser.Mnemon;

public class UnaryInstr extends ACode {
    
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
                return "0A\n";
            case M_ASRA: 
                return "0C\n";
            case M_STOP:
                return "00\n"; 
            default: 
                return "";  //Should not occur.
        }
    } 

    public String generateListing()
    {
        return Maps.mnemonStringTable.get(mnemonic) + "\n";
    } 

}
