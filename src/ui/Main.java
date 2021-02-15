package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ListManager;

public class Main extends Application{
	private ContactManagerGUI contactManagerGUI;
	private ListManager listManager;
	
	public Main() {
		listManager = new ListManager();
		contactManagerGUI = new ContactManagerGUI(listManager);
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("welcome.fxml"));
		fxmlLoader.setController(contactManagerGUI);
		Parent root = fxmlLoader.load();		
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Contact Manager");
		primaryStage.show();
	}

}
