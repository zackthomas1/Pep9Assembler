package main.parser.args;

public class IdentArg extends AArg  
{  
    private final String identValue;

    public IdentArg(String str)
    {
        identValue = str;
    }

    public String generateListing()
    {
        return identValue;
    } 

}
