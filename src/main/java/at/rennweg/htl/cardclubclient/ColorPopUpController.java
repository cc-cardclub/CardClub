package at.rennweg.htl.cardclubclient;

import at.rennweg.htl.cardclubclient.cards.Deck;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * choose the color when you play a wild-card
 *
 * @author Lisa-Marie
 */
public class ColorPopUpController {
    /**
     * stage with the colour buttons
     */
    @FXML
    private AnchorPane colorPopUp;

    /**
     * choose the color blue and set it as the next card on the pile
     *
     * @param actionEvent
     */
    public void onBlueColorSelected(ActionEvent actionEvent) {
        Deck.getLastCard().setColor("blue");
        closeWindow();
    }

    /**
     * choose the color red and set it as the next card on the pile
     *
     * @param actionEvent
     */
    public void onRedColorSelected(ActionEvent actionEvent) {
        Deck.getLastCard().setColor("red");
        closeWindow();
    }

    /**
     * choose the color yellow and set it as the next card on the pile
     *
     * @param actionEvent
     */
    public void onYellowColorSelected(ActionEvent actionEvent) {
        Deck.getLastCard().setColor("yellow");
        closeWindow();
    }

    /**
     * choose the color green and set it as the next card on the pile
     *
     * @param actionEvent
     */
    public void onGreenColorSelected(ActionEvent actionEvent) {
        Deck.getLastCard().setColor("green");
        closeWindow();
    }

    /**
     * close the color-choose-window
     */
    private void closeWindow() {
        Stage stage = (Stage) colorPopUp.getScene().getWindow();
        stage.close();
    }
}
