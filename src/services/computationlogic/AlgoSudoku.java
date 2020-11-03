package services.computationlogic;


public class AlgoSudoku {
	
	static int rows = 9; // nombre de lignes dans la grille
	static int columns = rows; // nombre de colonnes dans la grille
	static int cells = 9; // taille tableau de cellule
	
	static boolean finish = false; // détermine si le sudoku est terminé
	static int posRows = 0; // position courant de la ligne
	static int posColumns = 0; // position courant de la colonne
	static int nbTour = 0;
	static int nbOccurence = 0; // détermine le nb d'occurence dans ligne/colonne/carré
	
	static int[][][] matrice; // représentation de la grille de sudoku

	// permet de compter le nombre de cellule trouvé
	public static int CompterCelluleTrouve() {
		int compteurFinal = 0;
		for (int r = 0 ; r < rows ; r++) {
			for (int c = 0 ; c < columns ; c++) {
				if (CompterCellule(r,c) == 1 ) {compteurFinal += 1;} 
			}
		}	
		return compteurFinal;
	}
	
	// permet de compter les valeurs <> 0 dans la cellule
	public static int CompterCellule(int r , int c) {
		int countCell = 0;
		for (int i = 0 ; i < cells ; i++) {
			if (matrice[r][c][i] != 0) { countCell++;}
		}
		return countCell;
	}
	
	// permet de récuperer la valeur dans la cellule
		public static int ValeurCellule(int r , int c) {
			int valueCell = 0;
			for (int i = 0 ; i < cells ; i++) {
				if (matrice[r][c][i] != 0) { valueCell = matrice[r][c][i];}
			}
			return valueCell;
		}
	
	// crée la grille de Sudoku initial (cellule remplie de 1 à 9)
	public static void GenererMatrice() {
		for (int r = 0 ; r < rows ; r++ ) {
			for (int c = 0 ; c < columns ; c++) {
				if (CompterCellule(r,c) == 0) {matrice[r][c] = new int[]{1,2,3,4,5,6,7,8,9};}
			}
		}
	}
	
	public static void ResoudreLigne() {
		for (int c = 0 ; c < columns ; c++ ) { // se déplace sur les colonnes de la ligne
			if (CompterCellule(posRows,c) != 1) { // la cellule est à résoudre
				for (int c1 = 0 ; c1 < columns ; c1++) { // on boucle sur les colonnes pour vérifier si il existes des valeurs uniques
					if (c1 != c) { // on ne prend pas en compte la cellule dans laquel nous sommes
						if (CompterCellule(posRows,c1) == 1) { // celulle à valeur définitive
							matrice[posRows][c][ValeurCellule(posRows,c1)-1] = 0;
						} 
					}
				}
			}
		}
	}
	
	public static void ResoudreColonne() {
		for (int r = 0 ; r < rows ; r++ ) { // se déplace sur les lignes de la colonne
			if (CompterCellule(r,posColumns) != 1) { // la cellule est à résoudre
				for (int r1 = 0 ; r1 < rows ; r1++) { // on boucle sur les colonnes pour vérifier si il existes des valeurs uniques
					if (r1 != r) { // on ne prend pas en compte la cellule dans laquel nous sommes
						if (CompterCellule(r1,posColumns) == 1) { // celulle à valeur définitive
							matrice[r][posColumns][ValeurCellule(r1,posColumns)-1] = 0;
						} 
					}
				}
			}
		}
	}
	
	public static void ResoudreCarre() {
		for (int r = posRows ; r < posRows + 3 ; r++) {	
			for (int c = posColumns ; c < posColumns + 3 ; c++ ) {
				if (CompterCellule(r,c) != 1) {
					for (int c1 = posColumns ; c1 < posColumns + 3 ; c1++) {
						for (int r1 = posRows ; r1 < posRows + 3 ; r1++) {
							if (c1 != c || r1 != r) {
								if (CompterCellule(r1,c1) == 1) {
									matrice[r][c][ValeurCellule(r1,c1)-1] = 0;
								}
							}
						}
					}
				}
			}
		}
	}
	
	public static int[][][] resolve(int[][][] new_tab) {
		
		matrice = new_tab;
		GenererMatrice();
		
		while (!finish) {
			
			// résolution de tous les carrés par methode ResoudreCarre
			posRows = 0;
			for (int x = 0 ; x < 3 ; x++) {
				posColumns = 0;
				for (int y = 0 ; y < 3 ; y++) {
					ResoudreCarre();
					posColumns += 3;
				}
				posRows += 3;
			}
			
			// résolution de toutes les lignes par methode ResoudreLigne
			posRows = 0;
			posColumns = 0;
			for (int x = 0 ; x < rows ; x++) {
				ResoudreLigne();
				posRows += 1;
			}

			// résolution de toutes les colonnes par methode ResoudreColonne
			posRows = 0;
			posColumns = 0;
			for (int x = 0 ; x < columns ; x++) {
				ResoudreColonne();
				posColumns += 1;
			}
			
			nbTour += 1;
			if (CompterCelluleTrouve() == 81) {finish = true;}
			if (nbTour == 20) {break;}
			
		}		
		return new_tab;
	}
}