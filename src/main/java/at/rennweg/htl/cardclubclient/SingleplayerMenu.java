package at.rennweg.htl.cardclubclient;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * set the stage of the Singleplayer Menu
 *
 * @author Lisa-Marie Hörmann, Bernd Reither
 */
public class SingleplayerMenu {
    /**
     * for Main to launch the new stage: Singleplayer-Menu
     * @throws IOException
     */
    public static void start() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                GameBoard.class.getResource("singleplayerMenu_v1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = Main.getStage();

        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }
}
