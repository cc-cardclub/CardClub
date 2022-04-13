package at.rennweg.htl.cardclubclient;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * set the stage of the Options-Layout in StartMenu
 *
 * @author Lisa-Marie Hörmann, Bernd Reither
 */
public class Leaderboard {
    /**
     * for Main to launch the new stage after button click on "Leaderboard" in StartMenu
     *
     * @throws IOException
     */
    public static void start() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameBoard.class.getResource("leaderboard_v1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = Main.getStage();

        stage.setScene(scene);
        stage.show();
    }
}
