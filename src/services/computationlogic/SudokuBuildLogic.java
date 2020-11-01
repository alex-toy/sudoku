package services.computationlogic;

import java.io.IOException;

import gui.ControlLogic;
import gui.IUserInterfaceContract;

import model.LocalStorageImpl;
import problemdomain.IStorage;
import problemdomain.SudokuGame;

public class SudokuBuildLogic {

    public static void build(IUserInterfaceContract.View userInterface) throws IOException {
        SudokuGame initialState;
        IStorage storage = (IStorage) new LocalStorageImpl();

        try {
             initialState = storage.getGameData();
        } catch (IOException e) {

            initialState = GameLogic.getNewGame();
            storage.updateGameData(initialState);
        }

        IUserInterfaceContract.EventListener uiLogic = new ControlLogic(storage, userInterface);
        userInterface.setListener(uiLogic);
        userInterface.updateBoard(initialState);
    }
    
    
}