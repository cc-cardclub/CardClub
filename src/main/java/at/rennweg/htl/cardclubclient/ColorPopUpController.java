package at.rennweg.htl.cardclubclient;

import at.rennweg.htl.cardclubclient.cards.Deck;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ColorPopUpController {
    @FXML
    private AnchorPane ColorPopUp;

    public void onBlueColorSelected(ActionEvent actionEvent) {
        Deck.getLastCard().setColor("blue");
        closeWindow();
    }

    public void onRedColorSelected(ActionEvent actionEvent) {
        Deck.getLastCard().setColor("red");
        closeWindow();
    }

    public void onYellowColorSelected(ActionEvent actionEvent) {
        Deck.getLastCard().setColor("yellow");
        closeWindow();
    }

    public void onGreenColorSelected(ActionEvent actionEvent) {
        Deck.getLastCard().setColor("green");
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) ColorPopUp.getScene().getWindow();
        stage.close();
    }
}
