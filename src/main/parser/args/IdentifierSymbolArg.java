package main.parser.args;

public class IdentifierSymbolArg extends AArg  
{  
    private final String identifierStr;
    private final int identifierValue;

    public IdentifierSymbolArg(String str, int val)
    {
        identifierStr = str;
        identifierValue = val;
    }

    public String generateListing()
    {
        return identifierStr;
    } 
    
    public String getIdentStr()
    {
        return identifierStr;
    }

    public int getIdentifierValue()
    {
        return identifierValue;
    }
}
