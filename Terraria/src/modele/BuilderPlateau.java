/*
 * BuilderPlateau.java
 * Cette classe sert à initialiser notre plateau. On prend un fichier csv qu'on lit et à chaque valeur lue, on le stocke dans un tableau d'entiers
 */

package modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class BuilderPlateau {

	private int notreMap[][];
	private int width;
	private int height;
	
	/*
	 * Cette méthode est utilisée pour lire notre fichier. Elle prend en paramètre un url.
	 * A l'aide d'un bufferedreader, on lit chaque ligne de notre fichier en supprimant la virgule.
	 * Pour chaque entier lu, on le stocke dans un tableau d'entiers.
	 */
	
	public void lireFichier(String url) {
		FileInputStream fis = null;
		String ligne = "";
		
		try {
			fis = new FileInputStream(new File(url));
		} catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable !");
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		
		try {
			ligne = br.readLine();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		this.width = 80;
		this.height = 80;
		
		int[][] plateau = new int[this.width][this.height];
		String[] sampleString = new String[this.width];
		
		try {
			int i = 0;
			
			if (url.contains(".csv")) {
				while (ligne  != null) {
					sampleString = ligne.split(",");
					plateau[i] = stringTableCaster(sampleString);
					i++;
					ligne = br.readLine(); 
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		finally {
			if (fis != null) {
				try {
					fis.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		

		this.notreMap = plateau;
	}

	/*
	 * Cette méthode renvoie un tableau d'entiers à une dimension d'une ligne d'un fichier
	 */
	
	public int[] stringTableCaster(String[] tab) {
		int[] res = new int[tab.length];
		
		for (int i = 0; i < tab.length; i++) {
			res[i] = Integer.parseInt(tab[i]);
		}
		return res;
	}
	
	public void affichePlateau(int[][] tab) {
		for (int y = 0; y < tab.length; y++) {
			System.out.print("{");
			
			for (int x = 0; x < tab[y].length; x++) {
				System.out.print(tab[y][x]);
				
				if (x < tab.length-1) {
					System.out.print(",");
				}
			}
			System.out.println("},");
		}
	}
	
	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}
	
	public int[][] getPlateau(){
		return this.notreMap;
	}
}