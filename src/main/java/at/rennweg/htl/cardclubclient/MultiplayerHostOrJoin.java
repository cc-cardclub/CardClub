package at.rennweg.htl.cardclubclient;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * MultiplayerHostOrJoin class<br>
 * Used to start the Host or Join scene
 *
 * @author Lisa-Marie Hörmann, Bernd Reither
 */
public class MultiplayerHostOrJoin {
    /**
     * for Main to launch the stage at the beginning
     *
     * @throws IOException
     */
    public static void start() throws IOException {
        FXMLLoader fxmlLoader =
                new FXMLLoader(GameBoard.class.getResource("multiplayerHostOrJoin_v1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = Main.getStage();

        stage.setScene(scene);
        stage.show();
    }

}
