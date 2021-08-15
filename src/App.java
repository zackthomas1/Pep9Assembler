import LexAnalyzer.InBuffer;
import LexAnalyzer.Tokenizer;
import Tokens.AToken;
import Tokens.TEmpty;
import Tokens.TInvalid;

public class App {
    public static void main(String[] args) throws Exception {

        InBuffer inBuffer = new InBuffer("-54"); 
        Tokenizer t = new Tokenizer(inBuffer); 
        AToken aToken; 

        inBuffer.getLine(); 
        while(inBuffer.inputRemains()){
            do{
                aToken = t.getToken();
            }while(!(aToken instanceof TEmpty) && !(aToken instanceof TInvalid));
            inBuffer.getLine();
        }
    }
}
