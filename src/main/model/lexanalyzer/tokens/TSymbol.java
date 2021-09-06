package main.model.lexanalyzer.tokens;

public class TSymbol extends AToken {
   
    protected String stringValue; 

    public TSymbol()
    {
        stringValue = "";
    }
    
    public TSymbol(String sym)
    {
        stringValue = sym; 
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
