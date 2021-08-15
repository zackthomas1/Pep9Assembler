package Args;

public class IntArg extends AArg {
    
    private final int intValue; 

    public IntArg(int i)
    {
        intValue = i;
    }

    public String generateCode()
    {
        return String.format("%d", intValue);
    } 

}
