package LexAnalyzer.Tokens;

public class TAddr extends TIdentifier {
    
    public TAddr(StringBuffer addrss)
    {
        stringValue = new String(addrss);
    }

    @Override
    public String getDescribtion()
    {
        return String.format("Address: %s", stringValue);
    }
}
