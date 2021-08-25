package main.parser.codes;
abstract public class ACode {
    
    protected int byteSize;

    public ACode()
    {
        byteSize = 0;
    }

    abstract public String generateCode(); 
    abstract public String generateListing(); 

    public int getByteSize() {
        return byteSize;
    }
}
