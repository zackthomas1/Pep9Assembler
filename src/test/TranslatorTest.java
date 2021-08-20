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
        InBuffer b01 = new InBuffer(".block 4 \n .BLock 0xA4 \n .End");
        Translator tr01 = new Translator(b01); 
        tr01.translate();

        // assertEquals("0A\n0C\n00\nzz\n", tr01.generateProgramCode());
        assertEquals("asla\nasra\nstop\n.end\n", tr01.generateProgramListing());
    }
    
    @Test
    public void parseLineDotTest()
    {

    }

    @Test
    public void parseLineEmptyTest()
    {

    }
    
}
