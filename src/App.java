import main.utility.InBuffer;
import main.lexanalyzer.Tokenizer;
import main.lexanalyzer.tokens.AToken;
import main.lexanalyzer.tokens.TEmpty;
import main.lexanalyzer.tokens.TInvalid;
import main.parser.Translator;

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
