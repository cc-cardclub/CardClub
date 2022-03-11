package at.rennweg.htl.cardclubclient;

import at.rennweg.htl.cardclubclient.logic.GameCore;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * class to close the OptionsMenu
 *
 * @author Bernd Reither
 */
public class OptionsController {
    /**
     * return to StartMenu
     *
     * @throws IOException
     */
    @FXML
    protected void onReturnToStartmenuButton() throws IOException {
        Startmenu.start();
    }

    /**
     * reset the game
     */
    @FXML
    protected void onResetGameCore() {
        GameCore.reset();
    }
}
