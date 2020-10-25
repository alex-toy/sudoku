package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

class SudokuPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int digit; //the number it would display
    int x, y; //the x,y position on the grid

    SudokuPanel(int x, int y) {
        super();

        this.x = x;
        this.y = y;

        /** create a black border */
        setBorder(BorderFactory.createLineBorder(Color.black));

        /** set size to 50x50 pixel for one square */
        Dimension dim = getPreferredSize();
		dim.width = 25;
		setPreferredSize(dim);
		setMinimumSize(dim);
    }

    public int getDigit() { return digit; }

    //getters for x and y

    public void setDigit(int num) { digit = num; }

}