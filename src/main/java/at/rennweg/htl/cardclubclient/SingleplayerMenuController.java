package at.rennweg.htl.cardclubclient;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SingleplayerMenuController {

    @FXML
    protected void onPlayButtonClick() {
        GameBoard.main(new String[0]);
    }

    @FXML
    protected void onExitButtonClick() {
        System.exit(0);
    }
}
