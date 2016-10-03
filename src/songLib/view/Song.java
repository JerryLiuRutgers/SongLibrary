package songLib.view;

public class Song {
	
	private String name;
	private String artist;
	private String album;
	private String year;
	
	//Constructors
	public Song(String name, String artist) {
		this.name = name;
		this.artist = artist;
		album = "";
		year = "";
	}
	
	public Song(String name, String artist, String fieldThree, int x) {
		this.name = name;
		this.artist = artist;
		
		if (x == 0) {
			album = fieldThree;
			year = "";
		}
		else {
			year = fieldThree;
			album = "";
		}
	}
	
	public Song(String name, String artist, String album, String year) {
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.year = year;
	}
	
	//Getters
	public String getName() {
		return name;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public String getAlbum() {
		return album;
	}
	
	public String getYear() {
		return year;
	}
	
	//Setters
	public void setName(String name) {
		this.name = name;
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public void setAlbum(String album) {
		this.album = album;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	public String toString() {
		return name;
	}
}
