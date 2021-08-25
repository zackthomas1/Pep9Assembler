import main.utility.InBuffer;
import main.utility.Util;
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
        InBuffer inBuffer = new InBuffer("STWA 0xA5, d \n STWA 4, n \n  .BLOCK 6 \n ASRA \n Stop \n .End");
        Translator tr = new Translator(inBuffer); 
        tr.translate();
    }

    public static void test_03()
    {    
        int i = Util.hexStrToInt("05");
        System.out.println(i);
    }

    public static void test_04()
    {
        InBuffer inBuffer = new InBuffer("cat:"); 
        Tokenizer t = new Tokenizer(inBuffer); 
        
        inBuffer.getLine(); 
        AToken aToken = t.getToken(); 
        
        System.out.println(aToken.getDescribtion());
    }

    public static void test_05()
    {    
        InBuffer inBuffer = new InBuffer("BR 0x007, i \n ." + 
                                    "BLOCK 4 \n" + 
                                    "deci 0x2   ,d \n" + 
                                    "LDWA   +2,  d \n" + 
                                    "AdDa -5,   i \n" + 
                                    "STWA    0x0004, d \n" +
                                    "       DECO   0x04, d \n" +
                                    "Stop \n" + 
                                    ".End");
        Translator tr = new Translator(inBuffer); 
        tr.translate();
    }

    public static void test_06()
    {    
        InBuffer inBuffer = new InBuffer(" AdDa -5, i \n Stop \n .End");
        Translator tr = new Translator(inBuffer); 
        tr.translate();
    }

    public static void test_07()
    {    
        InBuffer inBuffer = new InBuffer ("s: .Block 4 \n t: .Block 4 \n u: LDWA t, d \n.End");
        Translator tr = new Translator(inBuffer); 
        tr.translate();
    }

    public static void main(String[] args) throws Exception {
        test_07();
    }
}
