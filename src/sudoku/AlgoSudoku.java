package sudoku;

public final class AlgoSudoku {
	
	static int rows = 9;
	static int columns = rows;
	static int[][][] matrice;

	public static void CreationMatrice() {
		matrice = new int[rows][columns][9];
		for (int r = 0 ; r < rows ; r++ ) {
			for (int c = 0 ; c < columns ; c++) {
				matrice[r][c] = new int[]{1,2,3,4,5,6,7,8,9};
			}
		}
	}
	
	public static void main(final String[] args) {
		TestAffichage();
	}
	
	public static void TestAffichage() {
		CreationMatrice();
		matrice[2][0] = new int[] {4};
		for (int r = 0 ; r < rows ; r++ ) {
			if (r > 0) {System.out.println();}
			for (int c = 0 ; c < columns ; c++) {
				if (c > 0) {System.out.print(" | ");}
				System.out.print(matrice[r][c].length);
			}
		}
	}
}