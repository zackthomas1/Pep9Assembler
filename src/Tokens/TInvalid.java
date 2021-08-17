package Tokens;
public class TInvalid extends AToken {
    
    private String errorMessage;
    
    public TInvalid()
    {
        errorMessage = "";
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
