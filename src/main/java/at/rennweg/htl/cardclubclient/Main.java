package at.rennweg.htl.cardclubclient;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Properties;
import java.util.UUID;

/**
 * change between stages
 *
 * @author Bernd Reither, Mattias Burkard
 */
public class Main extends Application {
    /**
     * store the current stage
     */
    private static Stage primaryStage;

    /**
     * Background music player
     */
    public static MediaPlayer mediaPlayer;

    /**
     * get the current stage
     *
     * @return current stage
     */
    public static Stage getStage() {
        return primaryStage;
    }

    /**
     * set the stage
     *
     * @param primaryStage set stage (GameBoard, Menu)
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;

        // Set title
        Properties propsInfo = new Properties();
        propsInfo.load(AboutController.class.getResourceAsStream("data/info.properties"));
        Main.primaryStage.setTitle("CardClub V" + propsInfo.getProperty("version"));

        // Set icon
        Main.primaryStage.getIcons().add(
                new Image(Main.class.getResourceAsStream("img/cc-logo.png")));

        // Generate client uuid
        Properties settings = OptionsController.getProps();
        UUID uuid;

        try {
            uuid = UUID.fromString(settings.getProperty("uuid"));
        } catch (IllegalArgumentException e) {
            uuid = UUID.randomUUID();
            settings.setProperty("uuid", uuid.toString());
            OptionsController.setProps(settings);
        }

        if (settings.getProperty("username").equals("0")) {
            settings.setProperty("username", uuid.toString());
            OptionsController.setProps(settings);
        }

        // Start background music
        mediaPlayer = new MediaPlayer(new Media(getClass().getResource("audio/bgmusic.mp3").toString()));
        mediaPlayer.setVolume(Double.parseDouble(settings.getProperty("volume")) / 100);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        mediaPlayer.play();

        Startmenu.start();
    }

    /**
     * launch the current stage
     *
     * @param args
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            if (args[args.length - 1].equals("server")) {
                at.rennweg.htl.cardclubserver.Main.main(args);
            } else {
                OptionsController.setPath();
                launch(args);
            }
        } else {
            OptionsController.setPath();
            launch(args);
        }
    }
}
