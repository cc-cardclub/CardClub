package at.rennweg.htl.cardclubclient;

import javafx.stage.Stage;

public class Main {
    private static Stage primaryStage;

    public static void main(String[] args) {
        SingleplayerMenu.launch(SingleplayerMenu.class, args);
    }

    public static Stage getStage() {
        return primaryStage;
    }

    public static void setStage(Stage primaryStage) {
        Main.primaryStage = primaryStage;
    }
}
