package main.model.parser.instrs;

import main.model.parser.AddressMode;
import main.model.parser.Maps;
import main.model.parser.Mnemon;
import main.model.parser.args.AArg;
import main.model.parser.args.HexArg;
import main.model.parser.args.IdentifierArg;
import main.model.parser.args.IntArg;
import main.model.parser.args.OperandSpecifiedInstr;
import main.model.utility.Util;

public class NonUnaryInstr extends AInstr implements OperandSpecifiedInstr{

    private final Mnemon mnemonic; 
    private final AArg operandSpecifier; 
    private final AddressMode address;

    public NonUnaryInstr (Mnemon mn, AArg os, AddressMode addr)
    {
        mnemonic = mn; 
        operandSpecifier = os; 
        address =  addr;
        byteSize = 3;
    }
    
    public String generateCode()
    {

        String hexCode;

        switch(mnemonic)
        {
            case M_BR:      // Operand Specifier
                hexCode = "12";
                break;
            case M_BRLE:
                hexCode = "14";
                break;
            case M_BRLT:
                hexCode = "16";
                break;
            case M_BREQ:
                hexCode = "18";
                break;
            case M_BRNE: 
                hexCode = "1A";
                break;
            case M_BRGE:
                hexCode = "1C";
                break;
            case M_BRGT:
                hexCode = "1E";
                break;
            case M_BRV:
                hexCode = "20";
                break;
            case M_BRC:
                hexCode = "22";
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
            hexCode += " " + Util.formatWord(integerArg.getIntValue());
        }else if(operandSpecifier instanceof HexArg){
            HexArg hexArg = (HexArg) operandSpecifier;
            hexCode += " " + Util.formatWord(hexArg.getIntValue());
        }else if(operandSpecifier instanceof IdentifierArg){
            IdentifierArg identArg = (IdentifierArg) operandSpecifier;
            if(Maps.symbolTable.containsKey(identArg.getIdentStr())){
                hexCode += " " + Util.formatWord(Maps.symbolTable.get(identArg.getIdentStr()));
            }
            else{
                // if symbol is not in table
                hexCode += " " + "xx xx";
            }
        }else{
            hexCode += " ";
        }

        return hexCode;
    }

    public String generateListing()
    {
        if (address == AddressMode.AM_I && 
        (mnemonic == Mnemon.M_BR || mnemonic == Mnemon.M_BRLT || mnemonic == Mnemon.M_BREQ || mnemonic == Mnemon.M_BRLE || mnemonic == Mnemon.M_CALL))
        {
            return String.format("%s \t\t %s", 
                        Maps.mnemonStringTable.get(mnemonic), operandSpecifier.generateListing());
        }else {
            return String.format("%s \t\t %s, %s", 
                        Maps.mnemonStringTable.get(mnemonic), operandSpecifier.generateListing(), Maps.addressModeStringTable.get(address));
        }
    }
    
    public AArg getOperandSpecifier()
    {
        return operandSpecifier;
    }

}
