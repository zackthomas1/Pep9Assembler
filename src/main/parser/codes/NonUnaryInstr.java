package main.parser.codes;

import main.parser.AddrMode;
import main.parser.Maps;
import main.parser.Mnemon;
import main.parser.args.AArg;
import main.parser.args.HexArg;
import main.parser.args.IdentArg;
import main.parser.args.IntArg;
import main.utility.Util;

public class NonUnaryInstr extends ACode{

    private final Mnemon mnemonic; 
    private final AArg operandSpecifier; 
    private final AddrMode address;

    public NonUnaryInstr (Mnemon mn, AArg os)
    {
        mnemonic = mn; 
        operandSpecifier = os; 
        address = AddrMode.AM_I; 
        byteSize = 3;
    }

    public NonUnaryInstr (Mnemon mn, AArg os, AddrMode addr)
    {
        mnemonic = mn; 
        operandSpecifier = os; 
        address =  addr;
        byteSize = 3;
    }
    @Override
    public String generateCode()
    {

        String hexCode;

        switch(mnemonic)
        {
            case M_BR:      // Operand Specifier
                hexCode = "12";
                break;
            case M_BRLT:
                hexCode = "16";
                break;
            case M_BREQ:
                hexCode = "18";
                break;
            case M_BRLE:
                hexCode = "14";
                break;
            case M_CALL: 
                hexCode = "24";
                break;
            case M_ADDA:    // Operand Specifier and Addressing Mode
                hexCode = "60";
                break;
            case M_CPWA:    
                hexCode = "A0";
                break;
            case M_DECI: 
                hexCode = "31";
                break;
            case M_DECO: 
                hexCode = "38";
                break;
            case M_LDWA: 
                hexCode = "C0";
                break;
            case M_SUBA: 
                hexCode = "70";
                break;
            case M_STWA: 
                hexCode = "E1";
                break;
            default:        // Should not occur
                hexCode = "";
                break;

        }

        // add addressing mode 
        hexCode = Util.intToHexStr(Util.hexStrToInt(hexCode) + Maps.MnemonValidAddresses.get(mnemonic).indexOf(address));

        // add operand specifier
        if (operandSpecifier instanceof IntArg){
            IntArg integerArg = (IntArg) operandSpecifier;
            hexCode += " " + Util.formatWord(integerArg.getIntValue()) + '\n';
        }else if(operandSpecifier instanceof HexArg){
            HexArg hexArg = (HexArg) operandSpecifier;
            hexCode += " " + Util.formatWord(hexArg.getIntValue()) + '\n';
        }else if(operandSpecifier instanceof IdentArg){
            IdentArg identArg = (IdentArg) operandSpecifier;
            hexCode += " " + Util.formatWord(Maps.symbolTable.get(identArg.getIdentStr())) + '\n';
        }else{
            hexCode += " ";
        }

        return hexCode;
    }

    @Override
    public String generateListing()
    {
        if (address == AddrMode.AM_I && Maps.MnemonValidAddresses.get(mnemonic).size() == 2)
        {
            return String.format("%s %s\n", Maps.mnemonStringTable.get(mnemonic), operandSpecifier.generateListing());

        } else {
            return String.format("%s %s, %s\n", Maps.mnemonStringTable.get(mnemonic), 
                        operandSpecifier.generateListing(), Maps.addressModeStringTable.get(address));
        }
    }
    
}
