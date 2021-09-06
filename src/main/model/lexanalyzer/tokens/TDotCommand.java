package main.model.lexanalyzer.tokens;

public class TDotCommand extends AToken implements StrToken {

    private String dotCommand;

    public TDotCommand(String dtcmd)
    {
        dotCommand = dtcmd;
    }

    public String getDescribtion() {
        return String.format("Dot Command: %s", dotCommand);
    }
    
    public String getStringValue() {
        return dotCommand;
    }
}
