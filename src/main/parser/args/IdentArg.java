package main.parser.args;

public class IdentArg extends AArg  
{  
    private final String identStr;

    public IdentArg(String str)
    {
        identStr = str;
    }

    public String generateListing()
    {
        return identStr;
    } 

    public String getIdentStr()
    {
        return identStr;
    }

}
