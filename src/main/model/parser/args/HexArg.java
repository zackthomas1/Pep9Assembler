package main.model.parser.args;

import main.model.utility.Util;

public class HexArg extends AArg {

    private final int intValue; 
    private final String hexStr; 

    public HexArg(int hv)
    {
        intValue = hv;
        hexStr = Util.intToHexStr(hv);
    }

    public HexArg(String hstr)
    {
        intValue = Util.hexStrToInt(hstr);
        hexStr = hstr;
    }

    public int getIntValue()
    {
        return intValue; 
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
