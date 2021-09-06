package main.lexanalyzer.tokens;

public class TAddress extends AToken implements StrToken {
    
    private String address; 

    public TAddress(String addrs)
    {
        address = addrs;
    }

    public String getDescribtion()
    {
        return String.format("Address: %s", address);
    }

    public String getStringValue()
    {
        return address;
    }
}
