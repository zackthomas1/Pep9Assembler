package Tokens;

public class TDot extends AToken {
    
    private String dotCommand;

    public TDot(StringBuffer dotCmmd)
    {
        dotCommand = new String(dotCmmd); 
    }
    
    public String getDescribtion()
    {
        return String.format("Dot Command: %s", dotCommand);
    }

    public String getDotCommand()
    {
        return dotCommand;
    }


}
