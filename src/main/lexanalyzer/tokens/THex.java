package main.lexanalyzer.tokens;

import main.utility.Util;

public class THex extends AToken {

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
        return String.format("Hexadecimal: %d", hexIntValue);
    }

    public int getIntValue()
    {
        return hexIntValue; 
    }

    public String getHexSymbol()
    {
        return hexSymbol;
    }
    
}
