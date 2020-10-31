package constants;

public class Dimensions {
	  private Dimensions() {}
	  public static final int CHECKBOX_WIDTH = 30;
	  public static final int CHECKBOX_HEIGHT = CHECKBOX_WIDTH;
	  public static final int DELTA = CHECKBOX_WIDTH;
	  public static final int TOTAL_SIZE = 9 * CHECKBOX_WIDTH;
	  public static final int NUMBER_SIZE = CHECKBOX_WIDTH / 3;
	 
	  public static final double WINDOW_Y = TOTAL_SIZE + 400;
	  public static final double WINDOW_X = TOTAL_SIZE + 100;
	  
	  public static final double BOARD_PADDING = Math.round(50/64.0 * CHECKBOX_WIDTH);
	  public static final double XANDY = Math.round(114/64.0 * CHECKBOX_WIDTH); 
	  
	}