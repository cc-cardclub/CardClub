package at.rennweg.htl.cardclubclient;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * set the stage of the About-Layout in StartMenu
 *
 * @author Lisa-Marie Hörmann, Bernd Reither
 */
public class About {
    /**
     * for Main to launch the new stage after button click on "About" in StartMenu
     *
     * @throws IOException
     */
    public static void start() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameBoard.class.getResource("about_v1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = Main.getStage();

        stage.setTitle("About us");
        stage.setScene(scene);
        stage.show();
    }
}
