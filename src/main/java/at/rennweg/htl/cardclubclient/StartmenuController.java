package at.rennweg.htl.cardclubclient;

import javafx.fxml.FXML;

import java.io.IOException;

public class StartmenuController {

    @FXML
    protected void onAboutButtonClick() throws IOException {
        About.start();
    }


    @FXML
    protected void onSingleplayerButtonCLick() throws IOException {
        SingleplayerMenu.start();
    }
}
