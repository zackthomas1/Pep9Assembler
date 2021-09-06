package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.Test;

import main.model.utility.InBuffer;

public class InBufferTest {
    
    @Test
    public void getLineTest()
    {
        InBuffer b = new InBuffer("LDWA 0x00D, i \n .END"); 
        b.getLine();

        assertEquals(" .END\n\n", b.getInString());
    }
    @Test
    public void getInStringTest()
    {
        InBuffer b = new InBuffer("Hello World"); 

        assertEquals("Hello World\n\n", b.getInString());
    }
    
    @Test
    public void inputRemainsTest()
    {
        InBuffer b = new InBuffer("LDWA 0x00D, i \n .END"); 
        
        b.getLine();
        assertTrue(b.inputRemains());

        b.getLine();
        assertTrue(b.inputRemains());

        b.getLine();
        assertFalse(b.inputRemains());
    }
    
    @Test
    public void advanceInputTest()
    {
        String str = "LDWA 0x00D, i";
        InBuffer b = new InBuffer(str);
        b.getLine();

        str += "\n\n";

        int i = 0;
        char nextChar = ' ';
        while(nextChar != '\n')
        {
            nextChar = b.advanceInput(); 
            assertEquals(str.charAt(i), nextChar);
            ++i;
        }
    }
}
