package at.rennweg.htl.cardclubclient;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage primaryStage;

    public static Stage getStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        Startmenu.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
