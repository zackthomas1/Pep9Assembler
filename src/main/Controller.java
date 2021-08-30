package main;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import main.parser.Translator;
import main.utility.InBuffer;

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
        Boolean translationValid = translator.translate();

        if (translationValid){
            objectCodeTextArea.setText(translator.generateProgramCode());
            programListingTextArea.setText(translator.generateProgramListing());
            symbolTableTextArea.setText(translator.generateSymbolTable());    
        }else{
            errorsTextArea.setText(translator.generateProgramErrorMessages());   
        }
    }
    
}
