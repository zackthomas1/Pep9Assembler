package Tokens;
public class TInteger extends AToken {
    private final int intValue; 

    public TInteger(int i)
    {
        intValue = i;
    }
    
    public int getIntValue()
    {
        return intValue;
    }
}
