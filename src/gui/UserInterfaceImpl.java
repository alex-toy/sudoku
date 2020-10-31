package gui;

import constants.Dimensions;
import constants.GameState;
import problemdomain.Coordinates;
import problemdomain.SudokuGame;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.HashMap;

/**
 * Manages the window, and displays a pop up notification when the user completes the puzzle.
 */
public class UserInterfaceImpl implements IUserInterfaceContract.View, EventHandler<KeyEvent> {
    
	private final Stage stage;
    private final Group root;

    private HashMap<Coordinates, SudokuTextField> textFieldCoordinates;

    private IUserInterfaceContract.EventListener listener;

    //Size of the window
    //private static final double WINDOW_Y = 732;
    private static final double WINDOW_Y = Dimensions.WINDOW_X;
    private static final double WINDOW_X = Dimensions.WINDOW_X;
    //distance between window and board
    private static final double BOARD_PADDING = Dimensions.BOARD_PADDING;

    private static final double BOARD_X_AND_Y = Dimensions.TOTAL_SIZE;
    private static final Color WINDOW_BACKGROUND_COLOR = Color.rgb(0, 150, 136);
    private static final Color BOARD_BACKGROUND_COLOR = Color.rgb(224, 242, 241);
    private static final String SUDOKU = "Sudoku";

    /**
     *
     * @param stage
     */
    public UserInterfaceImpl(Stage stage) {
        this.stage = stage;
        this.root = new Group();
        this.textFieldCoordinates = new HashMap<>();
        initializeUserInterface();
    }


    @Override
    public void setListener(IUserInterfaceContract.EventListener listener) {
        this.listener = listener;
    }

    public void initializeUserInterface() {
        drawBackground(root);
        drawTitle(root);
        drawSudokuBoard(root);
        drawTextFields(root);
        drawGridLines(root);
        drawCommands(root);
        stage.show();
    }

    /**
     * 1. Draw each TextField based on x and y values.
     * 2. As each TextField is drawn, add it's coordinates (x, y) based on it's Hash Value to
     * to the HashMap.
     *
     * @param root
     */
    private void drawTextFields(Group root) {
        //where to start drawing the numbers
        final int xOrigin = (int) Dimensions.BOARD_PADDING;
        final int yOrigin = (int) Dimensions.BOARD_PADDING;
        //how much to move the x or y value after each loop
        final int xAndYDelta = Dimensions.DELTA;


        for (int xIndex = 0; xIndex < 9; xIndex++) {
            for (int yIndex = 0; yIndex < 9; yIndex++) {
                int x = xOrigin + xIndex * xAndYDelta;
                int y = yOrigin + yIndex * xAndYDelta;
                
                SudokuTextField tile = new SudokuTextField(xIndex, yIndex);

                styleSudokuTile(tile, x, y);

                tile.setOnKeyPressed(this);

                textFieldCoordinates.put(new Coordinates(xIndex, yIndex), tile);

                root.getChildren().add(tile);
            }
        }
    }

    /**
     * Helper method for styling a sudoku tile number
     * @param tile
     * @param x
     * @param y
     */
    private void styleSudokuTile(SudokuTextField tile, double x, double y) {
        Font numberFont = new Font(Dimensions.NUMBER_SIZE);
        tile.setFont(numberFont);
        tile.setAlignment(Pos.CENTER);
        tile.setLayoutX(x);
        tile.setLayoutY(y);
        tile.setPrefHeight(Dimensions.CHECKBOX_HEIGHT);
        tile.setPrefWidth(Dimensions.CHECKBOX_WIDTH);

        tile.setBackground(Background.EMPTY);
    }


    /**
     * In order to draw the various lines that make up the Sudoku grid, we use a starting x and y offset
     * value (remember, x grows positively from left to right, and y grows positively from top to bottom).
     * Each square is meant to be 64x64 units, so we add that number each time a
     * @param root
     */
    private void drawGridLines(Group root) {
        //draw vertical lines starting at 114x and 114y:
        int xAndY = (int) Dimensions.XANDY;
        int index = 0;
        while (index < 8) {
            int thickness;
            if (index == 2 || index == 5) {
                thickness = 3;
            } else {
                thickness = 2;
            }

            Rectangle verticalLine = getLine(
                    xAndY + Dimensions.CHECKBOX_WIDTH * index,
                    BOARD_PADDING,
                    BOARD_X_AND_Y,
                    thickness
                    );

            Rectangle horizontalLine = getLine(
                    BOARD_PADDING,
                    xAndY + Dimensions.CHECKBOX_HEIGHT * index,
                    thickness,
                    BOARD_X_AND_Y
            );

            root.getChildren().addAll(
                    verticalLine,
                    horizontalLine
            );

            index++;
        }
    }

