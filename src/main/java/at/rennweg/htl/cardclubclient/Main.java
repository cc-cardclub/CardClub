package at.rennweg.htl.cardclubclient;

import at.rennweg.htl.cardclubclient.logic.ServerConnection;
import at.rennweg.htl.cardclubserver.Server;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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

        // Set icon
        Main.primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("img/cc-logo.png")));

        // Generate client uuid
        Properties settings = OptionsController.getProps();

        try {
            UUID uuid = UUID.fromString(settings.getProperty("uuid"));
        } catch (IllegalArgumentException e) {
            UUID uuid = UUID.randomUUID();
            settings.setProperty("uuid", uuid.toString());
            OptionsController.setProps(settings);
        }

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
