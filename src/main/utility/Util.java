package main.utility;

public class Util {
    

    public static boolean isDigit(char ch)
    {
        return ('0' <= ch) && (ch <= '9');
    }

    public static boolean isAlpha(char ch)
    {
        return (('a' <= ch) && (ch <= 'z') || ('A' <= ch) && (ch <= 'Z'));
    }

    public static boolean isHexdigit(char ch)
    {
        return (('a' <= ch) && (ch <= 'f') || ('A' <= ch) && (ch <= 'F') || isDigit(ch));
    }

    public static int hexCharToInt(char ch){

        final int LOWERCASETINT = 96;  // subtract value from lower case character for integer

        if (('a' <= ch) && (ch <= 'f') || ('A' <= ch) && (ch <= 'F')){
            ch = Character.toLowerCase(ch);
            return 9 + (ch - LOWERCASETINT);
        }else if (isDigit(ch)) {
            return ch - '0';
        }else{
            return -1;
        }
    }

    public static char intToHexChar(int i)
    {
        switch(i)
        {
            case 0:
                // fall through
            case 1:
                // fall through
            case 2:
                // fall through
            case 3:
                // fall through
            case 4:
                // fall through
            case 5:
                // fall through
            case 6:
                // fall through
            case 7:
                // fall through
            case 8:
                // fall through
            case 9: 
                return (char)(i + '0');
            case 10:
                return 'A';
            case 11:
                return 'B';
            case 12:
                return 'C'; 
            case 13:
                return 'D';
            case 14:
                return 'E';
            case 15:
                return 'F';
            default: 
                return '\0';
        }
    }

    public static int hexStrToInt(String h)
    {
        int hexValue = 0;

        int hexPlace = 0;
        for(int i = h.length() - 1; i >= 0 ; --i)
        {
            int hexDigit = hexCharToInt(h.charAt(i));
            hexValue = (hexDigit * (int)Math.pow(16, hexPlace)) + hexValue;
            hexPlace++;
        }

        return hexValue;
    }

    public static String intToHexStr(int i)
    {
        StringBuffer hexcode = new StringBuffer("");

        //
        if (i == 0)
        {
            hexcode.append("00");
            return hexcode.toString();
        }
        
        while(i > 0)
        {
            hexcode.append(intToHexChar(i % 16));
            i = i / 16;
        }

        if (hexcode.length() % 2 == 1)
        {
            hexcode.append('0');
        }

        hexcode.reverse();
        return hexcode.toString();
    }
   
    public static String formatWord(int i)
    {    
        // signed values
        if (i < 0){
            i = Const.TWOBYTEMAX - (-1 * (i + 1));
        } 

        String leftbyte = intToHexStr(i / 256);
        String rightbyte = intToHexStr(i % 256);

        return leftbyte + " " + rightbyte;
    }


    public static String formatByte(int i)
    {
        // signed values
        if (i < 0){
            i = Const.TWOBYTEMAX - (-1 * (i + 1));
        } 

        String rightbyte = intToHexStr(i % 256);

        return rightbyte;
    }

}
