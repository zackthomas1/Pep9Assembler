package main.parser.args;

public class IntArg extends AArg {
    
    private final int intValue; 

    public IntArg(int i)
    {
        intValue = i;
    }

    public int getIntValue()
    {
        return intValue;
    }

    public String generateListing()
    {
        return String.format("%d", intValue);
    } 

}
