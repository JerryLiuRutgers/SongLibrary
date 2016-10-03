package songLib.view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class SongLibController {
	@FXML ListView<Song> listView;
	@FXML Text details;
	
	ArrayList<Song> list = new ArrayList<Song>();
	
	private ObservableList<Song> obsList;
	
	public void start(Stage mainStage) throws IOException {
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
		   details.setText("Song: " + listView.getSelectionModel().getSelectedItem().getName() + "\n" +
				   			"Artist: " + listView.getSelectionModel().getSelectedItem().getArtist() + "\n" +
				   			"Album: " + listView.getSelectionModel().getSelectedItem().getAlbum() + "\n" +
				   			"Year: " + listView.getSelectionModel().getSelectedItem().getYear());
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
			list.add(new Song(line.split(";")[0], line.split(";")[1], line.split(";")[2], line.split(";")[3]));
			Collections.sort(list);
		}
		
		bufferedReader.close();
	}
}
