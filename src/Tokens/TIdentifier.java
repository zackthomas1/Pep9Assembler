package Tokens;

public class TIdentifier extends AToken {

    private final String stringValue; 

    public TIdentifier(StringBuffer stringBuffer)
    {
        stringValue = new String(stringBuffer); 
    }

    public String getStringValue()
    {
        return stringValue;
    }
    
}
