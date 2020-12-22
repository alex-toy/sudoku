package services.computationlogic;

import static problemdomain.SudokuGame.GRID_BOUNDARY;

import problemdomain.Coordinates;


public class SudokuSolver {
	
	
	static int rows = 9; // nombre de lignes dans la grille
	static int columns = rows; // nombre de colonnes dans la grille
	static int cells = 9; // taille tableau de cellule
	
	static boolean finish = false; // d�termine si le sudoku est termin�
	static int posRows = 0; // position courant de la ligne
	static int posColumns = 0; // position courant de la colonne
	static int nbTour = 0;
	static int nbOccurence = 0; // d�termine le nb d'occurence dans ligne/colonne/carr�
	private int max_it;
	
	private int[][][] matrice; // repr�sentation de la grille de sudoku
	
	public int[][][] Get_matrice() {
		return matrice;
	}
	
	public SudokuSolver(int[][][] matrice) {
		super();
		this.matrice = matrice;
		this.max_it = 1000000;
	}

	public void display() {
		for (int j = 0 ; j < 9 ; j++) {
			for (int i = 0 ; i < 9 ; i++) {
					System.out.print(" | ");
					if (CompterCellule(j,i) == 1) {System.out.print(ValeurCellule(j,i));}
					else {System.out.print(" ");}
			}
			System.out.println();
		}
	}
	
	
	// permet de compter le nombre de cellule trouv�
		public int CompterCelluleTrouve() {
			int compteurFinal = 0;
			for (int r = 0 ; r < rows ; r++) {
				for (int c = 0 ; c < columns ; c++) {
					if (CompterCellule(r,c) == 1 ) {compteurFinal += 1;} 
				}
			}	
			return compteurFinal;
		}
		
		// permet de compter les valeurs <> 0 dans la cellule
		public int CompterCellule(int r , int c) {
			int countCell = 0;
			for (int i = 0 ; i < cells ; i++) {
				if (matrice[r][c][i] != 0) { countCell++;}
			}
			return countCell;
		}
		
		// permet de r�cuperer la valeur dans la cellule
			public int ValeurCellule(int r , int c) {
				int valueCell = 0;
				for (int i = 0 ; i < cells ; i++) {
					if (matrice[r][c][i] != 0) { valueCell = matrice[r][c][i];}
				}
				return valueCell;
			}
		
		// cr�e la grille de Sudoku initial (cellule remplie de 1 � 9)
		public void GenererMatrice() {
			for (int r = 0 ; r < rows ; r++ ) {
				for (int c = 0 ; c < columns ; c++) {
					if (CompterCellule(r,c) == 0) {matrice[r][c] = new int[]{1,2,3,4,5,6,7,8,9};}
				}
			}
		}
		
		public void ResoudreLigne() {
			for (int c = 0 ; c < columns ; c++ ) { // se d�place sur les colonnes de la ligne
				if (CompterCellule(posRows,c) != 1) { // la cellule est � r�soudre
					for (int c1 = 0 ; c1 < columns ; c1++) { // on boucle sur les colonnes pour v�rifier si il existes des valeurs uniques
						if (c1 != c) { // on ne prend pas en compte la cellule dans laquel nous sommes
							if (CompterCellule(posRows,c1) == 1) { // celulle � valeur d�finitive
								matrice[posRows][c][ValeurCellule(posRows,c1)-1] = 0;
							} 
						}
					}
				}
			}
		}
		
		public void ResoudreColonne() {
			for (int r = 0 ; r < rows ; r++ ) { // se d�place sur les lignes de la colonne
				if (CompterCellule(r,posColumns) != 1) { // la cellule est � r�soudre
					for (int r1 = 0 ; r1 < rows ; r1++) { // on boucle sur les colonnes pour v�rifier si il existes des valeurs uniques
						if (r1 != r) { // on ne prend pas en compte la cellule dans laquel nous sommes
							if (CompterCellule(r1,posColumns) == 1) { // celulle � valeur d�finitive
								matrice[r][posColumns][ValeurCellule(r1,posColumns)-1] = 0;
							} 
						}
					}
				}
			}
		}
		
