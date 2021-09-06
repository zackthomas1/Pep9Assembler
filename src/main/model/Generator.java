package main.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import main.model.parser.Maps;
import main.model.parser.Translator;
import main.model.parser.codes.ACode;
import main.model.parser.codes.EmptyInstr;
import main.model.parser.codes.Error;
import main.model.utility.Util;

public class Generator {
    
    private Translator translator;

    public Generator (Translator t)
    {
        translator = t; 
    }
  
    public void systemOutCompiledProgram(Boolean translationValid)
    {
        // final output
        if (translationValid){      
            System.out.println("----------------");
            System.out.println("Object code:");;
            System.out.println("----------------");
            System.out.println(generateObjectCode());
           
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
            System.out.println(generateErrorMessages());
        }
    }

    public String generateObjectCode()
    {   
        StringBuffer buf = new StringBuffer();
        ArrayList<ACode> codeTable = translator.getCodeTable();
    
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

    public String generateProgramListing()
    {
        StringBuffer buf = new StringBuffer();
        ArrayList<ACode> codeTable = translator.getCodeTable();
        int localMemAddressCount = 0;

        for(int i = 0; i < codeTable.size(); i++){

            String symbolListing;
            if(Maps.symbolAddressTable.containsKey(localMemAddressCount)){
                symbolListing = Maps.symbolAddressTable.get(localMemAddressCount);
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

    public String generateSymbolTable()
    {
        StringBuffer buf = new StringBuffer(); 
        List<String> keys = Maps.symbolTable.keySet().stream().collect(Collectors.toList());

        for(int i = 0; i < keys.size(); ++i){
            buf.append(String.format("%s \t  %s\n", keys.get(i), Util.formatWord(Maps.symbolTable.get(keys.get(i)))));
        }
        return buf.toString();
    }

    public String generateErrorMessages()
    {
        StringBuffer buf = new StringBuffer();
        ArrayList<ACode> codeTable = translator.getCodeTable();

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
