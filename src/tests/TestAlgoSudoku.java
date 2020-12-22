package tests;

import services.computationlogic.AlgoSudoku;

public final class TestAlgoSudoku {
	
	static int[][][] board = new int[9][9][9];
	
	//PAge 6 Grille 5
	public static void GrilleUnFacile() {
		board[0][0] = new int[] {0,0,0,0,0,0,0,8,0};
		board[0][1] = new int[] {0,2,0,0,0,0,0,0,0};
		board[0][3] = new int[] {0,0,0,0,5,0,0,0,0};
		board[0][5] = new int[] {0,0,0,0,0,0,0,0,9};
		board[0][8] = new int[] {0,0,0,0,0,6,0,0,0};
		board[1][2] = new int[] {0,0,3,0,0,0,0,0,0};
		board[1][5] = new int[] {0,0,0,4,0,0,0,0,0};
		board[1][6] = new int[] {0,2,0,0,0,0,0,0,0};
		board[2][1] = new int[] {0,0,0,0,5,0,0,0,0};
		board[2][4] = new int[] {1,0,0,0,0,0,0,0,0};
		board[2][6] = new int[] {0,0,0,0,0,0,0,0,9};
		board[3][0] = new int[] {0,0,0,4,0,0,0,0,0};
		board[3][3] = new int[] {0,0,0,0,0,0,7,0,0};
		board[3][7] = new int[] {0,0,0,0,0,0,0,0,9};
		board[4][1] = new int[] {0,0,0,0,0,6,0,0,0};
		board[4][5] = new int[] {0,0,3,0,0,0,0,0,0};
		board[4][7] = new int[] {0,0,0,0,0,0,7,0,0};
		board[5][1] = new int[] {0,0,3,0,0,0,0,0,0};
		board[5][2] = new int[] {0,0,0,0,0,0,0,0,9};
		board[5][5] = new int[] {0,0,0,0,0,6,0,0,0};
		board[5][7] = new int[] {1,0,0,0,0,0,0,0,0};
		board[6][2] = new int[] {0,0,0,0,0,0,7,0,0};
		board[6][7] = new int[] {0,0,3,0,0,0,0,0,0};
		board[6][8] = new int[] {0,0,0,0,5,0,0,0,0};
		board[7][0] = new int[] {1,0,0,0,0,0,0,0,0};
		board[7][1] = new int[] {0,0,0,0,0,0,0,0,9};
		board[7][3] = new int[] {0,2,0,0,0,0,0,0,0};
		board[7][4] = new int[] {0,0,3,0,0,0,0,0,0};
		board[7][6] = new int[] {0,0,0,0,0,0,0,8,0};
		board[8][0] = new int[] {0,0,3,0,0,0,0,0,0};
		board[8][7] = new int[] {0,2,0,0,0,0,0,0,0};
	}
	
	//page 6 grille 6
	public static void GrilleDeuxFacile() {
		board[0][0] = new int[] {0,0,0,0,0,0,0,0,9};
		board[0][2] = new int[] {0,2,0,0,0,0,0,0,0};
		board[0][3] = new int[] {0,0,0,0,0,0,7,0,0};
		board[0][6] = new int[] {0,0,0,0,0,6,0,0,0};
		board[0][7] = new int[] {0,0,3,0,0,0,0,0,0};
		board[1][1] = new int[] {0,0,0,4,0,0,0,0,0};
		board[1][3] = new int[] {0,0,3,0,0,0,0,0,0};
		board[1][5] = new int[] {1,0,0,0,0,0,0,0,0};
		board[1][7] = new int[] {0,2,0,0,0,0,0,0,0};
		board[1][8] = new int[] {0,0,0,0,0,0,7,0,0};
		board[2][0] = new int[] {0,0,0,0,0,0,7,0,0};
		board[2][5] = new int[] {0,0,0,0,0,0,0,0,9};
		board[2][7] = new int[] {0,0,0,4,0,0,0,0,0};
		board[3][0] = new int[] {0,0,3,0,0,0,0,0,0};
		board[3][2] = new int[] {0,0,0,0,0,0,7,0,0};
		board[3][4] = new int[] {0,0,0,4,0,0,0,0,0};
		board[3][5] = new int[] {0,0,0,0,0,0,0,8,0};
		board[3][7] = new int[] {1,0,0,0,0,0,0,0,0};
		board[3][8] = new int[] {0,0,0,0,5,0,0,0,0};
		board[4][0] = new int[] {1,0,0,0,0,0,0,0,0};
		board[4][3] = new int[] {0,2,0,0,0,0,0,0,0};
		board[4][6] = new int[] {0,0,3,0,0,0,0,0,0};
		board[5][0] = new int[] {0,2,0,0,0,0,0,0,0};
		board[5][3] = new int[] {0,0,0,0,5,0,0,0,0};
		board[5][8] = new int[] {0,0,0,0,0,6,0,0,0};
		board[6][2] = new int[] {0,0,0,0,5,0,0,0,0};
		board[6][5] = new int[] {0,0,0,0,0,0,7,0,0};
		board[6][6] = new int[] {0,0,0,4,0,0,0,0,0};
		board[7][6] = new int[] {0,0,0,0,0,0,7,0,0};
		board[7][8] = new int[] {0,2,0,0,0,0,0,0,0};
		board[8][0] = new int[] {0,0,0,0,0,0,0,8,0};
		board[8][2] = new int[] {0,0,3,0,0,0,0,0,0};
		board[8][3] = new int[] {0,0,0,4,0,0,0,0,0};
		board[8][4] = new int[] {0,0,0,0,0,6,0,0,0};
		board[8][5] = new int[] {0,2,0,0,0,0,0,0,0};
	}
	
