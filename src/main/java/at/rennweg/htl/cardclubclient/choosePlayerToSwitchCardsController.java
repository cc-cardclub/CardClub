package at.rennweg.htl.cardclubclient;

import at.rennweg.htl.cardclubclient.logic.GameCore;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class choosePlayerToSwitchCardsController {

    public AnchorPane choosePlayerPopUp;

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
