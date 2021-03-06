package main.model.parser.instrs;
abstract public class AInstr {
    
    protected int byteSize;

    abstract public String generateCode(); 
    abstract public String generateListing(); 

    public int getByteSize() {
        return byteSize;
    }
}
