package main.parser.args;

import main.utility.Util;

public class HexArg extends AArg {

    private final int hexValue; 
    private final String hexCode; 

    public HexArg(int hv)
    {
        hexValue = hv;
        hexCode = Util.intToHexStr(hv);
    }

    public HexArg(String hc)
    {
        hexValue = Util.hexStrToInt(hc);
        hexCode = hc;
    }

    public String generateListing()
    {
        return String.format("0x%s", hexCode); 
    }




}
