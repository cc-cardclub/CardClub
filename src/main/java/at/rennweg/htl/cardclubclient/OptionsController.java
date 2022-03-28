package at.rennweg.htl.cardclubclient;

import at.rennweg.htl.cardclubclient.logic.GameCore;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.input.TouchEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * class to close the OptionsMenu
 *
 * @author Bernd Reither
 */
public class OptionsController implements Initializable {
    @FXML
    private Slider volumeSlider;

    private final Properties props = getProps();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        volumeSlider.adjustValue(Double.parseDouble(props.getProperty("volume")));

        // onVolumeSlider
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            props.setProperty("volume", String.valueOf(volumeSlider.getValue()));
            setProps(props);
        });
    }

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

    private Properties getProps() {
        Properties props = new Properties();

        try {
            if (Files.exists(Path.of(System.getProperty("user.home")
                    + "\\AppData\\Local\\CC\\settings.properties"))) {
                props.load(new FileInputStream(System.getProperty("user.home")
                        + "\\AppData\\Local\\CC\\settings.properties"));
            } else {
                props.load(AboutController.class.getResourceAsStream("data/settings.properties"));
                props.store(new FileOutputStream(System.getProperty("user.home")
                        + "\\AppData\\Local\\CC\\settings.properties"), null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return props;
    }

    private void setProps(Properties props) {
        try {
            props.store(new FileOutputStream(System.getProperty("user.home")
                    + "\\AppData\\Local\\CC\\settings.properties"), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
