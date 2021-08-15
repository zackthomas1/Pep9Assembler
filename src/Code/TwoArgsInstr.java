package Code;

import Args.AArg;

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
            case M_SET: 
                return String.format("%s <- %s\n", firstArg.generateCode(), 
                        secondArg.generateCode());
            case M_ADD: 
                return String.format("%s <- %s + %s\n", firstArg.generateCode(), 
                        firstArg.generateCode(),
                        secondArg.generateCode());
            case M_SUB: 
                return String.format("%s <- %s - %s\n", firstArg.generateCode(), 
                        firstArg.generateCode(),
                        secondArg.generateCode());
            case M_MUL: 
            return String.format("%s <- %s * %s\n", firstArg.generateCode(), 
                    firstArg.generateCode(),
                    secondArg.generateCode());
            case M_DIV: 
            return String.format("%s <- %s / %s\n", firstArg.generateCode(), 
                firstArg.generateCode(),
                secondArg.generateCode());
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
