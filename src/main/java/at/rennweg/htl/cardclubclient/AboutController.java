package at.rennweg.htl.cardclubclient;

import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import java.awt.Desktop;

public class AboutController {
    @FXML
    protected void onReturnToStartmenuButtonClick() throws IOException {
        Startmenu.start();
    }

    @FXML
    protected void onLink() {
        try {
            Desktop.getDesktop().browse(new URL("https://github.com/cc-cardclub/CardClubClient").toURI());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
