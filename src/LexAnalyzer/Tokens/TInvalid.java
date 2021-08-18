package LexAnalyzer.Tokens;
public class TInvalid extends AToken {
    
    private String errorMessage;
    
    public TInvalid()
    {
        errorMessage = "Tokenizier Error: ";
    }

    public TInvalid(String errmsg)
    {
        errorMessage = errorMessage + errmsg; 
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
