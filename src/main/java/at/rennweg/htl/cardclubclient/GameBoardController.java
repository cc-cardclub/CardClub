package at.rennweg.htl.cardclubclient;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameBoardController {
    @FXML
    private Button UNOButton;

    @FXML
    protected void onHelloButtonClick() {
        UNOButton.setText("Clicked");
    }
}
