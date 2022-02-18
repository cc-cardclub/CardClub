package at.rennweg.htl.cardclubclient;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameBoardController {
    @FXML
    private Button UNOButton;

    @FXML
    protected void onUNOButtonClick() {
        UNOButton.setText("Clicked");
    }

    @FXML
    protected void onExitButtonClick() {
        System.exit(0);
    }
}
