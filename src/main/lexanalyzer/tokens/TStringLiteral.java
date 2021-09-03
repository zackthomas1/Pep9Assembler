package main.lexanalyzer.tokens;

public class TStringLiteral extends AToken implements StrToken {
    private String stringLiteral; 

    public TStringLiteral(String strLit)
    {
        stringLiteral = strLit;
    }

    public String getDescribtion()
    {
        return String.format("String Literal: %s", stringLiteral);
    }

    public String getStringValue()
    {
        return stringLiteral;
    }
}
