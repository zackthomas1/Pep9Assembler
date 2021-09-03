package main.lexanalyzer.tokens;

public class TIdentifier extends AToken implements StrToken {

    private String identifier; 
    
    public TIdentifier(String ident)
    {
        identifier = ident; 
    }

    public String getDescribtion()
    {
        return String.format("Identifier: %s", identifier);
    }

    public String getStringValue()
    {
        return identifier;
    }
    
}
