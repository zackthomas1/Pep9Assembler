package main.model.parser.instrs;

public class EmptyInstr extends AInstr {
    // For an empty source line
   
    public String generateCode()
    {
        return "";
    }

    public String generateListing()
    {
        return "\n";
    }

}
