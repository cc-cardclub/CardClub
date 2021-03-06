package at.rennweg.htl.cardclubclient;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * MultiplayerMenuHost class<br>
 * Class used to start the multiplayer menu (host)
 *
 * @author Lisa-Marie Hörmann, Bernd Reither
 */
public class MultiplayerMenuHost {
    /**
     * for Main to launch the stage at the beginning
     *
     * @throws IOException
     */
    public static void start() throws IOException {
        FXMLLoader fxmlLoader =
                new FXMLLoader(GameBoard.class.getResource("multiplayerMenuHost_v1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = Main.getStage();

        stage.setScene(scene);
        stage.show();
    }
}
