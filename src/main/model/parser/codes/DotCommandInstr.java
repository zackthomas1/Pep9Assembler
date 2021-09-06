package main.model.parser.codes;

import main.model.parser.Maps;
import main.model.parser.Mnemon;
import main.model.parser.args.AArg;
import main.model.parser.args.HexArg;
import main.model.parser.args.IntArg;

public class DotCommandInstr extends ACode {

    private final Mnemon mnemonic; 
    private final AArg operandSpecifier; 

    public DotCommandInstr(Mnemon mn, AArg os)
    {
        mnemonic = mn; 
        operandSpecifier = os;
        
        byteSize = setInstrByteSize();
    }

    @Override
    public String generateCode() {

        String hexCode = "";

        switch(mnemonic)
        {
            case M_BLOCK:
                if (operandSpecifier instanceof IntArg){
                    IntArg integerArg = (IntArg) operandSpecifier;
                    hexCode = "00 ".repeat(integerArg.getIntValue());
                    hexCode = hexCode.substring(0, hexCode.length()-1);

                }else if (operandSpecifier instanceof HexArg){
                    HexArg hexArg = (HexArg) operandSpecifier;
                    hexCode = "00 ".repeat(hexArg.getIntValue());
                    hexCode = hexCode.substring(0, hexCode.length()-1);
                }
                break;
            case M_END: 
                hexCode = "zz";
                break;
            default:        // Should not occur
                hexCode = " ";
                break;      
        }
        return hexCode;
    }

    @Override
    public String generateListing() {
        if(operandSpecifier == null){
            return String.format("%s", Maps.mnemonStringTable.get(mnemonic));
        }else{
            return String.format("%s \t %s", Maps.mnemonStringTable.get(mnemonic), operandSpecifier.generateListing());
        }
    }

    private int setInstrByteSize()
    {
        if (mnemonic == Mnemon.M_BLOCK)
        {
            if (operandSpecifier instanceof IntArg){
                IntArg integerArg = (IntArg) operandSpecifier;
                return integerArg.getIntValue();
            }else if (operandSpecifier instanceof HexArg){
                HexArg hexArg = (HexArg) operandSpecifier;
                return hexArg.getIntValue();
            }else
                return -1;
        }else if (mnemonic == Mnemon.M_END){
            return 0;
        }else{
            return -1;
        }
    }


    
}
