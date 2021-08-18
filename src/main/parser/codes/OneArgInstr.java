package main.parser.codes;

import main.parser.Maps;
import main.parser.Mnemon;
import main.parser.args.AArg;

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
            case M_BR:
                return String.format("br %s\n", aArg.generateCode(), aArg.generateCode());
            case M_BRLT:
                return String.format("brlt %s\n", aArg.generateCode(), aArg.generateCode());
            case M_BREQ:
                return String.format("breq %s\n", aArg.generateCode(), aArg.generateCode());
            case M_BRLE:
                return String.format("brle %s\n", aArg.generateCode(), aArg.generateCode());             
            case M_CALL: 
                return String.format("call %s\n", aArg.generateCode(), aArg.generateCode());
            default: 
                return ""; // Should not occur
        }
    } 

    public String generateListing()
    {
        return String.format("%s (%s)\n", Maps.mnemonStringTable.get(mnemonic), aArg.generateCode()); 

    } 

}
