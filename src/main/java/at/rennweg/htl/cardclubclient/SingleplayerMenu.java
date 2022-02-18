package at.rennweg.htl.cardclubclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SingleplayerMenu extends Application {
    @Override
    public void start(Stage stageX) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameBoard.class.getResource("singleplayerMenu_v1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.setStage(stageX);
        Stage stage = Main.getStage();

        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
