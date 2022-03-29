package at.rennweg.htl.cardclubclient;

import at.rennweg.htl.cardclubclient.logic.GameCore;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * class to close the OptionsMenu
 *
 * @author Bernd Reither
 */
public class OptionsController implements Initializable {

    public static final String PROPS_PATH_WIN = System.getProperty("user.home")
            + "\\AppData\\Local\\cc-cardclub\\settings.properties";

    public static final String PROPS_PATH_LINUX = "~/.cc-cardclub/settings.properties";
    
    public static String propsPath;

    @FXML
    private Slider volumeSlider;

    private Properties props;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set correct path for settings
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            propsPath = PROPS_PATH_WIN;
        } else {
            propsPath = PROPS_PATH_LINUX;
        }

        // Load props
        props = getProps();

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
            if (Files.exists(Path.of(propsPath))) {
                props.load(new FileInputStream(propsPath));
            } else {
                props.load(AboutController.class.getResourceAsStream("data/settings.properties"));
                Files.createDirectories(Path.of(
                        propsPath.substring(0, propsPath.length() - "settings.properties".length())));
                props.store(new FileOutputStream(propsPath), null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return props;
    }

    private void setProps(Properties props) {
        try {
            props.store(new FileOutputStream(propsPath), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
