package at.rennweg.htl.cardclubclient;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameBoard {
    private static GameBoardController controller ;

    public void setController(GameBoardController controller) {
        GameBoard.controller = controller ;
    }

    public static void start() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameBoard.class.getResource("spielbrett_v1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = Main.getStage();
        stage.setTitle("Play UNO");
        stage.setScene(scene);
        stage.show();
    }


    public static void endBotTurn() {
        controller.endBotTurn();
    }
}
