package at.rennweg.htl.cardclubclient;

import at.rennweg.htl.cardclubclient.logic.ServerConnection;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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
                ServerConnection.connect();
                launch(args);
            }
        } else {
            ServerConnection.connect();
            launch(args);
        }
    }
}
