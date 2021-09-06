package main.model.parser.codes;

public class EmptyInstr extends ACode {
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