		public void ResoudreCarre() {
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
		
		public void TrouveUniqueLigne () {
			for (int y = 1 ; y <= 9 ; y++) {
				nbOccurence = 0 ;
				for (int c = 0 ; c < columns ; c++ ) { // se déplace sur les colonnes de la ligne
					if (CompterCellule(posRows,c) != 1) { // la cellule est à résoudre
						if (matrice[posRows][c][y-1] == y) { 	
							nbOccurence +=1 ;
						}
					}
				}
				
				if (nbOccurence == 1) {
					for (int c = 0 ; c < columns ; c ++) {
						if (matrice[posRows][c][y-1] == y) { 
							matrice[posRows][c] = new int[]{0,0,0,0,0,0,0,0,0};
							matrice[posRows][c][y-1] = y;
						} 
					}
				}
			}
		}	
		
		public void TrouveUniqueColonne () {
			for (int y = 1 ; y <= 9 ; y++) {
				nbOccurence = 0 ;
				for (int r = 0 ; r < rows ; r++ ) { // se déplace sur les colonnes de la ligne
					if (CompterCellule(r,posColumns) != 1) { // la cellule est à résoudre
						if (matrice[r][posColumns][y-1] == y) {
							nbOccurence +=1 ;
						}
					}
				}
				if (nbOccurence == 1) {
					for (int r = 0 ; r < rows ; r ++) {
						if (matrice[r][posColumns][y-1] == y) {
							matrice[r][posColumns] = new int[]{0,0,0,0,0,0,0,0,0};
							matrice[r][posColumns][y-1] = y;
						} 
					}
				}
			}
		}

		// fonction non-fonctionelle pour le moment // erreur de déplacement et de solvabilité
		public void TrouveUniqueCarre () {
			for (int y = 1 ; y <= 9 ; y++) {
				nbOccurence = 0;
				for (int r = posRows ; r < posRows + 3 ; r++) {	
					for (int c = posColumns ; c < posColumns + 3 ; c++) {
						if (CompterCellule(r,c) != 1) {
							if (matrice[r][c][y-1] == y) {
								nbOccurence +=1 ;
							} 
						}
					}
				}
				
				if (nbOccurence == 1) {
					for (int r = posRows ; r < posRows + 3 ; r++) {	
						for (int c = posColumns ; c < posColumns + 3 ; c++ ) {
							if (matrice[r][c][y-1] == y) {
								matrice[r][c] = new int[]{0,0,0,0,0,0,0,0,0};
								matrice[r][c][y-1] = y;
							}
						}
					}
				}
			}
		}

		
		public boolean resolve() {
			
			GenererMatrice();
			
			while (!finish) {
				
				// r�solution de tous les carr�s par methode ResoudreCarre
				posRows = 0;
				for (int x = 0 ; x < 3 ; x++) {
					posColumns = 0;
					for (int y = 0 ; y < 3 ; y++) {
						ResoudreCarre();
						posColumns += 3;
					}
					posRows += 3;
				}
				
				// r�solution de toutes les lignes par methode ResoudreLigne
				posRows = 0;
				posColumns = 0;
				for (int x = 0 ; x < rows ; x++) {
					ResoudreLigne();
					posRows += 1;
				}

				// r�solution de toutes les colonnes par methode ResoudreColonne
				posRows = 0;
				posColumns = 0;
				for (int x = 0 ; x < columns ; x++) {
					ResoudreColonne();
					posColumns += 1;
				}
				
				// r�solution de toutes les lignes par methode TrouveUniqueLigne
				posRows = 0;
				posColumns = 0;
				for (int x = 0 ; x < rows ; x++) {
					TrouveUniqueLigne();
					posRows += 1;
				}
				
				// r�solution de toutes les colonnes par methode TrouveUniqueColonne
				posRows = 0;
				posColumns = 0;
				for (int x = 0 ; x < columns ; x++) {
					TrouveUniqueColonne();
					posColumns += 1;
				}
				/*
				posRows = 0;
				for (int x = 0 ; x < 3 ; x++) {
					posColumns = 0;
					for (int y = 0 ; y < 3 ; y++) {
						TrouveUniqueCarre();
						posColumns += 3;
					}
					posRows += 3;
				}*/
				
				nbTour += 1;
				if (CompterCelluleTrouve() == 81) {finish = true;}
				if (nbTour == max_it) {break;}
				
			}		
			return finish;
		}


		
    /**
     *
     * @param puzzle
     * @return
     */
		
    private static Coordinates[] typeWriterEnumerate(int[][] puzzle) {
        Coordinates[] emptyCells = new Coordinates[40];
        int iterator = 0;
        for (int y = 0; y < GRID_BOUNDARY; y++) {
            for (int x = 0; x < GRID_BOUNDARY; x++) {
                if (puzzle[x][y] == 0) {
                    emptyCells[iterator] = new Coordinates(x, y);
                    if (iterator == 39) return emptyCells;
                    iterator++;
                }
            }
        }
        return emptyCells;
    }
}

