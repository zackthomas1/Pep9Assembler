package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.Test;

import main.lexanalyzer.Tokenizer;
import main.lexanalyzer.tokens.AToken;
import main.lexanalyzer.tokens.TAddr;
import main.lexanalyzer.tokens.TDotCmd;
import main.lexanalyzer.tokens.TEmpty;
import main.lexanalyzer.tokens.THex;
import main.lexanalyzer.tokens.TIdentifier;
import main.lexanalyzer.tokens.TInteger;
import main.lexanalyzer.tokens.TInvalid;
import main.lexanalyzer.tokens.TSymbol;
import main.utility.InBuffer;

public class TokenizerTest {
   
    @Test
    public void getTokenTAddrTest()
    {
        InBuffer b1 = new InBuffer(",i"); 
        Tokenizer t1 = new Tokenizer(b1); 
        
        b1.getLine(); 
        AToken aToken1 = t1.getToken(); 

        assertTrue(aToken1 instanceof TAddr);

        InBuffer b2 = new InBuffer(",d"); 
        Tokenizer t2 = new Tokenizer(b2); 
        
        b2.getLine(); 
        AToken aToken2 = t2.getToken(); 

        assertTrue(aToken2 instanceof TAddr);

        InBuffer b3 = new InBuffer("i"); 
        Tokenizer t3 = new Tokenizer(b3); 
        
        b3.getLine(); 
        AToken aToken3 = t3.getToken(); 

        assertFalse(aToken3 instanceof TAddr);
    }

    @Test
    public void getTokenTDotCmdTest()
    {
        InBuffer b1 = new InBuffer(".End"); 
        Tokenizer t1 = new Tokenizer(b1); 
        
        b1.getLine(); 
        AToken aToken1 = t1.getToken(); 

        assertTrue(aToken1 instanceof TDotCmd);

        InBuffer b2 = new InBuffer(".block"); 
        Tokenizer t2 = new Tokenizer(b2); 
        
        b2.getLine(); 
        AToken aToken2 = t2.getToken(); 

        assertTrue(aToken2 instanceof TDotCmd);

        InBuffer b3 = new InBuffer("end"); 
        Tokenizer t3 = new Tokenizer(b3); 
        
        b3.getLine(); 
        AToken aToken3 = t3.getToken(); 

        assertFalse(aToken3 instanceof TDotCmd);
        assertTrue(aToken3 instanceof TIdentifier);

        InBuffer b4 = new InBuffer("block"); 
        Tokenizer t4 = new Tokenizer(b4); 
        
        b4.getLine(); 
        AToken aToken4 = t4.getToken(); 

        assertFalse(aToken4 instanceof TDotCmd);
        assertTrue(aToken4 instanceof TIdentifier);

    }

    @Test
    public void getTokenTEmptyTest()
    {
        InBuffer b1 = new InBuffer(" "); 
        Tokenizer t1 = new Tokenizer(b1); 
        
        b1.getLine(); 
        AToken aToken1 = t1.getToken(); 

        assertTrue(aToken1 instanceof TEmpty);

        InBuffer b2 = new InBuffer("       "); 
        Tokenizer t2 = new Tokenizer(b2); 
        
        b2.getLine(); 
        AToken aToken2 = t2.getToken(); 

        assertTrue(aToken2 instanceof TEmpty);

        InBuffer b3 = new InBuffer("!"); 
        Tokenizer t3 = new Tokenizer(b3); 
        
        b3.getLine(); 
        AToken aToken3 = t3.getToken(); 

        assertFalse(aToken3 instanceof TEmpty);
    }

    @Test
    public void getTokenTHexTest()
    {
        InBuffer b1 = new InBuffer("0x05"); 
        Tokenizer t1 = new Tokenizer(b1); 
        
        b1.getLine(); 
        AToken aToken1 = t1.getToken(); 

        assertTrue(aToken1 instanceof THex);

        InBuffer b2 = new InBuffer("0xA5fA"); 
        Tokenizer t2 = new Tokenizer(b2); 
        
        b2.getLine(); 
        AToken aToken2 = t2.getToken(); 

        assertTrue(aToken2 instanceof THex);

        InBuffer b3 = new InBuffer("A5"); 
        Tokenizer t3 = new Tokenizer(b2); 
        
        b3.getLine(); 
        AToken aToken3 = t3.getToken(); 

        assertFalse(aToken3 instanceof THex);
    }

