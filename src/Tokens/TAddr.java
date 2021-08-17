package Tokens;

public class TAddr extends AToken {
    
    private String address; 

    public TAddr(StringBuffer addrss)
    {
        address = new String(addrss);
    }

    public String getDescribtion()
    {
        return String.format("Address: %s", address);
    }

    public String getAddress()
    {
        return address;
    }
}
