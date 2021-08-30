package main.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import main.utility.Const;
import main.utility.InBuffer;
import main.utility.Util;
import main.lexanalyzer.Tokenizer;
import main.lexanalyzer.tokens.AToken;
import main.lexanalyzer.tokens.TAddr;
import main.lexanalyzer.tokens.TEmpty;
import main.lexanalyzer.tokens.THex;
import main.lexanalyzer.tokens.TIdentifier;
import main.lexanalyzer.tokens.TInteger;
import main.lexanalyzer.tokens.TInvalid;
import main.lexanalyzer.tokens.TSymbol;
import main.parser.args.AArg;
import main.parser.args.HexArg;
import main.parser.args.IdentifierSymbolArg;
import main.parser.args.IntArg;
import main.parser.codes.ACode;
import main.parser.codes.DotCommandInstr;
import main.parser.codes.EmptyInstr;
import main.parser.codes.Error;
import main.parser.codes.NonUnaryInstr;
import main.parser.codes.UnaryInstr;

public class Translator {
    
    private final InBuffer b; 
    private Tokenizer t; 
    private ACode aCode; 

    private ArrayList<ACode> codeTable;
    private int currentline;
    private int byteAdr;
    
    private static Map<String, Integer> symbolTable;
    private static Map<Integer, String> symbolAddressTable;

    public Translator(InBuffer inBuffer)
    {
        b = inBuffer; 
        codeTable = new ArrayList<>(); 
        currentline = 0;
        byteAdr = 0;

        symbolTable = new HashMap<>();
        symbolAddressTable = new HashMap<>();
    }

