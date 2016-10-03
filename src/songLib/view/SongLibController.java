package songLib.view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class SongLibController {
	@FXML
	ListView<Song> listView;
	ArrayList<Song> list = new ArrayList<Song>();
	
	private ObservableList<Song> obsList;
	
	public void start(Stage mainStage) throws IOException {
		readList();
		obsList = FXCollections.observableList(list);
		listView.setItems(obsList);
	}
	
	// Each song should be stored in songs.txt in the form:
	// title;artist;album;year
	private void readList() throws IOException {
		String line;
		String name, artist, album, year;

		//songs.txt must be placed as a child of project root folder and a peer of /src
		FileReader fileReader = new FileReader("songs.txt");
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		// Adds a new Song to the ArrayList for each line of songs.txt
		while((line = bufferedReader.readLine()) != null) {	
			list.add(new Song(line.split(";")[0], line.split(";")[1], line.split(";")[2], line.split(";")[3]));
		}		
	}
}
