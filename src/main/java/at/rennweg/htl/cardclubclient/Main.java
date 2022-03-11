package at.rennweg.htl.cardclubclient;

import javafx.application.Application;
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
        Startmenu.start();
    }

    /**
     * launch the current stage
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