	//page 6 grille 7
	public static void GrilleTroisFacile() {
		board[0][1] = new int[] {0,0,0,0,0,0,7,0,0};
		board[0][5] = new int[] {0,0,0,0,0,6,0,0,0};
		board[0][6] = new int[] {0,0,0,0,5,0,0,0,0};
		board[1][2] = new int[] {0,0,0,0,5,0,0,0,0};
		board[1][3] = new int[] {0,0,0,4,0,0,0,0,0};
		board[1][4] = new int[] {0,0,0,0,0,0,7,0,0};
		board[1][8] = new int[] {0,2,0,0,0,0,0,0,0};
		board[2][1] = new int[] {1,0,0,0,0,0,0,0,0};
		board[2][2] = new int[] {0,0,0,4,0,0,0,0,0};
		board[2][4] = new int[] {0,2,0,0,0,0,0,0,0};
		board[2][5] = new int[] {0,0,0,0,5,0,0,0,0};
		board[2][6] = new int[] {0,0,0,0,0,0,0,8,0};
		board[2][7] = new int[] {0,0,0,0,0,0,7,0,0};
		board[3][1] = new int[] {0,0,3,0,0,0,0,0,0};
		board[3][2] = new int[] {0,0,0,0,0,0,0,8,0};
		board[3][5] = new int[] {0,2,0,0,0,0,0,0,0};
		board[3][6] = new int[] {0,0,0,0,0,0,0,0,9};
		board[3][8] = new int[] {0,0,0,0,5,0,0,0,0};
		board[4][4] = new int[] {0,0,0,0,5,0,0,0,0};
		board[4][6] = new int[] {1,0,0,0,0,0,0,0,0};
		board[4][7] = new int[] {0,2,0,0,0,0,0,0,0};
		board[5][0] = new int[] {0,2,0,0,0,0,0,0,0};
		board[5][1] = new int[] {0,0,0,0,5,0,0,0,0};
		board[5][4] = new int[] {0,0,0,0,0,0,0,0,9};
		board[5][5] = new int[] {0,0,0,0,0,0,0,8,0};
		board[6][0] = new int[] {0,0,0,0,5,0,0,0,0};
		board[6][8] = new int[] {0,0,0,4,0,0,0,0,0};
		board[7][1] = new int[] {0,2,0,0,0,0,0,0,0};
		board[7][2] = new int[] {0,0,3,0,0,0,0,0,0};
		board[7][4] = new int[] {0,0,0,0,0,6,0,0,0};
		board[7][5] = new int[] {0,0,0,4,0,0,0,0,0};
		board[7][8] = new int[] {1,0,0,0,0,0,0,0,0};
		board[8][1] = new int[] {0,0,0,0,0,0,0,0,9};
		board[8][4] = new int[] {0,0,3,0,0,0,0,0,0};
		board[8][5] = new int[] {1,0,0,0,0,0,0,0,0};
	}

	
	//page 6 grille 8
	public static void GrilleQuatreFacile() {
		board[0][1] = new int[] {0,0,0,0,0,0,7,0,0};
		board[0][2] = new int[] {0,0,0,0,0,0,0,0,9};
		board[0][4] = new int[] {0,0,0,0,5,0,0,0,0};
		board[0][5] = new int[] {1,0,0,0,0,0,0,0,0};
		board[0][8] = new int[] {0,0,0,0,0,6,0,0,0};
		board[1][0] = new int[] {0,0,3,0,0,0,0,0,0};
		board[1][2] = new int[] {0,0,0,0,5,0,0,0,0};
		board[1][3] = new int[] {0,0,0,0,0,0,0,8,0};
		board[1][6] = new int[] {1,0,0,0,0,0,0,0,0};
		board[2][2] = new int[] {1,0,0,0,0,0,0,0,0};
		board[2][5] = new int[] {0,2,0,0,0,0,0,0,0};
		board[3][0] = new int[] {1,0,0,0,0,0,0,0,0};
		board[3][5] = new int[] {0,0,0,0,0,6,0,0,0};
		board[3][6] = new int[] {0,0,0,0,5,0,0,0,0};
		board[4][0] = new int[] {0,0,0,0,0,6,0,0,0};
		board[4][3] = new int[] {0,0,0,0,5,0,0,0,0};
		board[4][6] = new int[] {0,0,0,4,0,0,0,0,0};
		board[4][8] = new int[] {0,0,3,0,0,0,0,0,0};
		board[5][0] = new int[] {0,2,0,0,0,0,0,0,0};
		board[5][3] = new int[] {0,0,3,0,0,0,0,0,0};
		board[5][5] = new int[] {0,0,0,0,0,0,0,8,0};
		board[5][6] = new int[] {0,0,0,0,0,0,0,0,9};
		board[5][7] = new int[] {0,0,0,0,0,6,0,0,0};
		board[5][8] = new int[] {1,0,0,0,0,0,0,0,0};
		board[6][1] = new int[] {0,0,3,0,0,0,0,0,0};
		board[6][5] = new int[] {0,0,0,4,0,0,0,0,0};
		board[6][6] = new int[] {0,0,0,0,0,0,7,0,0};
		board[6][7] = new int[] {0,0,0,0,5,0,0,0,0};
		board[6][8] = new int[] {0,0,0,0,0,0,0,8,0};
		board[7][0] = new int[] {0,0,0,0,0,0,7,0,0};
		board[8][1] = new int[] {1,0,0,0,0,0,0,0,0};
		board[8][2] = new int[] {0,0,0,0,0,0,0,8,0};
		board[8][3] = new int[] {0,0,0,0,0,0,7,0,0};
		board[8][5] = new int[] {0,0,3,0,0,0,0,0,0};
	}

	
	// Page 40 grille 73
	public static void GrilleUnMoyen() {
		board[0][3] = new int[] {0,0,0,4,0,0,0,0,0};
		board[0][5] = new int[] {1,0,0,0,0,0,0,0,0};
		board[0][6] = new int[] {0,2,0,0,0,0,0,0,0};
		board[1][1] = new int[] {0,0,0,0,0,0,0,8,0};
		board[1][2] = new int[] {1,0,0,0,0,0,0,0,0};
		board[1][5] = new int[] {0,0,0,0,0,6,0,0,0};
		board[2][0] = new int[] {0,0,0,0,0,0,0,0,9};
		board[2][2] = new int[] {0,0,0,4,0,0,0,0,0};
		board[2][4] = new int[] {0,2,0,0,0,0,0,0,0};
		board[2][6] = new int[] {0,0,0,0,0,0,7,0,0};
		board[3][0] = new int[] {0,0,3,0,0,0,0,0,0};
		board[3][8] = new int[] {0,0,0,0,0,0,7,0,0};
		board[4][0] = new int[] {0,0,0,0,0,0,7,0,0};
		board[4][1] = new int[] {0,2,0,0,0,0,0,0,0};
		board[4][2] = new int[] {0,0,0,0,0,6,0,0,0};
		board[4][3] = new int[] {0,0,0,0,5,0,0,0,0};
		board[4][5] = new int[] {0,0,0,0,0,0,0,0,9};
		board[5][3] = new int[] {0,0,0,0,0,0,7,0,0};
		board[5][5] = new int[] {0,2,0,0,0,0,0,0,0};
		board[5][6] = new int[] {0,0,3,0,0,0,0,0,0};
		board[5][8] = new int[] {0,0,0,0,5,0,0,0,0};
		board[6][0] = new int[] {0,0,0,0,0,0,0,8,0};
		board[6][5] = new int[] {0,0,0,0,5,0,0,0,0};
		board[6][6] = new int[] {0,0,0,0,0,6,0,0,0};
		board[7][0] = new int[] {0,0,0,0,5,0,0,0,0};
		board[7][1] = new int[] {0,0,3,0,0,0,0,0,0};
		board[7][7] = new int[] {0,0,0,0,0,0,0,8,0};
		board[8][4] = new int[] {0,0,0,0,0,0,0,8,0};
		board[8][8] = new int[] {0,0,0,4,0,0,0,0,0};
	}

