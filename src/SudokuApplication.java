
import javafx.application.Application;
import javafx.stage.Stage;
import services.computationlogic.SudokuBuildLogic;

import java.io.IOException;

import gui.DynamicTiles;
import gui.IUserInterfaceContract;
import gui.UserInterfaceImpl;


public class SudokuApplication extends Application {
    
	private IUserInterfaceContract.View uiImpl;
	//private DynamicTiles dynTiles;

    @Override
    public void start(Stage primaryStage) throws IOException {
        
    	uiImpl = new UserInterfaceImpl(primaryStage);
    	//dynTiles = new DynamicTiles();

        try {
            SudokuBuildLogic.build(uiImpl);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
