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
}
