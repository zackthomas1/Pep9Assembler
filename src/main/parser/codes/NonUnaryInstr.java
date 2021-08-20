package main.parser.codes;

import main.parser.AddrMode;
import main.parser.Maps;
import main.parser.Mnemon;
import main.parser.args.AArg;

public class NonUnaryInstr extends ACode{

    private final Mnemon mnemonic; 
    private final AArg operandSpecifier; 
    private final AddrMode address;

    public NonUnaryInstr (Mnemon mn, AArg os)
    {
        mnemonic = mn; 
        operandSpecifier = os; 
        address = AddrMode.AM_UNSET; 
    }

    public NonUnaryInstr (Mnemon mn, AArg os, AddrMode addr)
    {
        mnemonic = mn; 
        operandSpecifier = os; 
        address =  addr;
    }

    public String generateCode()
    {
        String hexCode;
        switch(mnemonic)
        {
            case M_BR:      // Operand Specifier
                hexCode = " ";
            case M_BRLT:
                hexCode = " ";
            case M_BREQ:
                hexCode = " ";
            case M_BRLE:
                hexCode = " ";
            case M_CALL: 
                hexCode = " ";
            case M_CPWA:    // Operand Specifier and Addressing Mode
                hexCode = " ";
            case M_DECI: 
                hexCode = " ";
            case M_DECO: 
                hexCode = " ";
            case M_ADDA: 
                hexCode = "60";
            case M_SUBA: 
                hexCode = "70";
            case M_STWA: 
                hexCode = "E0";
            case M_LDWA: 
                hexCode = "C0";
            default:        // Should not occur
                hexCode = " ";
        }

        return hexCode;
    }

    public String generateListing()
    {
        if (address == AddrMode.AM_UNSET)
        {
            return String.format("%s %s\n", Maps.mnemonStringTable.get(mnemonic), operandSpecifier.generateListing());

        } else {
            return String.format("%s %s, %s\n", Maps.mnemonStringTable.get(mnemonic), 
                        operandSpecifier.generateListing(), Maps.addressModeStringTable.get(address));
        }
    }
    
}
