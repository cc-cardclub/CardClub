package at.rennweg.htl.cardclubclient;

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
 * OptionsController class<br>
 * Used to access the settings file and control the options menu
 *
 * @author Bernd Reither, Raven Burkard
 */
public class OptionsController implements Initializable {

    /**
     * Props path for windows systems
     */
    public static final String PROPS_PATH_WIN = System.getProperty("user.home")
            + "\\AppData\\Local\\cc-cardclub\\settings.properties";

    /**
     * Props path for linux systems
     */
    public static final String PROPS_PATH_LINUX = System.getProperty("user.home")
            + "/.cc-cardclub/settings.properties";

    /**
     * The props path (different for every system)
     */
    public static String propsPath;

    /**
     * The volume slider
     */
    @FXML
    private Slider volumeSlider;
    /**
     * The username field
     */
    @FXML
    private TextField userName;
    /**
     * The server ip field
     */
    @FXML
    private TextField serverIp;
    /**
     * The server port field
     */
    @FXML
    private TextField serverPort;

    /**
     * The properties
     */
    private Properties props;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPath();

        // Load props
        props = getProps();

        // Set values
        volumeSlider.adjustValue(Double.parseDouble(props.getProperty("volume")));
        userName.setText(props.getProperty("username"));
        serverIp.setText(props.getProperty("server"));
        serverPort.setText(props.getProperty("port"));

        // onVolumeSlider
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            props.setProperty("volume", String.valueOf(volumeSlider.getValue()));
            Main.mediaPlayer.setVolume(Double.parseDouble(props.getProperty("volume")) / 100);
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
     * @throws IOException
     */
    @FXML
    protected void onReturnToStartmenuButton() throws IOException {
        props.setProperty("username", userName.getText());
        props.setProperty("server", serverIp.getText());
        props.setProperty("port", serverPort.getText());
        setProps(props);

        Startmenu.start();
    }

    /**
     * Get the properties (file)
     *
     * @return Options properties (from file)
     */
    public static Properties getProps() {
        Properties props = new Properties();

        try {
            if (Files.exists(Path.of(propsPath))) {
                props.load(new FileInputStream(propsPath));

                // Check if all properties are in the local file
                Properties defaultProps = new Properties();
                defaultProps.load(
                        AboutController.class.getResourceAsStream("data/settings.properties"));
                defaultProps.forEach((key, value) -> {
                    if (props.getOrDefault(key, "no-value").equals("no-value")) {
                        props.put(key, value);
                    }
                });

                setProps(props);
            } else {
                props.load(AboutController.class.getResourceAsStream("data/settings.properties"));
                Files.createDirectories(Path.of(
                        propsPath.substring(0, propsPath.length() - "settings.properties".length())
                ));
                props.store(new FileOutputStream(propsPath), null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return props;
    }

    /**
     * Set the properties (file)
     * @param props
     */
    public static void setProps(Properties props) {
        try {
            props.store(new FileOutputStream(propsPath), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
