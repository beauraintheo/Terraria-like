package modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class BuilderPlateau {

	private static int notreMap[][];
	private static int width;
	private static int height;

	public void remplirPlateau() {

	}




	public static void lireFichier(String url) {
		FileInputStream fis = null;
		String ligne = "";
		try {
			fis = new FileInputStream(new File(url));
		}
		catch(FileNotFoundException e) {
			System.out.println("Fichier introuvable !");
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		try {
			ligne = br.readLine();
		}
		catch(IOException e) {
			e.printStackTrace();
		}

		try {
			ligne=br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		width=80;
		height=80;
		int[][] plateau = new int[width][height];
		String[] sampleString = new String[width];
		try {
			int i = 0;
			if(url.contains(".csv")) {
				while (ligne  != null) {
					sampleString = ligne.split(",");
					plateau[i] = stringTableCaster(sampleString);
					i++;
					ligne= br.readLine(); 
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			if(fis != null) {
				try {
					fis.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		int[][] returntableau = new int[width][height];
		for (int i = 0; i < returntableau.length-1; i++) {
			for (int j = 0; j < returntableau[i].length; j++) {
				System.out.print(plateau[i][j]);
				returntableau[i][j] = plateau[j][i];
			}
		System.out.println();
		}
		notreMap =  returntableau;
	}


	public static int[] stringTableCaster(String[] tab) {
		int[] res = new int[tab.length];
		for(int i = 0; i < tab.length; i++) {
			res[i] = Integer.parseInt(tab[i]);
		}
		return res;
	}

	public void affichePlateau(int[][] tab) {
		for(int i = 0; i < tab.length; i++) {
			System.out.print("{");
			for(int j = 0; j < tab[i].length; j++) {
				System.out.print(tab[i][j]);
				if(j < tab.length-1) {
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
}
	