    // Sets aCode and returns boolean true if end statement is processed 
    private boolean parseLine()
    {
        boolean terminate = false; 
        Mnemon localMnemon = Mnemon.M_END; // useless initialization
        AArg localOperandArg = new IntArg(0); 
        AddrMode localAddrArg; 
        AToken aToken; 
        aCode = new EmptyInstr(); 
        ParseState state = ParseState.PS_START; 
        
        ++currentline;

        do{
            aToken = t.getToken();
            
            switch (state)
            {
                case PS_START:
                    if (aToken instanceof TIdentifier){
                        
                        TIdentifier localIdentifier = (TIdentifier) aToken;
                        String identStr = localIdentifier.getStringValue();
                        
                        if(Maps.unaryMnemonTable.containsKey(identStr.toLowerCase())){
                            localMnemon = Maps.unaryMnemonTable.get(identStr.toLowerCase());
                            state = ParseState.PS_UNARY;
                        } else if (Maps.dotCmdMnemonTable.containsKey(identStr.toLowerCase())){
                            localMnemon = Maps.dotCmdMnemonTable.get(identStr.toLowerCase());
                            terminate = localMnemon == Mnemon.M_END;
                            state = ParseState.PS_DOT1; 
                        } else if (Maps.nonunaryMnemonTable.containsKey(identStr.toLowerCase())){
                            localMnemon = Maps.nonunaryMnemonTable.get(identStr.toLowerCase());
                            state = ParseState.PS_FUNCTION; 
                        } else{
                            aCode = new Error("Invalid operation mnemonic.", currentline);
                        }
                    } else if (aToken instanceof TSymbol){
                        TSymbol locaTSymbol = (TSymbol) aToken;

                        if(symbolTable.containsKey(locaTSymbol.getStringValue())){
                            aCode = new Error(String.format("Duplicate instance of '%s' symbol found.", locaTSymbol.getStringValue()), currentline);
                        }else{
                            symbolTable.put(locaTSymbol.getStringValue(), byteAdr);
                            symbolAddressTable.put(byteAdr, locaTSymbol.getStringValue());
                            state = ParseState.PS_START;
                        }
                    } else if (aToken instanceof TEmpty){
                        aCode = new EmptyInstr();
                        state = ParseState.PS_FINISH;
                    } else if (aToken instanceof TInvalid){
                        TInvalid invalidToken = (TInvalid) aToken; 
                        aCode = new Error(invalidToken.getErrorMessage(), currentline);
                    }
                    else { 
                        aCode = new Error("Line must begin with function identifier", currentline);
                    }
                    break;
                case PS_FUNCTION: 
                    if (aToken instanceof TIdentifier){ // for symbols
                        TIdentifier localTIdentifier = (TIdentifier) aToken; 
                        
                        if(symbolTable.containsKey(localTIdentifier.getStringValue())){
                            localOperandArg = new IdentifierSymbolArg(localTIdentifier.getStringValue(), symbolTable.get(localTIdentifier.getStringValue())); 
                            state = ParseState.PS_NON_UNARY1; 
                        } else{ 
                            aCode = new Error(String.format("Invalid symbol. '%s' has not been declared",  localTIdentifier.getStringValue()), currentline);
                        }
                    }else if (aToken instanceof TInteger){
                        TInteger localTInteger = (TInteger) aToken;

                        if(localTInteger.getIntValue() < Const.TWOBYTEMIN || localTInteger.getIntValue() > Const.TWOBYTEMAX){
                            aCode = new Error("Integer value outside of valid range. Integer must be between -32768 and 65535.", currentline);
                        } else{
                            localOperandArg = new IntArg(localTInteger.getIntValue());
                            state = ParseState.PS_NON_UNARY1; 
                        }
                    } else if (aToken instanceof THex){
                        THex localTHex = (THex) aToken;

                        if(localTHex.getIntValue() < Const.TWOBYTEMIN || localTHex.getIntValue() > Const.TWOBYTEMAX){
                            aCode = new Error("Hex value outside of valid range. Hex must be between 0x0000(hex) 0(dec) and 0xFFFF(hex) 65535(dec).", currentline);
                        } else{
                            localOperandArg = new HexArg(localTHex.getIntValue());
                            state = ParseState.PS_NON_UNARY1; 
                        }
                    } else if (aToken instanceof TEmpty){
                        state = ParseState.PS_FINISH;
                    } else if (aToken instanceof TInvalid){
                        TInvalid invalidToken = (TInvalid) aToken; 
                        aCode = new Error(invalidToken.getErrorMessage(), currentline);
                    } else{ 
                        aCode = new Error("Operand invalid. Must be integer, hexadecimal, or symbol.", currentline);
                    }
                    break; 
                case PS_NON_UNARY1:
                    if (aToken instanceof TAddr){
                        TAddr localTAddr = (TAddr) aToken;
                        if (Maps.addressModeTable.containsKey(localTAddr.getStringValue()))
                        {
                            localAddrArg = Maps.addressModeTable.get(localTAddr.getStringValue()); 
                            
                            if (Maps.MnemonValidAddresses.get(localMnemon).contains(localAddrArg))
                            {
                                aCode = new NonUnaryInstr(localMnemon, localOperandArg, localAddrArg);
                                state = ParseState.PS_NON_UNARY2;    
                            } else{
                                aCode = new Error(String.format("'%s' is an invalid addressing mode for '%s' operation.", 
                                    Maps.addressModeStringTable.get(localAddrArg), Maps.mnemonStringTable.get(localMnemon)), currentline);
                            }
                        } else{
                            aCode = new Error("Invalid addressing mode.", currentline);
                        }
                    } else if (aToken instanceof TEmpty){
                        aCode = new NonUnaryInstr(localMnemon, localOperandArg);
                        state = ParseState.PS_FINISH;
                    } else{ 
                        aCode = new Error("Invalid addressing mode following first arugment.", currentline); 
                    }
                    break;       
                case PS_NON_UNARY2: 
                    if (aToken instanceof TEmpty){
                        state = ParseState.PS_FINISH;
                    } else{
                        aCode = new Error("Invalid character/s folllowing addressing mode.", currentline);
                    }
                    break;
                case PS_DOT1: 
                    if (aToken instanceof TInteger) {
                        TInteger localTInteger = (TInteger) aToken;
                        
                        if(localTInteger.getIntValue() < Const.TWOBYTEMIN || localTInteger.getIntValue() > Const.TWOBYTEMAX){
                            aCode = new Error("Integer value outside of valid range. Integer must be between -32768 and 65535.", currentline);
                        } else{
                            localOperandArg = new IntArg(localTInteger.getIntValue());
                            state = ParseState.PS_DOT2; 
                        }
                    } else if (aToken instanceof THex) {
                        THex localTHex = (THex) aToken;
                        
                        if(localTHex.getIntValue() < Const.TWOBYTEMIN || localTHex.getIntValue() > Const.TWOBYTEMAX){
                            aCode = new Error("Hex value outside of valid range. Hex must be between 0x0000(hex) 0(dec) and 0xFFFF(hex) 65535(dec).", currentline);
                        } else{
                            localOperandArg = new HexArg(localTHex.getIntValue());
                            state = ParseState.PS_DOT2; 
                        }
                    } else if (aToken instanceof TEmpty){
                        aCode = new DotCommandInstr(localMnemon);
                        state = ParseState.PS_FINISH;
                    } else {
                        aCode = new Error("Invalid operand specifier following dot command. Must be interger or hexadecimal.", currentline);
                    }
                    break; 
                case PS_DOT2: 
                    if (aToken instanceof TEmpty){
                        aCode = new DotCommandInstr(localMnemon, localOperandArg);
                        state = ParseState.PS_FINISH;
                    } else{ 
                        aCode = new Error("Invalid character/s following dot command.", currentline);
                    }
                    break;
                case PS_UNARY: 
                    if (aToken instanceof TEmpty){
                        aCode = new UnaryInstr(localMnemon);
                        terminate = localMnemon == Mnemon.M_END;
                        state = ParseState.PS_FINISH;
                    } else{
                        aCode = new Error("Invalid argument following unary instruction. Unary instructions take no arguments/s.", currentline);
                    }
                    break;
                default:
                    break;    
            }
        }while(state != ParseState.PS_FINISH && !(aCode instanceof Error));
        
        byteAdr += aCode.getByteSize();

        return terminate;
    }

