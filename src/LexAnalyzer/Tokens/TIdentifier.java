package LexAnalyzer.Tokens;

public class TIdentifier extends AToken {

    protected String stringValue; 

    public TIdentifier()
    {
        stringValue = "";
    }
    
    public TIdentifier(StringBuffer stringBuffer)
    {
        stringValue = new String(stringBuffer); 
    }

    public String getDescribtion()
    {
        return String.format("Identifier: %s", stringValue);
    }

    public String getStringValue()
    {
        return stringValue;
    }
    
}
