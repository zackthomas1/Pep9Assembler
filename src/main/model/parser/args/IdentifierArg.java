package main.model.parser.args;

public class IdentifierArg extends AArg  
{  
    private final String identifierStr;

    public IdentifierArg(String str)
    {
        identifierStr = str;
    }

    public String generateListing()
    {
        return identifierStr;
    } 
    
    public String getIdentStr()
    {
        return identifierStr;
    }

}
