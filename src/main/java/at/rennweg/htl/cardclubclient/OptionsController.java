package at.rennweg.htl.cardclubclient;

import at.rennweg.htl.cardclubclient.logic.GameCore;
import javafx.fxml.FXML;

import java.io.IOException;

public class OptionsController {
    @FXML
    protected void onReturnToStartmenuButton() throws IOException {
        Startmenu.start();
    }

    @FXML
    protected void onResetGameCore() {
        GameCore.reset();
    }
}
