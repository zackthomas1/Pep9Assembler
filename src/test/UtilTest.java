package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.Test;

import main.utility.Util;

public class UtilTest {
   
    @Test
    public void isDigitTest()
    {
        assertFalse(Util.isDigit('i'));
        assertFalse(Util.isDigit('%'));
        assertFalse(Util.isDigit('A'));
       
        assertTrue(Util.isDigit('9'));
        assertTrue(Util.isDigit('0'));
    }
    
    @Test
    public void isAlphaTest()
    {
        assertFalse(Util.isAlpha('9'));
        assertFalse(Util.isAlpha('%'));
        assertFalse(Util.isAlpha('1'));
       
        assertTrue(Util.isAlpha('A'));
        assertTrue(Util.isAlpha('z'));
    }

    @Test
    public void isHexdigitTest()
    {
        assertFalse(Util.isHexdigit('g'));
        assertFalse(Util.isHexdigit('!'));
        assertFalse(Util.isHexdigit('Z'));

        assertTrue(Util.isHexdigit('A'));
        assertTrue(Util.isHexdigit('f'));
        assertTrue(Util.isHexdigit('0'));
        assertTrue(Util.isHexdigit('9'));
    }

    @Test
    public void hexCharToIntTest()
    {
        assertEquals(0, Util.hexCharToInt('0'));
        assertEquals(1, Util.hexCharToInt('1'));
        assertEquals(9, Util.hexCharToInt('9'));
        assertEquals(10, Util.hexCharToInt('A'));
        assertEquals(10, Util.hexCharToInt('a'));
        assertEquals(15, Util.hexCharToInt('F'));
        assertEquals(15, Util.hexCharToInt('f'));

    }

    @Test
    public void intToHexCharTest()
    {
        assertEquals('0', Util.intToHexChar(0));
        assertEquals('9', Util.intToHexChar(9));
        assertEquals('A', Util.intToHexChar(10));
        assertEquals('F', Util.intToHexChar(15));
    }

    @Test
    public void hexStrToIntTest()
    {
        assertEquals(1, Util.hexStrToInt("01"));
        assertEquals(9, Util.hexStrToInt("09"));
        assertEquals(10, Util.hexStrToInt("0A"));
        assertEquals(10, Util.hexStrToInt("0a"));
        assertEquals(15, Util.hexStrToInt("0F"));
        assertEquals(15, Util.hexStrToInt("0f"));
        assertEquals(165, Util.hexStrToInt("A5"));
    }

    @Test
    public void intToHexStrTest()
    {
        assertEquals("0", Util.intToHexStr(0));
        assertEquals("5", Util.intToHexStr(5));
        assertEquals("9", Util.intToHexStr(9));
        assertEquals("A", Util.intToHexStr(10));
        assertEquals("F", Util.intToHexStr(15));

    }

}
