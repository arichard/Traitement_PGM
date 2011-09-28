import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Traitement_PGM {

	// Création d'un histogramme à partir d'un fichier PGM
	public HashMap<Integer, Integer> lecturePourHistogramme(String cheminFichier) {

		String filePath = cheminFichier;

		HashMap<Integer, Integer> histogramme = new HashMap<Integer, Integer>(
				256);
		for (int l = 1; l <= 256; l++) {
			histogramme.put(l, 0);
		}

		try {
			// Création du flux bufférisé sur un FileReader, immédiatement suivi
			// par un
			// try/finally, ce qui permet de ne fermer le flux QUE s'il le
			// reader
			// est correctement instancié (évite les NullPointerException)
			BufferedReader buff = new BufferedReader(new FileReader(filePath));

			try {
				String line;
				// Lecture du fichier ligne par ligne. Cette boucle se termine
				// quand la méthode retourne la valeur null.

				// lecture des 4 premières lignes pour les "éviter'
				for (int i = 1; i <= 4; i++) {
					line = buff.readLine();
				}

				// traitement des pixels
				int temp;
				while ((line = buff.readLine()) != null) {
					temp = histogramme.get(Integer.parseInt(line)) + 1;
					histogramme.put(Integer.parseInt(line), temp);
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

		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(cheminDesire));

			int maxValue = 0;
			for (int l = 1; l <=histogramme.size(); l++) {
				if (histogramme.get(l) > maxValue) {
					maxValue = histogramme.get(l);
				}
			}
			out.write("P2");
			out.newLine();
			out.write("# CREATOR: Antoine");
			out.newLine();
			out.write(maxValue + " 256");
			out.newLine();
			out.write("255");
			out.newLine();

			for (int i = 1; i <= histogramme.size(); i++) {
				for (int j = 1; j <= histogramme.get(i); j++) {
					out.write("255");
					out.newLine();
				}
				for (int k = 1; k <= maxValue; k++) {
					out.write("0");
					out.newLine();
				}
			}

			out.close();

		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
