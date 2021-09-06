package main.model.lexanalyzer.tokens;

import main.model.utility.Util;

public class THex extends AToken implements StrToken, IntToken {

    private int hexIntValue; 
    private String hexSymbol;
    
    public THex(int i)
    {
        hexIntValue = i; 
        hexSymbol = Util.intToHexStr(i);
    }

    public THex(String h)
    {
        hexIntValue = Util.hexStrToInt(h);
        hexSymbol = h;
    }

    public String getDescribtion()
    {
        return String.format("Hexcode: %s", hexSymbol);
    }

    public int getIntValue()
    {
        return hexIntValue; 
    }

    public String getStringValue()
    {
        return hexSymbol;
    }
    
}
