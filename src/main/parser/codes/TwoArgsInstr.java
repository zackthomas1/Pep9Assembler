package main.parser.codes;

import main.parser.Maps;
import main.parser.Mnemon;
import main.parser.args.AArg;

public class TwoArgsInstr extends ACode {

    private Mnemon mnemonic; 
    private AArg firstArg; 
    private AArg secondArg;


    public TwoArgsInstr(Mnemon mn, AArg fArg, AArg sArg)
    {
        mnemonic = mn; 
        this.firstArg = fArg;
        this.secondArg = sArg; 
    }

    public String generateCode()
    {
        switch(mnemonic)
        {
            case M_CPWA: 
                return String.format("Accumulator <- %s Addressing: %s\n", firstArg.generateCode(), secondArg.generateCode());
            case M_DECI: 
                return String.format("Accumulator <- %s Addressing: %s\n", firstArg.generateCode(), secondArg.generateCode());
            case M_DECO: 
                return String.format("Accumulator <- %s Addressing: %s\n", firstArg.generateCode(), secondArg.generateCode());
            case M_ADDA: 
                return String.format("Accumulator <- %s Addressing: %s\n", firstArg.generateCode(), secondArg.generateCode());
            case M_SUBA: 
                return String.format("Accumulator <- %s Addressing: %s\n", firstArg.generateCode(), secondArg.generateCode());
            case M_STWA: 
                return String.format("Accumulator <- %s Addressing: %s\n", firstArg.generateCode(), secondArg.generateCode());
            case M_LDWA: 
                return String.format("Accumulator <- %s Addressing: %s\n", firstArg.generateCode(), secondArg.generateCode());
            default: 
                return ""; // should not occur.
        }
    } 

    public String generateListing()
    {
        return String.format("%s (%s, %s)\n", Maps.mnemonStringTable.get(mnemonic), 
                firstArg.generateCode(),
                secondArg.generateCode()); 
    } 

}
