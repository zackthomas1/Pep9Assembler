import LexAnalyzer.InBuffer;
import LexAnalyzer.Tokenizer;
import Tokens.AToken;
import Tokens.TEmpty;
import Tokens.TInvalid;

public class App {
    public static void main(String[] args) throws Exception {

        InBuffer inBuffer = new InBuffer("alpha .beta \n   b7 0x23ab ,Sfx \n   ,i , cat \n -32768 65535"); 
        Tokenizer t = new Tokenizer(inBuffer); 
        AToken aToken; 

        inBuffer.getLine(); 
        while(inBuffer.inputRemains()){
            do{
                aToken = t.getToken();
                System.out.println(aToken.getDescribtion());
            }while(!(aToken instanceof TEmpty) && !(aToken instanceof TInvalid));
            inBuffer.getLine();
        }
    }
}
