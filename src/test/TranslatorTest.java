package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import main.parser.Translator;
import main.utility.InBuffer;

public class TranslatorTest {
        
    @Test
    public void bookTest()
    {
        InBuffer b01 = new InBuffer("BR 0x007, i \n" + 
                            ".BLOCK 4 \n" + 
                            "deci 0x2   ,d \n" + 
                            "LDWA   +2,  d \n" + 
                            "AdDa -5,   i \n" + 
                            "STWA    0x0004, d \n" +
                            "       DECO   0x04, d \n" +
                            "Stop \n" + 
                            ".End");

        Translator tr01 = new Translator(b01); 
        
        assertTrue(tr01.translate());
        String string = "12 00 07\n00 00 00 00\n31 00 02\nC1 00 02\n60 FF FB\nE1 00 04\n39 00 04\n00\nzz\n";
        assertEquals(string , tr01.generateProgramCode());
    }

    @Test
    public void unaryTest()
    {
        InBuffer b01 = new InBuffer("ASLA \n AsrA \n stop \n .End");
        Translator tr01 = new Translator(b01); 
        
        assertTrue(tr01.translate());
        assertEquals("0A\n0C\n00\nzz\n", tr01.generateProgramCode());
    }
    
    @Test 
    public void dotCommandTest()
    {
        InBuffer b01 = new InBuffer(" end"); 
        Translator tr01 = new Translator(b01); 

        assertFalse(tr01.translate());

        InBuffer b02 = new InBuffer(".end"); 
        Translator tr02 = new Translator(b02); 

        assertTrue(tr02.translate());
        assertEquals("zz\n", tr02.generateProgramCode());

        InBuffer b03 = new InBuffer(".end 4"); 
        Translator tr03 = new Translator(b03); 

        assertFalse(tr03.translate());
    }

    @Test
    public void blockTest()
    {
        InBuffer b01 = new InBuffer(".block 4 \n .End");
        Translator tr01 = new Translator(b01); 

        assertTrue(tr01.translate());
        assertEquals("00 00 00 00\nzz\n", tr01.generateProgramCode());

        InBuffer b02 = new InBuffer(".BLock 0x04 \n .end");
        Translator tr02 = new Translator(b02); 

        assertTrue(tr02.translate());
        assertEquals("00 00 00 00\nzz\n", tr02.generateProgramCode());

        InBuffer b03 = new InBuffer("block 4 \n .end"); 
        Translator tr03 = new Translator(b03); 

        assertFalse(tr03.translate());

        InBuffer b05 = new InBuffer(" .block \n .end"); 
        Translator tr05 = new Translator(b05); 

        assertFalse(tr05.translate());
    }

    @Test
    public void parseLineADDA()
    {
        InBuffer b01 = new InBuffer("ADDA -4, d \n ADDa 0x00Af, i \n ADdA 65535, s \n ADDA 0x00A3, sx \n.End");
        Translator tr01 = new Translator(b01); 
       
        assertTrue(tr01.translate());
        assertEquals("61 FF FC\n60 00 AF\n63 FF FF\n66 00 A3\nzz\n", tr01.generateProgramCode());
    }
    
    @Test
    public void parseLineLDWA()
    {
        InBuffer b01 = new InBuffer("Ldwa -4, d \n LDWA 0x00Af, i \n LDwA 5, s \n ldwA 0x00A3, sx \n.End");
        Translator tr01 = new Translator(b01); 

        assertTrue(tr01.translate());
        assertEquals("C1 FF FC\nC0 00 AF\nC3 00 05\nC6 00 A3\nzz\n", tr01.generateProgramCode());
    }

    @Test
    public void parseLineEmptySTWA()
    {
        InBuffer b01 = new InBuffer("STWA -4, d \n STwA 0x00Af, x \n sTWA 5, s \n STWA 0x00A3, sx \n.End");
        Translator tr01 = new Translator(b01); 

        assertTrue(tr01.translate());
        assertEquals("E1 FF FC\nE5 00 AF\nE3 00 05\nE6 00 A3\nzz\n", tr01.generateProgramCode());
    }

    @Test
    public void parseSymbol()
    {
        InBuffer b01 = new InBuffer("s: .Block 4 \n t: .Block 4 \n LDWA t, d \n.End");
        Translator tr01 = new Translator(b01); 

        assertTrue(tr01.translate());
        assertEquals("00 00 00 00\n00 00 00 00\nC1 00 04\nzz\n", tr01.generateProgramCode());
    }

    
    @Test
    public void parseSymbolBeforeDeclarationTest()
    {
        InBuffer b02 = new InBuffer("br main \n s: .block 4 \n t: .BLOCK 6 \n main: LDWA s, d \n STOP \n .end");
        Translator tr02 = new Translator(b02); 

        assertTrue(tr02.translate());
        assertEquals("12 00 0D\n00 00 00 00\n00 00 00 00 00 00\nC1 00 03\n00\nzz\n", tr02.generateProgramCode());
    }

    @Test
    public void invalidSymbolParseTest()
    {
        InBuffer b03 = new InBuffer("s: \n LDWA 5 \n .end");
        Translator tr03 = new Translator(b03); 

        assertFalse(tr03.translate());
    }
}
