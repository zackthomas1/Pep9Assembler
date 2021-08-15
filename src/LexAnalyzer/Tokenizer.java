package LexAnalyzer;
import Tokens.AToken;
import Tokens.TEmpty;
import Tokens.TIdentifier;
import Tokens.TInteger;
import Tokens.TInvalid;

public class Tokenizer {
    private final InBuffer b; 
    
    public Tokenizer(InBuffer inBuffer)
    {
        b = inBuffer;
    }

    public AToken getToken()
    {
        char nextChar;

        StringBuffer localStringValue = new StringBuffer("");
        int localIntValue = 0; 
        
        int sign = +1;
        AToken aToken = new TEmpty();
        LexState state = LexState.LS_START; 

        do{
            nextChar = b.advanceInput();

            switch(state){
                case LS_START:
                    if (Util.isAlpha(nextChar) && nextChar - '0' == 0){ // LS
                        localStringValue.append(nextChar);
                        state = LexState.LS_INT1;
                    } else if (Util.isDigit(nextChar) && nextChar - '0' > 0){
                        localIntValue = nextChar - '0'; 
                        state = LexState.LS_INT2;
                    }else if (nextChar == '-'){
                        sign = -1; 
                        state = LexState.LS_SIGN; 
                    }else if (nextChar == '+'){
                        sign = +1; 
                        state = LexState.LS_SIGN; 
                    }else if (Util.isAlpha(nextChar)){  // LS_IDENT
                        localStringValue.append(nextChar); 
                        state = LexState.LS_IDENT; 
                    }else if (nextChar == '\n'){
                        state = LexState.LS_STOP; 
                    }else if (nextChar == '.'){
                        localStringValue.append(nextChar);
                        state = LexState.LS_DOT1; 
                    }else if (nextChar == ','){
                        localStringValue.append(nextChar);
                        state = LexState.LS_ADDR1; 
                    }
                    else if (nextChar != ' '){
                        aToken = new TInvalid();
                    }
                    break;
                
                case LS_IDENT:
                    if (Util.isAlpha(nextChar) || Util.isDigit(nextChar)){
                        localStringValue.append(nextChar); 
                    }else{
                        b.backUpInput();
                        aToken = new TIdentifier(localStringValue);
                        state = LexState.LS_STOP;
                    }
                    break; 
                case LS_SIGN: 
                    if (Util.isDigit(nextChar)){
                        localIntValue = nextChar - '0';
                        state = LexState.LS_INT2; 
                    }else{
                        aToken = new TInvalid();
                    }
                    break; 
                case LS_INT2:
                    if(Util.isDigit(nextChar))
                    {
                        localIntValue = (localIntValue * 10) + (nextChar - '0');

                    }else{
                        b.backUpInput();
                        aToken = new TInteger(sign * localIntValue);
                        state = LexState.LS_STOP;
                    }
                    break; 
                default: 
                    break;
            }
        }while((state != LexState.LS_STOP) && !(aToken instanceof TInvalid));
        return aToken;    
    }
}
