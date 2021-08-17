package Tokens;

import LexAnalyzer.Util;

public class THex extends AToken {

    private int hexIntValue; 
    private String hexSymbol;
    
    public THex(int i)
    {
        hexIntValue = i; 
        hexSymbol = Util.decimalToHexStr(i);
    }

    public THex(String h)
    {
        hexIntValue = Util.hexStrToDecimal(h);
        hexSymbol = h;
    }
    
    public String getDescribtion()
    {
        return String.format("Hexadecimal: %d", hexIntValue);
    }

    public int getHexIntValue()
    {
        return hexIntValue; 
    }

    public String getHexSymbol()
    {
        return hexSymbol;
    }
    
}
