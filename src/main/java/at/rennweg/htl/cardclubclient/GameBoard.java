package at.rennweg.htl.cardclubclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameBoard extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameBoard.class.getResource("spielbrett_v1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Play UNO");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
