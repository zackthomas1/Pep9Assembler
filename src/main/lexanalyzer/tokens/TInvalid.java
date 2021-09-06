package main.lexanalyzer.tokens;
public class TInvalid extends AToken implements StrToken {
    
    private String errorMessage;
    
    public TInvalid(String errmsg)
    {
        errorMessage = errmsg; 
    }

    public String getDescribtion()
    {
        return "Error: " + errorMessage;
    }

    public String getStringValue()
    {
        return errorMessage;
    }
}