    /**
     * 
     * returns true if translation was success and no errors occured
     * @return
     */
    public Boolean translate()
    {
        int numErrors = 0; 
        t = new Tokenizer(b); 
        boolean terminateWithEnd = false; 
      
        b.getLine();

        while (b.inputRemains() && !terminateWithEnd){
            terminateWithEnd = parseLine();
            codeTable.add(aCode);
            if (aCode instanceof Error){
                numErrors++; 
            }
            b.getLine();
        }
        
        // Generate error if input program does not end with ".END" command
        if(!terminateWithEnd){
            aCode = new Error("Missing \"end\" sentinel", currentline); 
            codeTable.add(aCode); 
            numErrors++;
        }

        return numErrors == 0;

    }

    public void systemOutCompiledProgram(Boolean translationValid)
    {
        // final output
        if (translationValid){      
            System.out.println("----------------");
            System.out.println("Object code:");;
            System.out.println("----------------");
            System.out.println(generateProgramCode());
           
            System.out.println("----------------------------------------------");
            System.out.println("Program listing:");
            System.out.println("----------------------------------------------");
            System.out.println("Addr \t Symbol \t Mnemon \t Operand \t Comment");
            System.out.println(generateProgramListing());

            System.out.println("----------------");
            System.out.println("Symbol Table:");
            System.out.println("----------------");
            System.out.println("Symbol \t Value");
            System.out.println(generateSymbolTable());            
        }else{ 
            System.out.println(generateProgramErrorMessages());
        }

    }
    
    public String generateProgramCode()
    {   
        StringBuffer buf = new StringBuffer();
    
        //  object code output  
        for (int i = 0; i < codeTable.size(); i++){
            buf.append(String.format("%s\n",  codeTable.get(i).generateCode()));
        }

        return buf.toString();
    }

    public String generateProgramListing()
    {
        StringBuffer buf = new StringBuffer();

        int localMemAddressCount = 0;


        //  program listing output
        for(int i = 0; i < codeTable.size(); i++){

            String symbolListing;
            if(symbolAddressTable.containsKey(localMemAddressCount)){
                symbolListing = symbolAddressTable.get(localMemAddressCount);
            }else{
                symbolListing = " ";
            }

            buf.append(String.format("%s \t %s \t\t %s\n", 
                    Util.formatWord(localMemAddressCount), symbolListing, codeTable.get(i).generateListing()));
            localMemAddressCount += codeTable.get(i).getByteSize();
        }

        return buf.toString();
    }

    public String generateSymbolTable()
    {
        StringBuffer buf = new StringBuffer(); 

        List<String> keys = symbolTable.keySet().stream().collect(Collectors.toList());

        for(int i = 0; i < keys.size(); ++i){
            buf.append(String.format("%s \t  %s\n", keys.get(i), Util.formatWord(symbolTable.get(keys.get(i)))));
        }
        return buf.toString();
    }

    public String generateProgramErrorMessages()
    {
        StringBuffer buf = new StringBuffer();

        //  error message output
        int numErrors = 0;
        for(int i = 0; i < codeTable.size(); i++){
            if (codeTable.get(i) instanceof Error){
                buf.append(String.format("%s", codeTable.get(i).generateListing()));
                ++numErrors;
            }  
        }
        buf.append(String.format("%d error/s were detected. \n", numErrors));
        
        return buf.toString();
    }
}
