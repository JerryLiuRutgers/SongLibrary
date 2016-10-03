package songLib.view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class SongLibController {
	@FXML
	ListView<String> listView;
	ArrayList<Song> list;
	
	private ObservableList<String> obsList;
	
	public void start(Stage mainStage) {
		
	}
	
	// Each song should be stored in songs.txt in the form:
	// title|artist|album|year
	private void readList() throws IOException {
		String line;
		
		FileReader fileReader = new FileReader("/songLib/view/songs.txt");
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		while((line = bufferedReader.readLine()) != null) {
			
		}
	}
}
