package at.rennweg.htl.cardclubclient;

import at.rennweg.htl.cardclubclient.logic.GameCore;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

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
 * @author Bernd Reither, Mattias Burkard
 */
public class OptionsController implements Initializable {

    public static final String PROPS_PATH_WIN = System.getProperty("user.home")
            + "\\AppData\\Local\\cc-cardclub\\settings.properties";

    public static final String PROPS_PATH_LINUX = System.getProperty("user.home")
            + "/.cc-cardclub/settings.properties";
    
    public static String propsPath;

    @FXML
    private Slider volumeSlider;
    @FXML
    private TextField serverIp;
    @FXML
    private TextField serverPort;

    private Properties props;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPath();

        // Load props
        props = getProps();

        // Set values
        volumeSlider.adjustValue(Double.parseDouble(props.getProperty("volume")));
        serverIp.setText(props.getProperty("server"));
        serverPort.setText(props.getProperty("port"));

        // onVolumeSlider
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            props.setProperty("volume", String.valueOf(volumeSlider.getValue()));
            setProps(props);
        });
    }

    /**
     * Sets the path for the settings file
     */
    public static void setPath() {
        // Set correct path for settings
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            propsPath = PROPS_PATH_WIN;
        } else {
            propsPath = PROPS_PATH_LINUX;
        }
    }

    /**
     * return to StartMenu
     *
     * @throws IOException
     */
    @FXML
    protected void onReturnToStartmenuButton() throws IOException {
        props.setProperty("server", serverIp.getText());
        props.setProperty("port", serverPort.getText());
        setProps(props);

        Startmenu.start();
    }

    /**
     * reset the game
     */
    @FXML
    protected void onResetGameCore() {
        GameCore.reset();
    }

    public static Properties getProps() {
        Properties props = new Properties();

        try {
            if (Files.exists(Path.of(propsPath))) {
                props.load(new FileInputStream(propsPath));

                // Check if all properties are in the local file
                Properties defaultProps = new Properties();
                defaultProps.load(AboutController.class.getResourceAsStream("data/settings.properties"));
                defaultProps.forEach((key, value) -> {
                    if (props.getOrDefault(key, "no-value").equals("no-value")) {
                        props.put(key, value);
                    }
                });

                setProps(props);
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

    private static void setProps(Properties props) {
        try {
            props.store(new FileOutputStream(propsPath), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
