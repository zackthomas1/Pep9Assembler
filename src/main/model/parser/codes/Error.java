package main.model.parser.codes;
public class Error extends ACode {
    
    private final String errorMessage; 
    private final int errorLine;

    public Error(String errMssg, int line)
    {
        errorMessage = errMssg;
        errorLine = line;
    }

    public String generateCode()
    {
        return "";
    }

    public String generateListing()
    {
        return "ERROR "  + "line " + errorLine  + ": " + errorMessage + "\n"; 
    } 
}