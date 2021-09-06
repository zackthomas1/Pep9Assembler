package main;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Pep9AssemblerApp extends Application{

  public static void main(String[] args) throws Exception 
  {
    System.out.println("Pep9AssemblerApp::main-> Launch");
    launch(args);  
  
  }


  @Override
	public void start(Stage stage) throws Exception
	{

    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("view/pep9Assembler_gui_v001.fxml"));
    Parent root = loader.load();

    loader.getController();
      
    Scene scene = new Scene(root);

    stage.setTitle("Pep9 Assembler");
    stage.setScene(scene);
    stage.show();

  }
}
