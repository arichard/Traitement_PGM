import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Traitement_PGM {

	protected Traitement_PGM() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Création d'un histogramme à partir d'un fichier PGM
	public HashMap<Integer, Integer> lecturePourHistogramme(String cheminFichier) {

		String filePath = cheminFichier;

		HashMap<Integer, Integer> histogramme = new HashMap<Integer, Integer>();

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

	// création du fichier image PGM contenant l'histogramme
	public void creationHistogramme(HashMap<Integer, Integer> histogramme) {

	}

}
