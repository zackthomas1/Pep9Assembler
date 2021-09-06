package main.lexanalyzer.tokens;
public class TInteger extends AToken implements IntToken {
    
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
