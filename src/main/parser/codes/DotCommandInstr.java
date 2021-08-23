package main.parser.codes;

import main.parser.Maps;
import main.parser.Mnemon;
import main.parser.args.AArg;
import main.parser.args.EmptyArg;
import main.parser.args.HexArg;
import main.parser.args.IntArg;

public class DotCommandInstr extends ACode {

    private final Mnemon mnemonic; 
    private final AArg operandSpecifier; 

    public DotCommandInstr(Mnemon mn)
    {
        mnemonic = mn; 
        operandSpecifier = new EmptyArg();
    }

    public DotCommandInstr(Mnemon mn, AArg os)
    {
        mnemonic = mn; 
        operandSpecifier = os;
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
                    hexCode = "00 ".repeat(hexArg.getHexValue());
                    hexCode = hexCode.substring(0, hexCode.length()-1);
                }
                hexCode += "\n";
                break;
            case M_END: 
                hexCode = "zz\n";
                break;
            default:        // Should not occur
                hexCode = " ";
                break;      
        }
        return hexCode;
    }

    @Override
    public String generateListing() {
        if(operandSpecifier instanceof EmptyArg){
            return String.format("%s\n", Maps.mnemonStringTable.get(mnemonic));
        }else{
            return String.format("%s %s\n", Maps.mnemonStringTable.get(mnemonic), operandSpecifier.generateListing());
        }
    }




    
}
