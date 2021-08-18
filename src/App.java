import LexAnalyzer.Tokenizer;
import LexAnalyzer.Tokens.AToken;
import LexAnalyzer.Tokens.TEmpty;
import LexAnalyzer.Tokens.TInvalid;
import Parser.Translator;
import Utility.InBuffer;

public class App {

    public static void test_01()
    {    
        InBuffer inBuffer = new InBuffer("LDWA 0x00D, i \n .END"); 
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

    public static void test_02()
    {    
        InBuffer inBuffer = new InBuffer("LDWA 0x00D, i \n STWA -15, d\n .END");
        Translator tr = new Translator(inBuffer); 
        tr.translate();
    }


    public static void main(String[] args) throws Exception {
        test_02();
    }
}