	// Page 40 grille 74
	public static void GrilleDeuxMoyen() {
		board[0][1] = new int[] {0,0,0,0,0,0,7,0,0};
		board[0][2] = new int[] {0,2,0,0,0,0,0,0,0};
		board[0][6] = new int[] {0,0,0,0,0,0,0,0,9};
		board[1][1] = new int[] {0,0,0,0,0,0,0,0,9};
		board[1][3] = new int[] {0,0,0,0,0,0,7,0,0};
		board[2][0] = new int[] {0,0,0,4,0,0,0,0,0};
		board[2][1] = new int[] {0,0,0,0,5,0,0,0,0};
		board[2][3] = new int[] {0,0,0,0,0,6,0,0,0};
		board[2][5] = new int[] {0,2,0,0,0,0,0,0,0};
		board[2][6] = new int[] {0,0,3,0,0,0,0,0,0};
		board[2][8] = new int[] {0,0,0,0,0,0,7,0,0};
		board[3][0] = new int[] {0,0,0,0,0,0,0,0,9};
		board[3][4] = new int[] {0,0,0,0,0,0,0,8,0};
		board[3][6] = new int[] {0,0,0,0,0,0,7,0,0};
		board[4][0] = new int[] {0,2,0,0,0,0,0,0,0};
		board[4][2] = new int[] {0,0,0,0,0,0,7,0,0};
		board[4][4] = new int[] {0,0,3,0,0,0,0,0,0};
		board[4][8] = new int[] {0,0,0,0,0,0,0,8,0};
		board[5][5] = new int[] {1,0,0,0,0,0,0,0,0};
		board[5][6] = new int[] {0,0,0,4,0,0,0,0,0};
		board[6][4] = new int[] {1,0,0,0,0,0,0,0,0};
		board[7][5] = new int[] {0,0,0,4,0,0,0,0,0};
		board[7][7] = new int[] {0,0,0,0,0,0,0,0,9};
		board[8][0] = new int[] {0,0,0,0,0,0,0,8,0};
		board[8][1] = new int[] {0,0,0,4,0,0,0,0,0};
		board[8][2] = new int[] {1,0,0,0,0,0,0,0,0};
		board[8][3] = new int[] {0,0,0,0,0,0,0,0,9};
		board[8][6] = new int[] {0,2,0,0,0,0,0,0,0};
		board[8][8] = new int[] {0,0,0,0,5,0,0,0,0};
	}
	
