package main.lexanalyzer;
import main.utility.InBuffer;
import main.utility.Util;
import main.lexanalyzer.tokens.AToken;
import main.lexanalyzer.tokens.TAddr;
import main.lexanalyzer.tokens.TDotCmd;
import main.lexanalyzer.tokens.TEmpty;
import main.lexanalyzer.tokens.THex;
import main.lexanalyzer.tokens.TIdentifier;
import main.lexanalyzer.tokens.TInteger;
import main.lexanalyzer.tokens.TInvalid;
import main.lexanalyzer.tokens.TSymbol;
import main.parser.codes.Const;

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
                    if (Util.isDigit(nextChar) && nextChar - '0' == 0){         // LS_INT1
                        state = LexState.LS_INT1;
                    } else if (Util.isDigit(nextChar) && nextChar - '0' > 0){   // LS_INT2
                        localIntValue = nextChar - '0'; 
                        state = LexState.LS_INT2;
                    }else if (nextChar == '-'){                                 // LS_SIGN
                        sign = -1; 
                        state = LexState.LS_SIGN;                               
                    }else if (nextChar == '+'){                                 // LS_SIGN
                        sign = +1; 
                        state = LexState.LS_SIGN; 
                    }else if (Util.isAlpha(nextChar)){                          // LS_IDENT
                        localStringValue.append(nextChar); 
                        state = LexState.LS_IDENT; 
                    }else if (nextChar == '.'){                                 // LS_DOT1
                        state = LexState.LS_DOT1; 
                    }else if (nextChar == ','){                                 // LS_ADDR1
                        state = LexState.LS_ADDR1; 
                    }else if (nextChar == '\n'){
                        state = LexState.LS_STOP; 
                    }else if (nextChar != ' '){
                        aToken = new TInvalid("LS_START: Invalid character.");
                    }
                    break;
                case LS_INT1: 
                    if (Util.isDigit(nextChar)){
                        localIntValue = (localIntValue * 10) + (nextChar - '0');
                        state = LexState.LS_INT2; 
                    }else if (Util.isAlpha(nextChar) && nextChar == 'x'){
                        state = LexState.LS_HEX1; 
                    }else{
                        b.backUpInput();
                        aToken = new TInteger(sign * localIntValue);
                        state = LexState.LS_STOP;
                    }
                    break;
                case LS_SIGN: 
                    if (Util.isDigit(nextChar)){
                        localIntValue = nextChar - '0';
                        state = LexState.LS_INT2; 
                    }else{
                        aToken = new TInvalid("LS_SIGN: sign character must be followed by numerical to form valid integer.");
                    }
                    break; 
                case LS_INT2:
                    if(Util.isDigit(nextChar)){
                        localIntValue = (localIntValue * 10) + (nextChar - '0');
                        if ( localIntValue < Const.TWOBYTEMIN || localIntValue > Const.TWOBYTEMAX){
                            aToken = new TInvalid("LS_INT2: Integer outside of valid two byte range -32768 to 65535.");
                        }
                    }else{
                        b.backUpInput();
                        aToken = new TInteger(sign * localIntValue);
                        state = LexState.LS_STOP;
                    }
                    break; 
                case LS_IDENT:
                    if (Util.isAlpha(nextChar) || Util.isDigit(nextChar)){
                        localStringValue.append(nextChar); 
                    }else if(nextChar == ':'){
                        state = LexState.LS_SYMBOL;
                    }
                    else{
                        b.backUpInput();
                        aToken = new TIdentifier(localStringValue);
                        state = LexState.LS_STOP;
                    }
                    break; 
                case LS_DOT1: 
                    if (Util.isAlpha(nextChar)){
                        localStringValue.append(nextChar);
                        state = LexState.LS_DOT2; 
                    }else{
                        aToken = new TInvalid("LS_DOT1: Invalid dot command syntax. Character '.' must be followed by alphabetical character.");
                    }
                    break;
                case LS_ADDR1: 
                    if (Util.isAlpha(nextChar)){
                        localStringValue.append(nextChar);
                        state = LexState.LS_ADDR2; 
                    }else if (nextChar != ' '){
                        aToken = new TInvalid("LS_ADDR1: Invalid character used in adddress name. Use only alphabetical character."); 
                    }
                    break;
                case LS_HEX1: 
                    if (Util.isHexdigit(nextChar)){
                        localStringValue.append(nextChar);
                        localIntValue = (localIntValue * 16) + Util.hexCharToInt(nextChar);
                        state = LexState.LS_HEX2;
                    }else{
                        aToken = new TInvalid("LS_HEX1: '0x' must be followed by valid hexadecimal character 1-9 and a-f.");
                    }
                    break;
                case LS_SYMBOL: 
                    if( nextChar == ' ' || nextChar == '\n'){
                        b.backUpInput();
                        aToken = new TSymbol(localStringValue);
                        state = LexState.LS_STOP;
                    }else{
                        aToken = new TInvalid("LS_SYMBOL: Invalid character following symbol declaration");
                    }
                    break;
                case LS_HEX2: 
                    if(Util.isHexdigit(nextChar)){
                        localStringValue.append(nextChar);
                        localIntValue = (localIntValue * 16) + Util.hexCharToInt(nextChar);
                        if (localIntValue > Const.TWOBYTEMAX){
                            aToken = new TInvalid("LS_HEX2: Hex value exceeded max value of FFFF(hex)/65535(dec)");
                        }
                        state = LexState.LS_HEX2;
                    }else{
                        b.backUpInput();
                        aToken = new THex(localIntValue);
                        state = LexState.LS_STOP;
                    }
                    break;
                case LS_DOT2: 
                    if(Util.isAlpha(nextChar)){
                        localStringValue.append(nextChar);
                        state = LexState.LS_DOT2;
                    }else{
                        b.backUpInput();
                        aToken = new TDotCmd(localStringValue);
                        state = LexState.LS_STOP;
                    }
                    break;
                case LS_ADDR2: 
                    if(Util.isAlpha(nextChar)){
                        localStringValue.append(nextChar); 
                        state = LexState.LS_ADDR2; 
                    }else{
                        b.backUpInput(); 
                        aToken = new TAddr(localStringValue);
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
