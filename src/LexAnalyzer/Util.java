package LexAnalyzer;
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

    public static int charToHexDigit(char ch){

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

    public static char hexDigitToChar(int i)
    {
        switch(i)
        {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
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

    public static int hexStrToDecimal(String h)
    {
        int hexValue = 0;

        for(int i = h.length() - 1; i <= 0 ; --i)
        {
            int hexDigit = charToHexDigit(h.charAt(i));
            hexValue = (hexValue * 16) + hexDigit;
        }

        return hexValue;
    }

    public static String decimalToHexStr(int i)
    {
        StringBuffer hexcode = new StringBuffer("");

        while(i > 0)
        {
            hexcode.append(hexDigitToChar(i % 16));
            i = i / 16;
        }

        hexcode.reverse();
        return hexcode.toString();
        
    }
}
