package test;

import static org.junit.Assert.*;

import org.junit.Test;

import modele.GrandChefMH;

public class DeplacementTest {

	@Test
	public void testDeplacementGaucheNormal() throws HorsDuTerrainException {
		GrandChefMH jeu = new GrandChefMH(1,64,48);
		jeu.avertirDeplacementJoueur("Gauche");
		assertEquals(48, jeu.getCoordJoueurX());		
	}
	
	@Test
	public void testDeplacementGaucheObstacle() throws HorsDuTerrainException {		
		GrandChefMH jeu = new GrandChefMH(1,96,48);
		jeu.avertirDeplacementJoueur("Gauche");
		assertEquals(96, jeu.getCoordJoueurX());			
	}
	
	@Test
	public void testDeplacementGaucheVide() throws HorsDuTerrainException {
		GrandChefMH jeu = new GrandChefMH(1,80,32);
		jeu.avertirDeplacementJoueur("Gauche");
		jeu.unTour(4);
		assertEquals(64, jeu.getCoordJoueurX());	
		assertEquals(48, jeu.getCoordJoueurY());	
	}
	
	@Test
	public void testDeplacementDroiteNormal() throws HorsDuTerrainException {
		GrandChefMH jeu = new GrandChefMH(1,48,48);
		jeu.avertirDeplacementJoueur("Droite");
		assertEquals(64, jeu.getCoordJoueurX());
	}
	
	@Test
	public void testDeplacementDroiteObstacle() throws HorsDuTerrainException {
		GrandChefMH jeu = new GrandChefMH(1,64,48);
		jeu.avertirDeplacementJoueur("Droite");
		assertEquals(64, jeu.getCoordJoueurX());		
	}
	
	@Test
	public void testDeplacementDroiteVide() throws HorsDuTerrainException {
		GrandChefMH jeu = new GrandChefMH(1,80,32);
		jeu.avertirDeplacementJoueur("Droite");
		jeu.unTour(4);
		assertEquals(96, jeu.getCoordJoueurX());	
		assertEquals(48, jeu.getCoordJoueurY());	
	}
	
	@Test
	public void testDeplacementHautNormal() throws HorsDuTerrainException {
		GrandChefMH jeu = new GrandChefMH(1,80,32);
		jeu.avertirDeplacementJoueur("Haut");
		assertEquals(80, jeu.getCoordJoueurX());	
		assertEquals(16, jeu.getCoordJoueurY());
	}
	
	@Test
	public void testDeplacementHautNormalObstacle() throws HorsDuTerrainException {
		GrandChefMH jeu = new GrandChefMH(1,16,48);
		jeu.avertirDeplacementJoueur("Haut");
		assertEquals(16, jeu.getCoordJoueurX());	
		assertEquals(48, jeu.getCoordJoueurY());
	}
	
	@Test
	public void testDeplacementHautNormalGameloop() throws HorsDuTerrainException {
		GrandChefMH jeu = new GrandChefMH(1,80,32);
		jeu.avertirDeplacementJoueur("Haut");
		jeu.unTour(4);
		assertEquals(80, jeu.getCoordJoueurX());	
		assertEquals(32, jeu.getCoordJoueurY());
	}
	
	@Test
	public void testDeplacementHautNormalGameloopObstacle() throws HorsDuTerrainException {
		GrandChefMH jeu = new GrandChefMH(1,16,48);
		jeu.avertirDeplacementJoueur("Haut");
		jeu.unTour(4);
		assertEquals(16, jeu.getCoordJoueurX());	
		assertEquals(48, jeu.getCoordJoueurY());
	}
	
	@Test
	public void testDeplacementBasGameloop() throws HorsDuTerrainException {
		GrandChefMH jeu = new GrandChefMH(1,32,16);
		jeu.avertirDeplacementJoueur("Bas");
		jeu.unTour(4);
		assertEquals(32, jeu.getCoordJoueurX());	
		assertEquals(48, jeu.getCoordJoueurY());
	}
	
}