	// Page 40 grille 75
	public static void GrilleTroisMoyen() {
			board[0][0] = new int[] {0,0,3,0,0,0,0,0,0};
			board[0][4] = new int[] {0,0,0,0,0,0,7,0,0};
			board[0][5] = new int[] {0,0,0,0,0,0,0,0,9};
			board[0][7] = new int[] {0,0,0,4,0,0,0,0,0};
			board[1][2] = new int[] {0,0,0,0,0,0,7,0,0};
			board[1][3] = new int[] {0,0,3,0,0,0,0,0,0};
			board[1][5] = new int[] {0,0,0,0,0,0,0,8,0};
			board[1][6] = new int[] {0,0,0,0,0,0,0,0,9};
			board[2][1] = new int[] {0,0,0,0,0,0,0,0,9};
			board[2][4] = new int[] {0,0,0,4,0,0,0,0,0};
			board[2][5] = new int[] {1,0,0,0,0,0,0,0,0};
			board[2][8] = new int[] {0,0,0,0,0,0,0,8,0};
			board[3][1] = new int[] {1,0,0,0,0,0,0,0,0};
			board[3][2] = new int[] {0,0,0,0,0,0,0,8,0};
			board[3][7] = new int[] {0,0,0,0,5,0,0,0,0};
			board[4][4] = new int[] {0,0,0,0,5,0,0,0,0};
			board[4][7] = new int[] {0,2,0,0,0,0,0,0,0};
			board[5][2] = new int[] {0,0,0,4,0,0,0,0,0};
			board[5][3] = new int[] {0,0,0,0,0,0,0,8,0};
			board[5][6] = new int[] {0,0,3,0,0,0,0,0,0};
			board[6][3] = new int[] {0,0,0,0,0,0,0,0,9};
			board[6][4] = new int[] {0,0,0,0,0,6,0,0,0};
			board[6][7] = new int[] {0,0,0,0,0,0,7,0,0};
			board[6][8] = new int[] {0,0,0,4,0,0,0,0,0};
			board[7][3] = new int[] {0,0,0,4,0,0,0,0,0};
			board[7][4] = new int[] {0,0,3,0,0,0,0,0,0};
			board[8][0] = new int[] {0,0,0,0,0,0,0,0,9};
			board[8][2] = new int[] {0,0,0,0,5,0,0,0,0};
		}

