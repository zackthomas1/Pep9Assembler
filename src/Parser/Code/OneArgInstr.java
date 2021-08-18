package Parser.Code;

import Parser.Maps;
import Parser.Mnemon;
import Parser.Args.AArg;

public class OneArgInstr extends ACode {
    private final Mnemon mnemonic; 
    private final AArg aArg;

    public OneArgInstr(Mnemon mn, AArg aArg)
    {
        mnemonic = mn; 
        this.aArg = aArg; 
    }

    public String generateCode()
    {
        switch(mnemonic)
        {
            case M_ABS:
                return String.format("%s <- |%s|\n", aArg.generateCode(), aArg.generateCode());
            case M_NEG: 
                return String.format("%s <- -%s\n", aArg.generateCode(), aArg.generateCode());
            default: 
                return ""; // Should not occur
        }
    } 

    public String generateListing()
    {
        return String.format("%s (%s)\n", Maps.mnemonStringTable.get(mnemonic), aArg.generateCode()); 

    } 

}
