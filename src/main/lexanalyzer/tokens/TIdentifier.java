package main.lexanalyzer.tokens;

public class TIdentifier extends AToken {

    private String stringValue; 
    
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