	// Page 40 grille 76
	public static void GrilleQuatreMoyen() {
		board[0][1] = new int[] {0,2,0,0,0,0,0,0,0};
		board[0][7] = new int[] {0,0,3,0,0,0,0,0,0};
		board[1][2] = new int[] {0,0,0,0,0,0,0,0,9};
		board[1][5] = new int[] {0,0,0,0,0,0,7,0,0};
		board[1][6] = new int[] {0,0,0,0,0,0,0,8,0};
		board[1][7] = new int[] {1,0,0,0,0,0,0,0,0};
		board[2][2] = new int[] {0,0,0,0,0,0,7,0,0};
		board[2][3] = new int[] {0,0,0,4,0,0,0,0,0};
		board[2][4] = new int[] {1,0,0,0,0,0,0,0,0};
		board[2][5] = new int[] {0,0,0,0,0,0,0,8,0};
		board[2][6] = new int[] {0,0,0,0,5,0,0,0,0};
		board[3][2] = new int[] {0,0,3,0,0,0,0,0,0};
		board[3][3] = new int[] {0,0,0,0,0,6,0,0,0};
		board[3][5] = new int[] {0,0,0,4,0,0,0,0,0};
		board[3][6] = new int[] {0,0,0,0,0,0,0,0,9};
		board[4][0] = new int[] {0,0,0,0,0,0,0,8,0};
		board[4][2] = new int[] {0,0,0,4,0,0,0,0,0};
		board[4][6] = new int[] {0,0,0,0,0,6,0,0,0};
		board[4][8] = new int[] {0,2,0,0,0,0,0,0,0};
		board[5][4] = new int[] {0,0,0,0,5,0,0,0,0};
		board[5][6] = new int[] {0,0,0,4,0,0,0,0,0};
		board[6][0] = new int[] {0,0,0,0,0,0,0,0,9};
		board[6][6] = new int[] {0,2,0,0,0,0,0,0,0};
		board[7][1] = new int[] {0,0,0,0,0,0,7,0,0};
		board[7][2] = new int[] {0,2,0,0,0,0,0,0,0};
		board[7][5] = new int[] {0,0,0,0,0,6,0,0,0};
		board[8][2] = new int[] {0,0,0,0,0,6,0,0,0};
		board[8][4] = new int[] {0,0,0,0,0,0,0,0,9};
		board[8][5] = new int[] {0,2,0,0,0,0,0,0,0};
		board[8][8] = new int[] {0,0,0,0,0,0,7,0,0};
	}		
		
