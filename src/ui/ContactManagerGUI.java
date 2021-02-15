package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import model.ListManager;
import model.addContact;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ContactManagerGUI {

    @FXML
    private BorderPane mainPanel;
    
    @FXML
    private TextField txtName;

    @FXML
    private TextField txtEmail;

    @FXML
    private Label labMsg;
    
    @FXML
    private TableView<addContact> tvContactList;

    @FXML
    private TableColumn<addContact, String> tcName;

    @FXML
    private TableColumn<addContact, String> tcEmail;
    
    private ListManager listManager;
    
    public ContactManagerGUI(ListManager cm) {
    	listManager = cm;    	
	}
    
    public void initialize() {
    	
    }
    
    private void initializeTableView() {
    	ObservableList<addContact> observableList;
    	observableList = FXCollections.observableArrayList(listManager.getContacts());
    	
		tvContactList.setItems(observableList);
		tcName.setCellValueFactory(new PropertyValueFactory<addContact,String>("name"));
		tcEmail.setCellValueFactory(new PropertyValueFactory<addContact,String>("email"));
    }

    @FXML
    public void loadAddContact(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add-contact.fxml"));
    	fxmlLoader.setController(this);
    	Parent addContactPane = fxmlLoader.load();
    	
    	mainPanel.getChildren().clear();
    	mainPanel.setTop(addContactPane);
    }

    @FXML
    public void loadContactList(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("contact-list.fxml"));
    	fxmlLoader.setController(this);
    	Parent contactListPane = fxmlLoader.load();
    	
    	mainPanel.getChildren().clear();
    	mainPanel.setCenter(contactListPane);
    	initializeTableView();
    }
    
    @FXML
    public void addContact(ActionEvent event) {
    	listManager.addContact(txtName.getText(),txtEmail.getText());
    	
    	txtName.setText("");
    	txtEmail.setText("");
    	
    	labMsg.setText("The contact was added succesfully!");
    }
    
    @FXML
    public void exportContact(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Open Resource File");
    	File f = fileChooser.showOpenDialog(mainPanel.getScene().getWindow());
        if (f != null) {
        	Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Import Contacts");
            try {
				listManager.exportContacts(f.getAbsolutePath());
				alert.setContentText("The contacts was exported");

				alert.showAndWait();
			} catch (FileNotFoundException e) {
				alert.setContentText("The contacts was NOT exported");

				alert.showAndWait();
				e.printStackTrace();
			}
        }
    }

    @FXML
    public void importContact(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Open Resource File");
    	File f = fileChooser.showOpenDialog(mainPanel.getScene().getWindow());
    	if(f != null) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Import Contacts");
    		try {
				listManager.importContacts(f.getAbsolutePath());
				alert.setContentText("The contacts was imported");

				alert.showAndWait();
			} catch (IOException e) {
				alert.setContentText("The contacts was NOT imported");

				alert.showAndWait();
				e.printStackTrace();
			}
    	}
    }


}
