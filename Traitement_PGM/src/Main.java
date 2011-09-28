
import java.io.*;
import java.util.HashMap;

/**
 * @author Antoine
 * 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Traitement_PGM Lena = new Traitement_PGM();
		
		String cheminLena = new String("/Users/Antoine/Documents/ECN/Cours/EI3/OBJET/lena512x512.pgm");
		HashMap<Integer, Integer> histogrammeLena = new HashMap<Integer, Integer>();
		histogrammeLena = Lena.lecturePourHistogramme(cheminLena);
		String cheminHistogrammeLena = new String("/Users/Antoine/Documents/ECN/Cours/EI3/OBJET/lena512x512.pgm");
		Lena.creationHistogramme(histogrammeLena, cheminHistogrammeLena);

	}

}
