package at.rennweg.htl.cardclubclient;

import at.rennweg.htl.cardclubclient.logic.GameCore;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ChoosePlayerToSwitchCardsController implements Initializable {

    public AnchorPane choosePlayerPopUp;
    public Button Bot1Selected;
    public Button Bot3Selected;
    public Button Bot2Selected;
    public Label amountCardsBot1;
    public Label amountCardsBot3;
    public Label amountCardsBot2;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            amountCardsBot1.setText("Anzahl Karten: " + GameCore.getPlayer(1).getAllCards().size());
            if (GameCore.getPlayers().size() == 2) {
                Bot2Selected.setDisable(true);
                amountCardsBot2.setDisable(true);
                Bot3Selected.setDisable(true);
                amountCardsBot3.setDisable(true);
            } else if (GameCore.getPlayers().size() == 3) {
                Bot3Selected.setDisable(true);
                amountCardsBot3.setDisable(true);
            } else {
                amountCardsBot2.setText("Anzahl Karten: " + GameCore.getPlayer(2).getAllCards().size());
                amountCardsBot3.setText("Anzahl Karten: " + GameCore.getPlayer(3).getAllCards().size());
            }
        });

    }

    public void onBot1Selected(ActionEvent actionEvent) {
        GameCore.chosenPlayerSwitchCards = GameCore.getPlayer(1);
        closeWindow();
    }

    public void onBot2Selected(ActionEvent actionEvent) {
        GameCore.chosenPlayerSwitchCards = GameCore.getPlayer(2);
    }

    public void onBot3Selected(ActionEvent actionEvent) {
        GameCore.chosenPlayerSwitchCards = GameCore.getPlayer(3);
    }

    /**
     * close the color-choose-window
     */
    private void closeWindow() {
        Stage stage = (Stage) choosePlayerPopUp.getScene().getWindow();
        stage.close();
    }
}