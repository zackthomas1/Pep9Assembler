package main.parser.codes;
abstract public class ACode {
    
    protected int byteSize;

    abstract public String generateCode(); 
    abstract public String generateListing(); 

    public int getByteSize() {
        return byteSize;
    }
}