	// Page 78 grille 149
	public static void GrilleUnDifficile() {
		board[0][3] = new int[] {0,0,0,4,0,0,0,0,0};
		board[1][1] = new int[] {0,0,0,0,0,6,0,0,0};
		board[1][2] = new int[] {0,0,0,4,0,0,0,0,0};
		board[1][4] = new int[] {0,0,0,0,0,0,0,0,9};
		board[1][5] = new int[] {0,2,0,0,0,0,0,0,0};
		board[1][6] = new int[] {1,0,0,0,0,0,0,0,0};
		board[2][2] = new int[] {0,0,0,0,0,0,0,0,9};
		board[2][8] = new int[] {0,0,0,0,5,0,0,0,0};
		board[3][4] = new int[] {1,0,0,0,0,0,0,0,0};
		board[3][8] = new int[] {0,0,3,0,0,0,0,0,0};
		board[4][1] = new int[] {0,0,0,0,0,0,7,0,0};
		board[4][2] = new int[] {0,2,0,0,0,0,0,0,0};
		board[4][5] = new int[] {0,0,0,0,5,0,0,0,0};
		board[4][6] = new int[] {0,0,0,0,0,0,0,0,9};
		board[4][7] = new int[] {1,0,0,0,0,0,0,0,0};
		board[5][0] = new int[] {0,0,0,0,5,0,0,0,0};
		board[5][1] = new int[] {1,0,0,0,0,0,0,0,0};
		board[5][3] = new int[] {0,2,0,0,0,0,0,0,0};
		board[5][8] = new int[] {0,0,0,0,0,6,0,0,0};
		board[6][0] = new int[] {1,0,0,0,0,0,0,0,0};
		board[6][3] = new int[] {0,0,3,0,0,0,0,0,0};
		board[6][4] = new int[] {0,2,0,0,0,0,0,0,0};
		board[7][6] = new int[] {0,2,0,0,0,0,0,0,0};
		board[7][7] = new int[] {0,0,0,0,0,6,0,0,0};
		board[8][5] = new int[] {0,0,0,0,0,6,0,0,0};
		board[8][8] = new int[] {0,0,0,4,0,0,0,0,0};
	}
		
	// Page 79 grille 151
	public static void GrilleDeuxDifficile() {
		board[0][0] = new int[] {1,0,0,0,0,0,0,0,0};
		board[0][1] = new int[] {0,0,0,0,0,6,0,0,0};
		board[0][7] = new int[] {0,2,0,0,0,0,0,0,0};
		board[1][0] = new int[] {0,0,0,0,0,0,7,0,0};
		board[1][3] = new int[] {0,0,0,0,0,6,0,0,0};
		board[1][5] = new int[] {0,0,0,0,0,0,0,8,0};
		board[2][4] = new int[] {0,0,0,4,0,0,0,0,0};
		board[3][2] = new int[] {0,0,0,0,0,6,0,0,0};
		board[3][4] = new int[] {0,0,0,0,0,0,7,0,0};
		board[4][0] = new int[] {0,0,0,0,0,0,0,8,0};
		board[4][2] = new int[] {0,2,0,0,0,0,0,0,0};
		board[4][5] = new int[] {0,0,3,0,0,0,0,0,0};
		board[4][6] = new int[] {0,0,0,4,0,0,0,0,0};
		board[4][7] = new int[] {0,0,0,0,0,0,7,0,0};
		board[5][6] = new int[] {0,0,3,0,0,0,0,0,0};
		board[5][8] = new int[] {0,0,0,0,0,0,0,0,9};
		board[6][2] = new int[] {0,0,3,0,0,0,0,0,0};
		board[6][4] = new int[] {1,0,0,0,0,0,0,0,0};
		board[6][5] = new int[] {0,0,0,0,0,0,7,0,0};
		board[6][7] = new int[] {0,0,0,0,0,6,0,0,0};
		board[7][1] = new int[] {0,0,0,0,0,0,0,0,9};
		board[7][4] = new int[] {0,0,0,0,5,0,0,0,0};
		board[7][5] = new int[] {0,0,0,4,0,0,0,0,0};
		board[8][6] = new int[] {1,0,0,0,0,0,0,0,0};
	}
	
	
	public static void main(final String[] args) {
		GrilleQuatreMoyen();
		AlgoSudoku.Resolve(board);
		AlgoSudoku.Display();
		System.out.println("Nb de tour : " + AlgoSudoku.nbTour);
		System.out.println("Nb chiffre: " + AlgoSudoku.CompterCelluleTrouve());
	}
	
	public static void DisplayCellule(int r, int c) {
		System.out.println();
		for (int x = 0 ; x < 9 ; x++) {
			System.out.print(AlgoSudoku.matrice[r][c][x] + "|");
		}
	}
	
}
