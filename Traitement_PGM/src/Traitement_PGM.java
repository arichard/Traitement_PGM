import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Traitement_PGM {

	// Cr�ation d'un histogramme � partir d'un fichier PGM
	public HashMap<Integer, Integer> lecturePourHistogramme(String cheminFichier) {

		String filePath = cheminFichier;

		HashMap<Integer, Integer> histogramme = new HashMap<Integer, Integer>();
		

		try {
			// Cr�ation du flux buff�ris� sur un FileReader, imm�diatement suivi
			// par un
			// try/finally, ce qui permet de ne fermer le flux QUE s'il le
			// reader
			// est correctement instanci� (�vite les NullPointerException)
			BufferedReader buff = new BufferedReader(new FileReader(filePath));

			try {
				String line;
				// Lecture du fichier ligne par ligne. Cette boucle se termine
				// quand la m�thode retourne la valeur null.

				// lecture des 4 premi�res lignes pour les "�viter'
				for (int i = 1; i <= 4; i++) {
					line = buff.readLine();
				}

				// traitement des pixels
				while ((line = buff.readLine()) != null) {
					histogramme.put(Integer.parseInt(line),
							histogramme.get(Integer.parseInt(line) + 1));
				}

			} finally {
				// dans tous les cas, on ferme nos flux
				buff.close();
			}
		} catch (IOException ioe) {
			// erreur de fermeture des flux
			System.out.println("Erreur --" + ioe.toString());
		}

		return histogramme;

	}

	// creation du fichier image PGM contenant l'histogramme
	public void creationHistogramme(HashMap<Integer, Integer> histogramme,
			String cheminDesire) {

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(cheminDesire));

			
			int maxValue=0;
			for (int j = 0; j<histogramme.size(); j++)
			{
				if(histogramme.get(j)>maxValue)
				{
					maxValue = histogramme.get(j);
				}
			}
			out.write("P2");
			out.newLine();
			out.write("# CREATOR: Antoine");
			out.newLine();
			out.write(maxValue+" 256");
			out.newLine();
			out.write("255");
			out.newLine();

			for (int i = 1; i <= histogramme.size(); i++) {
				for (int j = 1; j <= histogramme.get(i); j++) {
					out.write("255");
					out.newLine()
				}
				for (int k = 1; k <= maxValue; k++) {
					out.write("0");
					out.newLine();
				}
			}
			
			out.close();

		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
