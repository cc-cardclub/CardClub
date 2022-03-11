package at.rennweg.htl.cardclubclient;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * set the stage of the StartMenu
 *
 * @author Lisa-Marie Hörmann, Bernd Reither
 */
public class Startmenu {
    /**
     * for Main to launch the stage at the beginning
     *
     * @throws IOException
     */
    public static void start() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameBoard.class.getResource("startmenu_v1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = Main.getStage();

        stage.setTitle("Startmenu");
        stage.setScene(scene);
        stage.show();
    }
}
