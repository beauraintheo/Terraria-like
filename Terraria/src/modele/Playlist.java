package modele;

import java.util.ArrayList;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Playlist {

	private ArrayList<Musique> playlist;
	
	public Playlist() {		
		playlist = new ArrayList<Musique>();
		playlist.add(new Musique(1, "Ressources/Musique/casserBloc.wav"));
		playlist.add(new Musique(2, "Ressources/Musique/selectionItem.wav"));
		playlist.add(new Musique(3, "Ressources/Musique/taper.wav"));
		playlist.add(new Musique(4, "Ressources/Musique/musiqueExterieure.mp3"));
	}

	public void ajouterMusique(Musique m) {
		if (!musiquePresente(m.getId()))
			this.playlist.add(m);
	}

	public void retirerMusique(Musique m) {
		if (musiquePresente(m.getId()))
			this.playlist.remove(m);
	}

	public void jouerMusique(int id) {
		for (int i = 0; i < playlist.size(); i++) {
			if (playlist.get(i).getId() == id) {
				String url = playlist.get(i).getURL();
				MediaPlayer mp = new MediaPlayer(new Media(url));
				mp.play();
			}
		}
	}
	
	public void arreterMusique(int id) {
		for (int i = 0; i < playlist.size(); i++) {
			if (playlist.get(i).getId() == id) {
				String url = playlist.get(i).getURL();
				MediaPlayer mp = new MediaPlayer(new Media(url));
				mp.stop();
			}
		}
	}

	public boolean musiquePresente(int id) {
		for (int i = 0; i < playlist.size(); i++) {
			if (playlist.get(i).getId() == id)
				return true;
		}
		return false;
	}

	public void afficherPlaylist() {
		for (int i = 0; i < playlist.size(); i++) {
			System.out.println(playlist.get(i));
		}
	}
}