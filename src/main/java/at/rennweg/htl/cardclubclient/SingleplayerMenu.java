package at.rennweg.htl.cardclubclient;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SingleplayerMenu {
    public static void start() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameBoard.class.getResource("singleplayerMenu_v1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = Main.getStage();

        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }
}
