package problemdomain;



import java.io.Serializable;

import constants.GameState;
import services.computationlogic.SudokuUtilities;

public class SudokuGame implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final GameState gameState;
    private final int[][] gridState;

    /**
     * To make it easier to work with Arrays (where the first index position is 0 instead of 1, and so on),
     * Grid coordinates will be represented with x and y index values ranging from 0 (inclusive) to 8 (inclusive).
     */
    public static final int GRID_BOUNDARY = 9;

    public SudokuGame(GameState gameState, int[][] gridState) {
        this.gameState = gameState;
        this.gridState = gridState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public int[][] getCopyOfGridState() {
        return SudokuUtilities.copyToNewArray(gridState);
    }

}
