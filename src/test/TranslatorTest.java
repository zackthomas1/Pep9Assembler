package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import main.parser.Translator;
import main.utility.InBuffer;

public class TranslatorTest {
    
    @Test
    public void UnaryTest()
    {
        InBuffer b01 = new InBuffer("ASLA \n AsrA \n stop \n .End");
        Translator tr01 = new Translator(b01); 
        tr01.translate();

        assertEquals("0A\n0C\n00\nzz\n", tr01.generateProgramCode());
        assertEquals("asla\nasra\nstop\n.end\n", tr01.generateProgramListing());
    }
    
    @Test
    public void BlockTest()
    {
        InBuffer b01 = new InBuffer(".block 4 \n .End");
        Translator tr01 = new Translator(b01); 
        tr01.translate();

        assertEquals("00 00 00 00 \nzz\n", tr01.generateProgramCode());


        InBuffer b02 = new InBuffer(".BLock 0x04 \n .end");
        Translator tr02 = new Translator(b02); 
        tr02.translate();

        assertEquals("00 00 00 00 \nzz\n", tr02.generateProgramCode());
    }

    @Test
    public void parseLineADDA()
    {
        InBuffer b01 = new InBuffer("ADDA 4, d \n ADDa 0x00Af, i \n ADdA 5, s \n ADDA 0x00A3, sx \n.End");
        Translator tr01 = new Translator(b01); 
        tr01.translate();

        assertEquals("61 00 04\n60 00 AF\n63 00 05\n66 00 A3\nzz\n", tr01.generateProgramCode());
    }
    
    @Test
    public void parseLineLDWA()
    {
        InBuffer b01 = new InBuffer("Ldwa 4, d \n LDWA 0x00Af, i \n LDwA 5, s \n ldwA 0x00A3, sx \n.End");
        Translator tr01 = new Translator(b01); 
        tr01.translate();

        assertEquals("C1 00 04\nC0 00 AF\nC3 00 05\nC6 00 A3\nzz\n", tr01.generateProgramCode());
    }

    @Test
    public void parseLineEmptySTWA()
    {
        InBuffer b01 = new InBuffer("STWA 4, d \n STWA 0x00Af, x \n STWA 5, s \n STWA 0x00A3, sx \n.End");
        Translator tr01 = new Translator(b01); 
        tr01.translate();

        assertEquals("E1 00 04\nE5 00 AF\nE3 00 05\nE6 00 A3\nzz\n", tr01.generateProgramCode());
    }
    
}
