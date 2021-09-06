package main.model.lexanalyzer.tokens;

public class TComment extends AToken implements StrToken {
    
    private String comment; 

    public TComment(String cmt)
    {
        comment = cmt;
    }

    public String getDescribtion()
    {
        return String.format("Comment: %s", comment);
    }

    public String getStringValue()
    {
        return comment;
    }
}
