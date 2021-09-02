package main.lexanalyzer.tokens;
public class TInvalid extends AToken {
    
    private String errorMessage;
    
    public TInvalid(String errmsg)
    {
        errorMessage = errmsg; 
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public String getDescribtion()
    {
        return "Error: " + errorMessage;
    }
}
