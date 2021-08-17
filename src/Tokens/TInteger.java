package Tokens;
public class TInteger extends AToken {
    
    private int intValue; 

    public TInteger(int i)
    {
        intValue = i;
    }

    public String getDescribtion()
    {
        return String.format("Integer: %d", intValue);
    }
    
    public int getIntValue()
    {
        return intValue;
    }
}
