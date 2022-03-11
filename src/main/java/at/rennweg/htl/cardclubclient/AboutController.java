package at.rennweg.htl.cardclubclient;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

import java.awt.Desktop;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * class to control the About-Layout
 *
 * @author Bernd Reither
 */
public class AboutController implements Initializable {
    /**
     * stores the Current Version of the game
     */
    @FXML
    private Label currVersion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Properties propsInfo = new Properties();
            propsInfo.load(AboutController.class.getResourceAsStream("data/info.properties"));

            currVersion.setText(currVersion.getText() + propsInfo.getProperty("version"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