    @Test
    public void getTokenTIdentifierTest()
    {
        InBuffer b1 = new InBuffer("Cat5"); 
        Tokenizer t1 = new Tokenizer(b1); 
        
        b1.getLine(); 
        AToken aToken1 = t1.getToken(); 

        assertTrue(aToken1 instanceof TIdentifier);

        InBuffer b2 = new InBuffer("Hello"); 
        Tokenizer t2 = new Tokenizer(b2); 
        
        b2.getLine(); 
        AToken aToken2 = t2.getToken(); 

        assertTrue(aToken2 instanceof TIdentifier);
        
        InBuffer b3 = new InBuffer("!Cat5"); 
        Tokenizer t3 = new Tokenizer(b3); 
        
        b3.getLine(); 
        AToken aToken3 = t3.getToken(); 

        assertFalse(aToken3 instanceof TIdentifier);
    }

    @Test
    public void getTokenTIntegerTest()
    {    
        InBuffer b1 = new InBuffer("5"); 
        Tokenizer t1 = new Tokenizer(b1); 
        
        b1.getLine(); 
        AToken aToken1 = t1.getToken(); 

        assertTrue(aToken1 instanceof TInteger);

        InBuffer b2 = new InBuffer("154"); 
        Tokenizer t2 = new Tokenizer(b2); 
        
        b2.getLine(); 
        AToken aToken2 = t2.getToken(); 

        assertTrue(aToken2 instanceof TInteger);
        
        InBuffer b3 = new InBuffer("a45"); 
        Tokenizer t3 = new Tokenizer(b3); 
        
        b3.getLine(); 
        AToken aToken3 = t3.getToken(); 

        assertFalse(aToken3 instanceof TInteger);
    }

    @Test
    public void getTokenTInvalidTest()
    {
        InBuffer b1 = new InBuffer("!cat5"); 
        Tokenizer t1 = new Tokenizer(b1); 
        
        b1.getLine(); 
        AToken aToken1 = t1.getToken(); 

        assertTrue(aToken1 instanceof TInvalid);

        InBuffer b2 = new InBuffer("+a"); 
        Tokenizer t2 = new Tokenizer(b2); 
        
        b2.getLine(); 
        AToken aToken2 = t2.getToken(); 

        assertTrue(aToken2 instanceof TInvalid);
        
        InBuffer b3 = new InBuffer(".5"); 
        Tokenizer t3 = new Tokenizer(b3); 
        
        b3.getLine(); 
        AToken aToken3 = t3.getToken(); 

        assertTrue(aToken3 instanceof TInvalid);

        InBuffer b4 = new InBuffer(",213"); 
        Tokenizer t4 = new Tokenizer(b4); 
        
        b4.getLine(); 
        AToken aToken4 = t4.getToken(); 

        assertTrue(aToken4 instanceof TInvalid);

        InBuffer b5 = new InBuffer("0xQ4"); 
        Tokenizer t5 = new Tokenizer(b5); 
        
        b5.getLine(); 
        AToken aToken5 = t5.getToken(); 

        assertTrue(aToken5 instanceof TInvalid);
        
        InBuffer b6 = new InBuffer("0xFFFF9"); 
        Tokenizer t6 = new Tokenizer(b6); 
        
        b6.getLine(); 
        AToken aToken6 = t6.getToken(); 

        assertTrue(aToken6 instanceof TInvalid);
    }

    @Test
    public void getTokenTSymbolTest()
    {
        InBuffer b1 = new InBuffer("cat:"); 
        Tokenizer t1 = new Tokenizer(b1); 
        
        b1.getLine(); 
        AToken aToken1 = t1.getToken();

        assertTrue(aToken1 instanceof TSymbol);

        InBuffer b2 = new InBuffer("cat :"); 
        Tokenizer t2 = new Tokenizer(b2); 
        
        b2.getLine(); 
        AToken aToken2 = t2.getToken();

        assertFalse(aToken2 instanceof TSymbol);

        InBuffer b3 = new InBuffer("CaR:"); 
        Tokenizer t3 = new Tokenizer(b3); 
        
        b3.getLine(); 
        AToken aToken3 = t3.getToken();

        assertTrue(aToken3 instanceof TSymbol);


    }

}
