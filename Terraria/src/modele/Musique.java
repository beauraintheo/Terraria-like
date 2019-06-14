package modele;

import java.io.File;

public class Musique {

	private int id;
	private String url;
	
	public Musique(int id, String url) {
		this.id = id;
		this.url = initialiserMusique(url);
	}
	
	public String initialiserMusique(String url) {
		url = new File(url).toURI().toString();
		return url;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getURL() {
		return this.url;
	}
	
	public String toString() {
		return "Id : " + this.id + "\nURL : " + this.url;
	}
}