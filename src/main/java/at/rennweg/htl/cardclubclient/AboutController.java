package at.rennweg.htl.cardclubclient;

import javafx.fxml.FXML;

import java.io.IOException;

public class AboutController {
    @FXML
    protected void onReturnToStartmenuButtonClick() throws IOException {
        Startmenu.start();
    }
}
