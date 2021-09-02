package main.lexanalyzer.tokens;

public class TDotCommand extends AToken {
    
    private String stringValue; 

    public TDotCommand(StringBuffer dotCmmd)
    {
        stringValue = new String(dotCmmd); 
    }
    
    @Override
    public String getDescribtion()
    {
        return String.format("Dot Command: %s", stringValue);
    }

    public String getStringValue()
    {
        return stringValue;
    }


}

