import LexAnalyzer.Tokenizer;
import LexAnalyzer.Tokens.AToken;
import LexAnalyzer.Tokens.TEmpty;
import LexAnalyzer.Tokens.TInvalid;
import Utility.InBuffer;

public class App {
    public static void main(String[] args) throws Exception {

        InBuffer inBuffer = new InBuffer("LDWX 0x00D, i"); 
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
