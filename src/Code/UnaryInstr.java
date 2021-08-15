package Code;

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
            case M_STOP:
                return "stop\n"; 
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
