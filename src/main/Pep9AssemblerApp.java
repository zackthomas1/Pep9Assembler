package main;

import java.net.URL;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;


public class Pep9AssemblerApp extends Application{

    @Override
	public void start(Stage stage) throws Exception
	{
    FXMLLoader loader = new FXMLLoader();
    // loader.setLocation(new URL("file:///C:/Dev/Java/Pep9Assembler/src/main/resources/pep9Assembler_gui_v001.fxml"));
    loader.setLocation(getClass().getResource("resources/pep9Assembler_gui_v001.fxml"));
    Parent content = loader.load();
      
    // VBox vbox = loader.<VBox>load();
    Scene scene = new Scene(content);

    stage.setTitle("Pep9 Assembler");
    stage.setScene(scene);
    stage.show();

  }

    public static void main(String[] args) throws Exception 
    {
		System.out.println("App::main-> Start App");
		launch(args);     
    }

}
