package main.lexanalyzer.tokens;
public class TInvalid extends AToken {
    
    private String errorMessage;
    
    public TInvalid()
    {
        errorMessage = "Tokenizier Error: ";
    }

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
        return "Syntax Error";
    }
}
