package main.parser.args;

import main.utility.Util;

public class HexArg extends AArg {

    private final int hexValue; 
    private final String hexStr; 

    public HexArg(int hv)
    {
        hexValue = hv;
        hexStr = Util.intToHexStr(hv);
    }

    public HexArg(String hstr)
    {
        hexValue = Util.hexStrToInt(hstr);
        hexStr = hstr;
    }

    public int getHexValue()
    {
        return hexValue; 
    }

    public String getHexStr()
    {
        return hexStr;
    }

    public String generateListing()
    {
        return String.format("0x%s", hexStr); 
    }
}
