package main.controller;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import main.model.utility.InBuffer;
import main.model.parser.Translator;
import main.model.Generator;

public class Controller {

    public Button generateCodeBtn; 
   
    public TextArea assemblyInstTextArea;
    public TextArea objectCodeTextArea;
    public TextArea programListingTextArea;
    public TextArea symbolTableTextArea;
    public TextArea errorsTextArea;

    public void buttonClick()
    {
        System.out.println("Controller::buttonClick-> Generate machine code");
        
        // clear text fields
        objectCodeTextArea.clear();
        programListingTextArea.clear();
        symbolTableTextArea.clear();
        errorsTextArea.clear();

        InBuffer buffer = new InBuffer(assemblyInstTextArea.getText()); 

        Translator translator = new Translator(buffer); 
        Generator generator = new Generator(translator);

        if (translator.translate()){
            objectCodeTextArea.setText(generator.generateObjectCode());
            programListingTextArea.setText(generator.generateProgramListing());
            symbolTableTextArea.setText(generator.generateSymbolTable());    
        }else{
            errorsTextArea.setText(generator.generateErrorMessages());   
        }
    }
    
}
