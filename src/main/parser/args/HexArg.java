package main.parser.args;

import main.utility.Util;

public class HexArg extends AArg {

    private final int hexValue; 
    private final String hexCode; 

    public HexArg(int hexValue)
    {
        this.hexValue = hexValue;
        hexCode = Util.intToHexStr(hexValue);
    }

    public HexArg(String hexCode)
    {
        hexValue = Util.hexStrToInt(hexCode);
        this.hexCode = hexCode;
    }

    public String generateListing()
    {
        return String.format("%d", hexValue); 
    }




}
