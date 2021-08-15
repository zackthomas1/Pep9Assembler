package Args;

public class IdentArg extends AArg  
{  
    private final String identValue;

    public IdentArg(String str)
    {
        identValue = str;
    }

    public String generateCode()
    {
        return identValue;
    } 

}
