package at.rennweg.htl.cardclubclient;

import javafx.fxml.FXML;

import java.io.IOException;

public class SingleplayerMenuController {

    @FXML
    protected void onPlayButtonClick() throws IOException {
        GameBoard.start();
    }

}
