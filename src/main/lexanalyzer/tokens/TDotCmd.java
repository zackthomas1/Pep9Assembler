package main.lexanalyzer.tokens;

public class TDotCmd extends TIdentifier {
    
    public TDotCmd(StringBuffer dotCmmd)
    {
        stringValue = new String(dotCmmd); 
    }
    
    @Override
    public String getDescribtion()
    {
        return String.format("Dot Command: %s", stringValue);
    }

}
