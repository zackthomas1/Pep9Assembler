package main.parser.args;

public class IntArg extends AArg {
    
    private final int intValue; 

    public IntArg(int i)
    {
        intValue = i;
    }

    public String generateListing()
    {
        return String.format("%d", intValue);
    } 

}
