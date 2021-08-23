package main.lexanalyzer.tokens;

public class TSymbol extends AToken {
   
    protected String stringValue; 

    public TSymbol()
    {
        stringValue = "";
    }
    
    public TSymbol(StringBuffer stringBuffer)
    {
        stringValue = new String(stringBuffer); 
    }

    @Override
    public String getDescribtion()
    {
        return String.format("Symbol: %s", stringValue);
    }

    public String getStringValue()
    {
        return stringValue;
    }
    
}
