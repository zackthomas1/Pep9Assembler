package main.lexanalyzer.tokens;

public class TAddress extends AToken {
    
    private String stringValue; 

    public TAddress(StringBuffer addrss)
    {
        stringValue = new String(addrss);
    }

    @Override
    public String getDescribtion()
    {
        return String.format("Address: %s", stringValue);
    }

    public String getStringValue()
    {
        return stringValue;
    }
}
