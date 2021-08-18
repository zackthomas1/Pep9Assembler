package LexAnalyzer.Tokens;

public class TDot extends TIdentifier {
    
    public TDot(StringBuffer dotCmmd)
    {
        stringValue = new String(dotCmmd); 
    }
    
    @Override
    public String getDescribtion()
    {
        return String.format("Dot Command: %s", stringValue);
    }

}