    /**
     * Convenience method to reduce repetitious code.
     *
     * X, Y, Height, Width,
     * @return A Rectangle to specification
     */
    public Rectangle getLine(double x, double y, double height, double width){
        Rectangle line = new Rectangle();

        line.setX(x);
        line.setY(y);

        line.setHeight(height);
        line.setWidth(width);

        line.setFill(Color.BLACK);
        return line;

    }

    /**
     * Background of the primary window
     * @param root
     */
    private void drawBackground(Group root) {
        Scene scene = new Scene(root, WINDOW_X, WINDOW_Y);
        scene.setFill(WINDOW_BACKGROUND_COLOR);
        stage.setScene(scene);
    }

    /**
     * Background of the actual sudoku board, offset from the window by BOARD_PADDING
     * @param root
     */
    private void drawSudokuBoard(Group root) {
        Rectangle boardBackground = new Rectangle();
        boardBackground.setX(BOARD_PADDING);
        boardBackground.setY(BOARD_PADDING);
        boardBackground.setWidth(BOARD_X_AND_Y);
        boardBackground.setHeight(BOARD_X_AND_Y);
        boardBackground.setFill(BOARD_BACKGROUND_COLOR);
        root.getChildren().add(boardBackground);
    }

    private void drawTitle(Group root) {
        Text title = new Text(50, Dimensions.TOTAL_SIZE + 50, SUDOKU);
        title.setFont(new Font(20));
        title.setFill(Color.WHITE);
        
        root.getChildren().add(title);
    }
    
    
    private void drawCommands(Group root) {
    	Button resolve = new Button();
        resolve.setText("Resolve");
        resolve.setLayoutX(300);
        resolve.setLayoutY(20);
        resolve.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("resolve!");
            }
        });
    	root.getChildren().add(resolve);
    	
    	Button new_game = new Button();
    	new_game.setText("New game");
    	new_game.setLayoutX(300);
    	new_game.setLayoutY(50);
    	new_game.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("new game!");
            }
        });
    	root.getChildren().add(new_game);
    }
    
    
    @Override
    public void updateSquare(int x, int y, int input) {
        SudokuTextField tile = textFieldCoordinates.get(new Coordinates(x, y));
        String value = Integer.toString(
                input
        );

        if (value.equals("0")) value = "";

        tile.textProperty().setValue(value);
    }

    @Override
    public void updateBoard(SudokuGame game) {
        for (int xIndex = 0; xIndex < 9; xIndex++) {
            for (int yIndex = 0; yIndex < 9; yIndex++) {
                TextField tile = textFieldCoordinates.get(new Coordinates(xIndex, yIndex));

                String value = Integer.toString(
                        game.getCopyOfGridState()[xIndex][yIndex]
                );

                if (value.equals("0")) value = "";
                tile.setText(
                        value
                );

                if (game.getGameState() == GameState.NEW){
                    if (value.equals("")) {
                        tile.setStyle("-fx-opacity: 1;");
                        tile.setDisable(false);
                    } else {
                        tile.setStyle("-fx-opacity: 0.8;");
                        tile.setDisable(true);
                    }
                }
            }
        }
    }

    @Override
    public void showDialog(String message) {
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.OK);
        dialog.showAndWait();

        if (dialog.getResult() == ButtonType.OK) listener.onDialogClick();
    }

    @Override
    public void showError(String message) {
        Alert dialog = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        dialog.showAndWait();
    }


    @Override
    public void handle(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            if (event.getText().equals("0")
                    || event.getText().equals("1")
                    || event.getText().equals("2")
                    || event.getText().equals("3")
                    || event.getText().equals("4")
                    || event.getText().equals("5")
                    || event.getText().equals("6")
                    || event.getText().equals("7")
                    || event.getText().equals("8")
                    || event.getText().equals("9")
            ) {
                int value = Integer.parseInt(event.getText());
                handleInput(value, event.getSource());
            } else if (event.getCode() == KeyCode.BACK_SPACE) {
                handleInput(0, event.getSource());
            } else {
                ((TextField)event.getSource()).setText("");
            }
        }

        event.consume();
    }

    /**
     * @param value  expected to be an integer from 0-9, inclusive
     * @param source the textfield object that was clicked.
     */
    private void handleInput(int value, Object source) {
        listener.onSudokuInput(
                ((SudokuTextField) source).getX(),
                ((SudokuTextField) source).getY(),
                value
        );
    }
}
