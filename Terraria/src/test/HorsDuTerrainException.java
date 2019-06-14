package test;

import modele.Joueur;
import modele.Plateau;

public class HorsDuTerrainException extends Exception {
	
	private Joueur joueur;
	private Plateau plateau;
	
	public HorsDuTerrainException(Joueur joueur, Plateau plateau) {
		this.joueur = joueur;
		this.plateau = plateau;
	}
	
	public static boolean verifCoordJoueur(Joueur joueur, Plateau plateau) {
		if(joueur.getCoordXPosition()<0 || joueur.getCoordYPosition()<0) {
			return false;
		}
		if(plateau.getTaillePlateauX()*16<joueur.getCoordXPosition() || plateau.getTaillePlateauY()*16<joueur.getCoordYPosition()) {
			return false;
		}
		return true;
	}
	
	public String toString() {
		return "Vous avez spawn hors de la map aux coordonnees :" + joueur.getCoordXPosition() + "," + joueur.getCoordYPosition();
	}
	
}