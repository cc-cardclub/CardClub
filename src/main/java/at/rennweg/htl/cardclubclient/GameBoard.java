package at.rennweg.htl.cardclubclient;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * set the stage of our GameBoard to play UNO
 *
 * @author Lisa-Marie Hörmann, Bernd Reither
 */
public class GameBoard {
    /**
     * set the controller of the GameBoard
     */
    private static GameBoardController controller;

    /**
     * set the controller of the GameBoard
     * @param controller
     */
    public void setController(GameBoardController controller) {
        GameBoard.controller = controller;
    }

    /**
     * for Main to launch the new stage after Singleplayer-Menu
     * @throws IOException
     */
    public static void start() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameBoard.class.getResource("spielbrett_v1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = Main.getStage();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * for controller to end a Bot turn
     */
    public static void endBotTurn() {
        controller.endBotTurn();
    }

    /**
     * for controller to refresh the board after game finished
     */
    public static void refresh() {
        controller.refresh();
    }
}
