package at.rennweg.htl.cardclubclient;

import javafx.fxml.FXML;

import java.io.IOException;

/**
 * class to control the StartMenu buttons
 *
 * @author Lisa-Marie Hörmann, Bernd Reither
 */
public class StartmenuController {
    /**
     * open the About window on Button Click
     *
     * @throws IOException
     */
    @FXML
    protected void onAboutButtonClick() throws IOException {
        About.start();
    }

    /**
     * open the SingleplayerMenu window on Button Click
     *
     * @throws IOException
     */
    @FXML
    protected void onSingleplayerButtonCLick() throws IOException {
        SingleplayerMenu.start();
    }

    /**
     * open the Options window on Button Click
     *
     * @throws IOException
     */
    public void onOptionsButtonClick() throws IOException {
        Options.start();
    }

    /**
     * Open the Leaderboard scene
     *
     * @throws IOException
     */
    public void onLeaderButtonClick() throws IOException {
        Leaderboard.start();
    }

    /**
     * Open the MultiplayerHostOrJoin scene
     * @throws IOException
     */
    public void onMultiplayerButtonClick() throws IOException {
        MultiplayerHostOrJoin.start();
    }
}
