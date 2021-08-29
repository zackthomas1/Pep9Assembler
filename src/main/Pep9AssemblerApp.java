package main;

import java.net.URL;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Pep9AssemblerApp extends Application{

  public static void main(String[] args) throws Exception 
  {
  System.out.println("App::main-> Start App");
  launch(args);     
  }


  @Override
	public void start(Stage stage) throws Exception
	{
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("resources/pep9Assembler_gui_v001.fxml"));
    Parent content = loader.load();
      
    Scene scene = new Scene(content);

    stage.setTitle("Pep9 Assembler");
    stage.setScene(scene);
    stage.show();

  }
}
