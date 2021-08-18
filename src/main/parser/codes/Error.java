package main.parser.codes;
public class Error extends ACode {
    
    private final String errorMessage; 

    public Error(String errMessage)
    {
        errorMessage = errMessage;
    }

    public String generateCode()
    {
        return "";
    }

    public String generateListing()
    {
        return "ERROR: " + errorMessage + "\n"; 
    } 


}
