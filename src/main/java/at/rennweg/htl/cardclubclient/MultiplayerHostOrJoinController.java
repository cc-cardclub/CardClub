package at.rennweg.htl.cardclubclient;

import javafx.event.ActionEvent;

import java.io.IOException;

/**
 * MultiplayerHostOrJoinController class<br>
 * Used to control the host or join scene
 *
 * @author Lisa-Marie Hörmann, Bernd Reither
 */
public class MultiplayerHostOrJoinController {
    /**
     * Start the host scene
     *
     * @param actionEvent
     * @throws IOException
     */
    public void multiplayerHostGame(ActionEvent actionEvent) throws IOException {
        MultiplayerMenuHost.start();
    }

    /**
     * Start the join scene
     *
     * @param actionEvent
     * @throws IOException
     */
    public void multiplayerJoinGame(ActionEvent actionEvent) throws IOException {
        MultiplayerMenuJoin.start();
    }

    /**
     * Start the startmenu scene
     *
     * @param actionEvent
     * @throws IOException
     */
    public void backToStartMenu(ActionEvent actionEvent) throws IOException {
        Startmenu.start();
    }
}
