package Parser;

import java.util.ArrayList;

import LexAnalyzer.Tokenizer;
import LexAnalyzer.Tokens.AToken;
import LexAnalyzer.Tokens.TEmpty;
import LexAnalyzer.Tokens.THex;
import LexAnalyzer.Tokens.TIdentifier;
import LexAnalyzer.Tokens.TInteger;
import LexAnalyzer.Tokens.TInvalid;
import Parser.Args.AArg;
import Parser.Args.IdentArg;
import Parser.Args.IntArg;
import Parser.Code.ACode;
import Parser.Code.EmptyInstr;
import Parser.Code.Error;
import Parser.Code.TwoArgsInstr;
import Parser.Code.UnaryInstr;
import Utility.InBuffer;

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
        AArg localFirstArg = new IntArg(0); 
        AArg localSecondArg; 
        Mnemon localMnemon = Mnemon.M_END; // useless initialization
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
                            aCode = new UnaryInstr(localMnemon);
                            terminate = localMnemon == Mnemon.M_END;
                            state = ParseState.PS_UNARY;
                        } else if (Maps.nonunaryMnemonTable.containsKey(identStr.toLowerCase())){
                            localMnemon = Maps.unaryMnemonTable.get(identStr.toLowerCase());
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
                    if (aToken instanceof TInteger){
                        state
                    }else if (aToken instanceof THex){

                    }else if (aToken instanceof TIdentifier){

                    }
                    break; 
                case PS_1ST_OPEND: 
                    break; 
                case PS_COMMA: 
                    if (aToken instanceof TIdentifier){
                        TIdentifier localTIdentifier = (TIdentifier) aToken;
                        localSecondArg = new IdentArg(localTIdentifier.getStringValue()); 
                        aCode = new TwoArgsInstr(localMnemon, localFirstArg, localSecondArg); 
                        state = ParseState.PS_2ND_OPRND;
                    }else if (aToken instanceof TInteger){
                        TInteger localTInteger = (TInteger) aToken;
                        localSecondArg = new IntArg(localTInteger.getIntValue());
                        aCode = new TwoArgsInstr(localMnemon, localFirstArg, localSecondArg);
                        state = ParseState.PS_2ND_OPRND;
                    }else{
                        aCode = new Error("Second argument not an identifier or integer.");
                    }
                    break;
                case PS_2ND_OPRND: 
                    break; 
                case PS_UNARY: 
                    break;
                case PS_NON_UNARY1: 
                    break;
                case PS_NON_UNARY2:
                    if (aToken instanceof TEmpty){
                        state = ParseState.PS_FINISH; 
                    }else{
                        aCode = new Error("Illegal trailing character.");
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
            System.out.println("Object code: \n");
            for (int i = 0; i < codeTable.size(); i++){
                System.out.printf("%s", codeTable.get(i).generateCode());
            }
        
            //  program listing output
            System.out.println("\nProgram listing:\n");
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
