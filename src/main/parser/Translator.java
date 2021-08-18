package main.parser;

import java.util.ArrayList;

import main.utility.InBuffer;
import main.lexanalyzer.Tokenizer;
import main.lexanalyzer.tokens.AToken;
import main.lexanalyzer.tokens.TAddr;
import main.lexanalyzer.tokens.TEmpty;
import main.lexanalyzer.tokens.THex;
import main.lexanalyzer.tokens.TIdentifier;
import main.lexanalyzer.tokens.TInteger;
import main.lexanalyzer.tokens.TInvalid;
import main.parser.args.AArg;
import main.parser.args.IdentArg;
import main.parser.args.IntArg;
import main.parser.codes.ACode;
import main.parser.codes.EmptyInstr;
import main.parser.codes.Error;
import main.parser.codes.OneArgInstr;
import main.parser.codes.TwoArgsInstr;
import main.parser.codes.UnaryInstr;

public class Translator {
    
    private final InBuffer b; 
    private Tokenizer t; 
    private ACode aCode; 

    public Translator(InBuffer inBuffer)
    {
        b = inBuffer; 
    }

    // Sets aCode and returns boolean true if end statement is processed 
    private boolean parseLine()
    {
        boolean terminate = false; 
        Mnemon localMnemon = Mnemon.M_END; // useless initialization
        AArg localFirstArg = new IntArg(0); 
        AArg localSecondArg; 
        AToken aToken; 
        aCode = new EmptyInstr(); 
        ParseState state = ParseState.PS_START; 

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
                            aCode = new Error("Line must begin with function identifier");
                        }
                    }else if (aToken instanceof TEmpty){
                        aCode = new EmptyInstr();
                        state = ParseState.PS_FINISH;
                    } else if (aToken instanceof TInvalid){
                        TInvalid invalidToken = (TInvalid) aToken; 
                        aCode = new Error(invalidToken.getErrorMessage());
                    }
                    else { 
                        aCode = new Error("Line must begin with function identifier");
                    }
                    break;
                case PS_FUNCTION: 
                    // if (aToken instanceof TIdentifier){ // use when implementing symbols
                    //     TIdentifier localTIdentifier = (TIdentifier) aToken; 
                    //     localFirstArg = new IdentArg(localTIdentifier.getStringValue()); 
                    //     state = ParseState.PS_1ST_OPEND; 
                    // }
                    if (aToken instanceof TInteger){
                        TInteger localTInteger = (TInteger) aToken;
                        localFirstArg = new IntArg(localTInteger.getIntValue());
                        state = ParseState.PS_NON_UNARY1; 
                    }else if (aToken instanceof THex){
                        THex localTHex = (THex) aToken;
                        localFirstArg = new IntArg(localTHex.getHexIntValue());
                        state = ParseState.PS_NON_UNARY1; 
                    }
                    else if (aToken instanceof TInvalid){
                        TInvalid invalidToken = (TInvalid) aToken; 
                        aCode = new Error(invalidToken.getErrorMessage());
                    }
                    else{ 
                        aCode = new Error("Operand invalid. Must be integer, hexadecimal, or identifier.");
                    }
                    break; 
                case PS_NON_UNARY1:
                    if (aToken instanceof TAddr){
                        TAddr localTIdentifier = (TAddr) aToken;
                        localSecondArg = new IdentArg(localTIdentifier.getStringValue()); 
                        aCode = new TwoArgsInstr(localMnemon, localFirstArg, localSecondArg);
                        state = ParseState.PS_NON_UNARY2;
                    }else if (aToken instanceof TEmpty){
                        aCode = new OneArgInstr(localMnemon, localFirstArg);
                        state = ParseState.PS_FINISH;
                    }
                    else{ 
                        aCode = new Error("Invalid addressing mode following first arugment."); 
                    }
                    break;       
                case PS_NON_UNARY2: 
                    if (aToken instanceof TEmpty){
                        state = ParseState.PS_FINISH;
                    }else{
                        aCode = new Error("Invalid character/s folllowing addressing mode.");
                    }
                    break;
                case PS_UNARY: 
                    if (aToken instanceof TEmpty){
                        aCode = new UnaryInstr(localMnemon);
                        terminate = localMnemon == Mnemon.M_END;
                        state = ParseState.PS_FINISH;
                    }else{
                        aCode = new Error("Invalid argument following unary instruction. Unary instructions take no arguments/s.");
                    }
                    break;
                default:
                    break;
                
            }
        }while(state != ParseState.PS_FINISH && !(aCode instanceof Error));
        
        return terminate;
    }

    public void translate()
    {
        ArrayList<ACode> codeTable = new ArrayList<>(); 
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
            aCode = new Error("Missing \"end\" sentinel"); 
            codeTable.add(aCode); 
            numErrors++;
        }

        // final output
        if (numErrors == 0){      
            //  object code output  
            System.out.println("Object code:");
            for (int i = 0; i < codeTable.size(); i++){
                System.out.printf("%s", codeTable.get(i).generateCode());
            }
        
            //  program listing output
            System.out.println("\nProgram listing:");
            for(int i = 0; i < codeTable.size(); i++){
                System.out.printf("%s", codeTable.get(i).generateListing());
            }

        }else{ 
            //  error message output
            System.out.printf("%d errors were detected. \n", numErrors); 
            for(int i = 0; i < codeTable.size(); i++){
                if (codeTable.get(i) instanceof Error)
                {
                    System.out.printf("%s", codeTable.get(i).generateListing());
                }            
            }
        }
    }

}
