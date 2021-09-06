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
import main.lexanalyzer.tokens.TAddress;
import main.lexanalyzer.tokens.TComment;
import main.lexanalyzer.tokens.TDotCommand;
import main.lexanalyzer.tokens.TEmpty;
import main.lexanalyzer.tokens.THex;
import main.lexanalyzer.tokens.TIdentifier;
import main.lexanalyzer.tokens.TInteger;
import main.lexanalyzer.tokens.TInvalid;
import main.lexanalyzer.tokens.TStringLiteral;
import main.lexanalyzer.tokens.TSymbol;
import main.parser.args.AArg;
import main.parser.args.HexArg;
import main.parser.args.IdentifierArg;
import main.parser.args.IntArg;
import main.parser.args.StringLiteralArg;
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
        AddressMode localAddrArg; 
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
                        } else if (Maps.nonunaryMnemonTable.containsKey(identStr.toLowerCase())){
                            localMnemon = Maps.nonunaryMnemonTable.get(identStr.toLowerCase());
                            state = ParseState.PS_FUNCTION; 
                        } else{
                            aCode = new Error("Invalid operation mnemonic.", currentline);
                        }
                    } else if (aToken instanceof TDotCommand){
                        TDotCommand localDotCommand = (TDotCommand) aToken;
                        String dotCommand = localDotCommand.getStringValue();

                        if (Maps.dotCmdMnemonTable.containsKey(dotCommand.toLowerCase())){
                            localMnemon = Maps.dotCmdMnemonTable.get(dotCommand.toLowerCase());
                            terminate = localMnemon == Mnemon.M_END;
                            state = ParseState.PS_DOT1; 
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
                            state = ParseState.PS_SYMBOL;
                        }
                    } else if(aToken instanceof TComment){
                        state = ParseState.PS_START; 
                    }else if (aToken instanceof TEmpty){
                        aCode = new EmptyInstr();
                        state = ParseState.PS_FINISH;
                    } else if (aToken instanceof TInvalid){
                        TInvalid invalidToken = (TInvalid) aToken; 
                        aCode = new Error(invalidToken.getStringValue(), currentline);
                    }
                    else { 
                        aCode = new Error("Line must begin with function identifier", currentline);
                    }
                    break;
                case PS_SYMBOL: 
                    break;
                case PS_FUNCTION: 
                    if (aToken instanceof TInteger){
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
                    }else if (aToken instanceof TIdentifier){
                        TIdentifier localTIdentifier = (TIdentifier) aToken;            
                        if(symbolTable.containsKey(localTIdentifier.getStringValue())){
                            localOperandArg = new IdentifierArg(localTIdentifier.getStringValue(), symbolTable.get(localTIdentifier.getStringValue())); 
                            state = ParseState.PS_NON_UNARY1; 
                        } else{ 
                            aCode = new Error(String.format("Invalid symbol. '%s' has not been declared",  localTIdentifier.getStringValue()), currentline);
                        }
                    }else if (aToken instanceof TStringLiteral){
                        TStringLiteral localTStringLiteral = (TStringLiteral) aToken;

                        if(localTStringLiteral.getStringValue().length() > 2){
                            aCode = new Error("String operands must have length at most two.", currentline);
                        }else{
                            localOperandArg = new StringLiteralArg(localTStringLiteral.getStringValue());
                            state = ParseState.PS_NON_UNARY1; 
                        }
                    } else if (aToken instanceof TInvalid){
                        TInvalid invalidToken = (TInvalid) aToken; 
                        aCode = new Error(invalidToken.getStringValue(), currentline);
                    } else{ 
                        aCode = new Error("Operand invalid. Must be integer, hexadecimal, or symbol.", currentline);
                    }
                    break; 
                case PS_NON_UNARY1:
                    if (aToken instanceof TAddress){
                        TAddress localTAddr = (TAddress) aToken;
                        if (Maps.addressModeTable.containsKey(localTAddr.getStringValue())){
                            localAddrArg = Maps.addressModeTable.get(localTAddr.getStringValue()); 
                            
                            if (Maps.MnemonValidAddresses.get(localMnemon).contains(localAddrArg)){
                                aCode = new NonUnaryInstr(localMnemon, localOperandArg, localAddrArg);
                                state = ParseState.PS_NON_UNARY2;    
                            }else{
                                aCode = new Error(String.format("'%s' is an invalid addressing mode for '%s' instruction mnemonic.", 
                                    Maps.addressModeStringTable.get(localAddrArg), Maps.mnemonStringTable.get(localMnemon)), currentline);
                            }
                        }else{
                            aCode = new Error("Invalid addressing mode.", currentline);
                        }
                    } else if (aToken instanceof TComment) {
                        if(localMnemon == Mnemon.M_BR || localMnemon == Mnemon.M_BRLT || localMnemon == Mnemon.M_BREQ || 
                        localMnemon == Mnemon.M_BRLE || localMnemon == Mnemon.M_CALL) 
                        {
                            aCode = new NonUnaryInstr(localMnemon, localOperandArg, AddressMode.AM_I);
                            state = ParseState.PS_COMMENT;
                        } else{
                            aCode = new Error("Missing addressing mode.", currentline); 
                        }
                    } else if (aToken instanceof TEmpty){
                        if(localMnemon == Mnemon.M_BR || localMnemon == Mnemon.M_BRLT || localMnemon == Mnemon.M_BREQ || 
                        localMnemon == Mnemon.M_BRLE || localMnemon == Mnemon.M_CALL) 
                        {
                            aCode = new NonUnaryInstr(localMnemon, localOperandArg, AddressMode.AM_I);
                            state = ParseState.PS_FINISH;
                        }else{
                            aCode = new Error("Invalid addressing mode.", currentline); 
                        }
                    } else{ 
                        aCode = new Error("Invalid addressing mode.", currentline); 
                    }
                    break;       
                case PS_NON_UNARY2: 
                    if (aToken instanceof TEmpty){
                        state = ParseState.PS_FINISH;
                    }else if(aToken instanceof TComment){
                        state = ParseState.PS_COMMENT;           
                    }else{
                        aCode = new Error("Invalid character/s folllowing addressing mode.", currentline);
                    }
                    break;
                case PS_DOT1: 
                    if (aToken instanceof TInteger) {
                        if(localMnemon == Mnemon.M_END) {
                            aCode = new Error("Only a comment can follow .END", currentline);
                            break;
                        }

                        TInteger localTInteger = (TInteger) aToken;

                        if(localTInteger.getIntValue() < Const.TWOBYTEMIN || localTInteger.getIntValue() > Const.TWOBYTEMAX){
                            aCode = new Error("Integer value outside of valid range. Integer must be between -32768 and 65535.", currentline);
                        }else{
                            localOperandArg = new IntArg(localTInteger.getIntValue());
                            state = ParseState.PS_DOT2; 
                        }
                    }else if (aToken instanceof THex) {
                        if(localMnemon == Mnemon.M_END) {
                            aCode = new Error("Only a comment can follow .END", currentline);
                            break;
                        }

                        THex localTHex = (THex) aToken;
                        
                        if(localTHex.getIntValue() < Const.TWOBYTEMIN || localTHex.getIntValue() > Const.TWOBYTEMAX){
                            aCode = new Error("Hex value outside of valid range. Hex must be between 0x0000(hex) 0(dec) and 0xFFFF(hex) 65535(dec).", currentline);
                        }else{
                            localOperandArg = new HexArg(localTHex.getIntValue());
                            state = ParseState.PS_DOT2; 
                        }
                    }else if (aToken instanceof TIdentifier){
                        if(localMnemon == Mnemon.M_BLOCK)
                        {
                            aCode = new Error(".BLOCK requires a decimal or hex constant argument", currentline);
                            break;
                        } else if(localMnemon == Mnemon.M_END) {
                            aCode = new Error("Only a comment can follow .END", currentline);
                            break;
                        }
                    } else if (aToken instanceof TStringLiteral){
                        if(localMnemon == Mnemon.M_BLOCK) {
                            aCode = new Error(".BLOCK requires a decimal or hex constant argument", currentline);
                            break;
                        } else if(localMnemon == Mnemon.M_END) {
                            aCode = new Error("Only a comment can follow .END", currentline);
                            break;
                        }
                    }else if (aToken instanceof TComment){
                        if (localMnemon == Mnemon.M_END)
                        {
                            aCode = new DotCommandInstr(localMnemon, null);
                            state = ParseState.PS_COMMENT;
                        }else{
                            aCode = new Error("Missing argument", currentline);
                        }
                    }else if (aToken instanceof TEmpty){
                        if (localMnemon == Mnemon.M_END)
                        {
                            aCode = new DotCommandInstr(localMnemon, null);
                            state = ParseState.PS_FINISH;    
                        }else{
                            aCode = new Error(String.format("Missing argument following %s command.", Maps.mnemonStringTable.get(localMnemon)), currentline);
                        }                   
                    } else {
                        aCode = new Error("Invalid argument following dot command. Must be interger or hexadecimal.", currentline);
                    }
                    break; 
                case PS_DOT2: 
                
                    if (aToken instanceof TComment){
                        aCode = new DotCommandInstr(localMnemon, localOperandArg);
                        state = ParseState.PS_COMMENT;
                    }else if (aToken instanceof TEmpty){
                        aCode = new DotCommandInstr(localMnemon, localOperandArg);
                        state = ParseState.PS_FINISH;
                    }else{ 
                        aCode = new Error("Invalid character/s following dot command.", currentline);
                    }
                    break;
                case PS_UNARY: 
                    if (aToken instanceof TComment){
                        aCode = new UnaryInstr(localMnemon);
                        state = ParseState.PS_COMMENT;
                    }else if (aToken instanceof TEmpty){
                        aCode = new UnaryInstr(localMnemon);
                        state = ParseState.PS_FINISH;
                    }else{
                        aCode = new Error("Invalid argument following unary instruction. Unary instructions take no arguments/s.", currentline);
                    }
                    break;
                case PS_COMMENT: 
                    if (aToken instanceof TEmpty)
                    {
                        state = ParseState.PS_FINISH;
                    }else{
                        aCode = new Error("Token found after a comment", currentline);
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
            System.out.println(outputObjectCode());
           
            System.out.println("----------------------------------------------");
            System.out.println("Program listing:");
            System.out.println("----------------------------------------------");
            System.out.println("Addr \t Symbol \t Mnemon \t Operand \t Comment");
            System.out.println(outputProgramListing());

            System.out.println("----------------");
            System.out.println("Symbol Table:");
            System.out.println("----------------");
            System.out.println("Symbol \t Value");
            System.out.println(outputSymbolTable());            
        }else{ 
            System.out.println(outputProgramErrorMessages());
        }

    }
    
    public String outputObjectCode()
    {   
        StringBuffer buf = new StringBuffer();
    
        //  object code output  
        for (int i = 0; i < codeTable.size(); i++){
            if (codeTable.get(i) instanceof EmptyInstr) {
                buf.append("");
            }else{
                buf.append(String.format("%s\n",  codeTable.get(i).generateCode()));
            }
        }

        return buf.toString();
    }

    public String outputProgramListing()
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

            if (codeTable.get(i) instanceof EmptyInstr){
                buf.append("");
            }else{
                buf.append(String.format("%s \t %s \t\t %s\n", 
                    Util.formatWord(localMemAddressCount), symbolListing, codeTable.get(i).generateListing()));
                localMemAddressCount += codeTable.get(i).getByteSize();
            }
        }

        return buf.toString();
    }

    public String outputSymbolTable()
    {
        StringBuffer buf = new StringBuffer(); 

        List<String> keys = symbolTable.keySet().stream().collect(Collectors.toList());

        for(int i = 0; i < keys.size(); ++i){
            buf.append(String.format("%s \t  %s\n", keys.get(i), Util.formatWord(symbolTable.get(keys.get(i)))));
        }
        return buf.toString();
    }

    public String outputProgramErrorMessages()
    {
        StringBuffer buf = new StringBuffer();

        int numErrors = 0;
        for(int i = 0; i < codeTable.size(); i++){
            if (codeTable.get(i) instanceof Error){
                buf.append(String.format("%s", codeTable.get(i).generateListing()));
                ++numErrors;
            }  
        }

        if (numErrors == 1){
            buf.append("1 error detected. \n");
        }else {
            buf.append(String.format("%d errors detected. \n", numErrors));
        }
        
        return buf.toString();
    }
}
