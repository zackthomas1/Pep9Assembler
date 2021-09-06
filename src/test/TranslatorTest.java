package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import main.model.Generator;
import main.model.parser.Translator;
import main.model.utility.InBuffer;

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
        Generator g01 = new Generator(tr01);
        
        assertTrue(tr01.translate());
        String string = "12 00 07\n00 00 00 00\n31 00 02\nC1 00 02\n60 FF FB\nE1 00 04\n39 00 04\n00\nzz\n";
        assertEquals(string , g01.generateObjectCode());
    }

    @Test
    public void unaryTest()
    {
        InBuffer b01 = new InBuffer("ASLA \n AsrA \n stop \n .End");
        Translator tr01 = new Translator(b01); 
        Generator g01 = new Generator(tr01);
        
        assertTrue(tr01.translate());
        assertEquals("0A\n0C\n00\nzz\n", g01.generateObjectCode());
    }
    
    @Test 
    public void endTest()
    {
        InBuffer b01 = new InBuffer(" end"); 
        Translator tr01 = new Translator(b01); 

        assertFalse(tr01.translate());

        InBuffer b02 = new InBuffer(".end"); 
        Translator tr02 = new Translator(b02); 
        Generator g02 = new Generator(tr02);

        assertTrue(tr02.translate());
        assertEquals("zz\n", g02.generateObjectCode());

        InBuffer b03 = new InBuffer(".end 4"); 
        Translator tr03 = new Translator(b03); 

        assertFalse(tr03.translate());

        InBuffer b04 = new InBuffer(".end 0x44"); 
        Translator tr04 = new Translator(b04); 

        assertFalse(tr04.translate());

        InBuffer b05 = new InBuffer(".end main"); 
        Translator tr05 = new Translator(b05); 

        assertFalse(tr05.translate());
    }

    @Test
    public void blockTest()
    {
        InBuffer b01 = new InBuffer(".block 4 \n .End");
        Translator tr01 = new Translator(b01); 
        Generator g01 = new Generator(tr01);

        assertTrue(tr01.translate());
        assertEquals("00 00 00 00\nzz\n", g01.generateObjectCode());

        InBuffer b02 = new InBuffer(".BLock 0x04 \n .end");
        Translator tr02 = new Translator(b02); 
        Generator g02 = new Generator(tr01);

        assertTrue(tr02.translate());
        assertEquals("00 00 00 00\nzz\n", g02.generateObjectCode());

        InBuffer b03 = new InBuffer("block 4 \n .end"); 
        Translator tr03 = new Translator(b03); 

        assertFalse(tr03.translate());

        InBuffer b05 = new InBuffer(" .block \n .end"); 
        Translator tr05 = new Translator(b05); 

        assertFalse(tr05.translate());

        InBuffer b06 = new InBuffer(" .block \"Hellow String\" \n .end"); 
        Translator tr06 = new Translator(b06); 

        assertFalse(tr06.translate());

        InBuffer b07 = new InBuffer(" .block main \n .end"); 
        Translator tr07 = new Translator(b07); 

        assertFalse(tr07.translate());
    }

    @Test
    public void addaTest()
    {
        InBuffer b01 = new InBuffer("ADDA -4, d \n ADDa 0x00Af, i \n ADdA 65535, s \n ADDA 0x00A3, sx \n.End");
        Translator tr01 = new Translator(b01); 
        Generator g01 = new Generator(tr01);
       
        assertTrue(tr01.translate());
        assertEquals("61 FF FC\n60 00 AF\n63 FF FF\n66 00 A3\nzz\n", g01.generateObjectCode());
    }
    
    @Test
    public void ldwaTest()
    {
        InBuffer b01 = new InBuffer("Ldwa -4, d \n LDWA 0x00Af, i \n LDwA 5, s \n ldwA 0x00A3, sx \n.End");
        Translator tr01 = new Translator(b01); 
        Generator g01 = new Generator(tr01);

        assertTrue(tr01.translate());
        assertEquals("C1 FF FC\nC0 00 AF\nC3 00 05\nC6 00 A3\nzz\n", g01.generateObjectCode());
    }

    @Test
    public void stwaTest()
    {
        InBuffer b01 = new InBuffer("STWA -4, d \n STwA 0x00Af, x \n sTWA 5, s \n STWA 0x00A3, sx \n.End");
        Translator tr01 = new Translator(b01); 
        Generator g01 = new Generator(tr01);

        assertTrue(tr01.translate());
        assertEquals("E1 FF FC\nE5 00 AF\nE3 00 05\nE6 00 A3\nzz\n", g01.generateObjectCode());
    }

    @Test
    public void commentLineTest()
    {
        InBuffer b01 = new InBuffer(";Hlloe this is a commment line \n" + 
                            ".End ; Goodbye");

        Translator tr01 = new Translator(b01); 
        Generator g01 = new Generator(tr01);

        assertTrue(tr01.translate());
        String string = "zz\n";
        assertEquals(string , g01.generateObjectCode());
    }

    @Test
    public void commentUnaryNonUnary()
    {
        InBuffer b01 = new InBuffer(";hello this is a commment line \n" + 
                            "LDWA +2, d ; comment3 \n" +
                            "STOP ; #2 comment \n" +
                            ".End ; Goodbye");

        Translator tr01 = new Translator(b01); 
        Generator g01 = new Generator(tr01);

        assertTrue(tr01.translate());
        String string = "C1 00 02\n00\nzz\n";
        assertEquals(string , g01.generateObjectCode());
    }

    @Test
    public void commentDotCommandTest()
    {
        InBuffer b01 = new InBuffer(";hello this is a commment line \n" + 
                            ".BLOCK 4 \n" + 
                            ".End ; Goodbye");

        Translator tr01 = new Translator(b01); 
        Generator g01 = new Generator(tr01);

        assertTrue(tr01.translate());
        String string = "00 00 00 00\nzz\n";
        assertEquals(string , g01.generateObjectCode());
    }

    @Test
    public void commentTest()
    {
        InBuffer b01 = new InBuffer("BR 0x007, i ; helllo world \n" + 
                            ".BLOCK 4 ;    \n" + 
                            "deci 0x2 ,d;This is a comment 3 \n" + 
                            "LDWA   +2,  d ;33333%!@#$$ \n" + 
                            "AdDa -5,   i ;      fsdf5435/* \n" + 
                            "STWA    0x0004, d  ; hellow rosd\n" +
                            "       DECO   0x04, d ;TESTING \n" +
                            "Stop ; GOOOD BYE\n" +
                            ";Hlloe this is a commment line \n" + 
                            ".End ; Goodbye");

        Translator tr01 = new Translator(b01); 
        Generator g01 = new Generator(tr01);
        
        assertTrue(tr01.translate());
        String string = "12 00 07\n00 00 00 00\n31 00 02\nC1 00 02\n60 FF FB\nE1 00 04\n39 00 04\n00\nzz\n";
        assertEquals(string , g01.generateObjectCode());
    }

    @Test
    public void parseSymbol()
    {
        InBuffer b01 = new InBuffer("s: .Block 4 \n t: .Block 4 \n LDWA t, d \n.End");
        Translator tr01 = new Translator(b01); 
        Generator g01 = new Generator(tr01);

        assertTrue(tr01.translate());
        assertEquals("00 00 00 00\n00 00 00 00\nC1 00 04\nzz\n", g01.generateObjectCode());
    }

    
    @Test
    public void parseSymbolBeforeDeclarationTest()
    {
        InBuffer b01 = new InBuffer("br main \n s: .block 4 \n t: .BLOCK 6 \n main: LDWA s, d \n STOP \n .end");
        Translator tr01 = new Translator(b01); 
        Generator g01 = new Generator(tr01);

        assertTrue(tr01.translate());
        assertEquals("12 00 0D\n00 00 00 00\n00 00 00 00 00 00\nC1 00 03\n00\nzz\n", g01.generateObjectCode());
    }

    @Test
    public void invalidSymbolParseTest()
    {
        InBuffer b03 = new InBuffer("s: \n LDWA 5 \n .end");
        Translator tr03 = new Translator(b03); 

        assertFalse(tr03.translate());
    }

    @Test
    public void leadingTabsTest()
    {
        InBuffer b1 = new InBuffer("ldwa \t\t 4	, i \n" + 
                                    "\t Stwa 5   , \t d \n" +
                                    "\t   .end\t	"); 
        Translator tr01 = new Translator(b1); 
        Generator g01 = new Generator(tr01);

        assertTrue(tr01.translate());
        assertEquals("C0 00 04\nE1 00 05\nzz\n", g01.generateObjectCode());
    }
}
