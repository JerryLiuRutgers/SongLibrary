//Jerry Liu - jl1421 - 138006256
//Cynthia Liu - cl844 - 151001799

package songLib.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SongLibController {
	@FXML ListView<Song> listView;
	@FXML Text details;
	@FXML Button Add;
	@FXML Button Edit;
	@FXML Button Delete;
	@FXML TextField nameIn;
	@FXML TextField artistIn;
	@FXML TextField albumIn;
	@FXML TextField yearIn;
	
	ArrayList<Song> list = new ArrayList<Song>();
	
	private ObservableList<Song> obsList;
	
	public void start(Stage mainStage) throws IOException {
		//create save file
		File f;
		f = new File("songs.txt");
		f.createNewFile();
		
		readList();
		obsList = FXCollections.observableList(list);
		listView.setItems(obsList);
		
		//select first item
		listView.getSelectionModel().select(0);
		showItem(mainStage);
		
		//set listener for items
		listView
        .getSelectionModel()
        .selectedItemProperty()
        .addListener(
           (obs, oldVal, newVal) -> 
               showItem(mainStage));
	}
	
	private void showItem(Stage mainStage) {                
		try {   
			details.setText("Song: " + listView.getSelectionModel().getSelectedItem().getName() + "\n" +
				   			"Artist: " + listView.getSelectionModel().getSelectedItem().getArtist() + "\n" +
				   			"Album: " + listView.getSelectionModel().getSelectedItem().getAlbum() + "\n" +
				   			"Year: " + listView.getSelectionModel().getSelectedItem().getYear());
		}
		catch (NullPointerException e) {
			
		}
	}
	
	public void missingAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		
		alert.setTitle("Error");
		alert.setHeaderText("Must have name and artist");
		alert.showAndWait();
	}
	
	public void duplicateAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		
		alert.setTitle("Error");
		alert.setHeaderText("Song already in list");
		alert.showAndWait();
	}
	
	public void add (ActionEvent e) throws FileNotFoundException {		
		if (!nameIn.getText().equals("") && !artistIn.getText().equals("")) {		
			Song toAdd = new Song(nameIn.getText(), artistIn.getText(), albumIn.getText(), yearIn.getText());
			
			if (checkDuplicate(toAdd)) {
				duplicateAlert();
			}
			else {
				list.add(toAdd);
				Collections.sort(list);
			
				//clear fields
				nameIn.setText("");
				artistIn.setText("");
				albumIn.setText("");
				yearIn.setText("");
			
				//update listView
				obsList = FXCollections.observableList(list);
				listView.setItems(obsList);
				
				//select new item
				listView.getSelectionModel().select(list.indexOf(toAdd));
				
				//save to file
				writeList();
			}
		}
		else {
			missingAlert();
		}
	}
	
	public void edit (ActionEvent e) throws FileNotFoundException {
		
		if (!nameIn.getText().equals("") && !artistIn.getText().equals("")) {
			Song toEdit = new Song(nameIn.getText(), artistIn.getText(),albumIn.getText(),yearIn.getText());
			
			if (checkDuplicate(toEdit)) {
				duplicateAlert();
			}
			else {
				obsList.set(listView.getSelectionModel().getSelectedIndex(), toEdit);
				Collections.sort(list);	
			
				//clear fields
				nameIn.setText("");
				artistIn.setText("");
				albumIn.setText("");
				yearIn.setText("");
				
				//save to file
				writeList();
			}
		}
		else {
			missingAlert();
		}
	}
	
	public void delete (ActionEvent e) throws FileNotFoundException {
		int selectedIndex = listView.getSelectionModel().getSelectedIndex();
		list.remove(selectedIndex);
		
		if (selectedIndex >= list.size()) {
			listView.getSelectionModel().select(selectedIndex - 1);
		}
		else {
			listView.getSelectionModel().select(selectedIndex);
		}
		
		//update listView
		obsList = FXCollections.observableList(list);
		listView.setItems(obsList);
		
		//save to file
		writeList();
	}
	
	public boolean checkDuplicate(Song check) {
		for (Song checker : list) {
			if(check.isEqual(checker)) {
				return true;
			}
		}
		return false;
	}
	
	// Each song should be stored in songs.txt in the form:
	// title;artist;album;year
	private void readList() throws IOException {
		String line;

		//songs.txt must be placed as a child of project root folder and a peer of /src
		FileReader fileReader = new FileReader("songs.txt");
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		// Adds a new Song to the ArrayList for each line of songs.txt
		while((line = bufferedReader.readLine()) != null) {	
			list.add(new Song(line.split(";", -1)[0], line.split(";", -1)[1], line.split(";", -1)[2], line.split(";", -1)[3]));
			Collections.sort(list);
		}
		
		bufferedReader.close();
	}
	
	private void writeList() throws FileNotFoundException {
		PrintWriter clear = new PrintWriter("songs.txt");
		clear.close();
		
		PrintWriter pw = new PrintWriter("songs.txt");
		for (Song x : list) {
			pw.println(x.getName() + ";" + x.getArtist() + ";" + x.getAlbum() + ";" + x.getYear());
		}
		pw.close();
	}
}
