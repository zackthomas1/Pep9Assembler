package main.parser.args;

public class StringLiteralArg extends AArg {

    private String stringValue; 
    private int intValue; 

    public StringLiteralArg(String str)
    {   
        stringValue = str;
        intValue = 0;
        
        for(int i= 0; i < str.length(); i++)
        {
            intValue += str.charAt(i);
        }
    }

    public int getIntValue()
    {
        return intValue; 
    }

    public String generateListing() {
        return stringValue;
    }
    
}
