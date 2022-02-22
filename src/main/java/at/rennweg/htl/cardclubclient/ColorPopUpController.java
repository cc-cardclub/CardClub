package at.rennweg.htl.cardclubclient;

import at.rennweg.htl.cardclubclient.cards.Deck;
import javafx.event.ActionEvent;

public class ColorPopUpController {

    public void onBlueColorSelected(ActionEvent actionEvent) {
        Deck.getLastCard().setColor("blue");
    }

    public void onRedColorSelected(ActionEvent actionEvent) {
        Deck.getLastCard().setColor("red");
    }

    public void onYellowColorSelected(ActionEvent actionEvent) {
        Deck.getLastCard().setColor("yellow");
    }

    public void onGreenColorSelected(ActionEvent actionEvent) {
        Deck.getLastCard().setColor("green");
    }
}
