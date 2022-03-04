package at.rennweg.htl.cardclubclient.logic;

import at.rennweg.htl.cardclubclient.GameBoardController;
import at.rennweg.htl.cardclubclient.cards.Card;
import javafx.scene.control.Alert;

public class Checker {
    public static boolean checkTurnValidity(Card currentCard, Card previousCard) {
        if (GameCore.plus2and4CardsSelected && previousCard.getNumber().equals("wildDraw4")
                && currentCard.getNumber().equals("draw2")) {
            return true;
        }

        if (currentCard.getColor().equals("black") || previousCard.getColor().equals("black")) {
            return true;
        }
        if (currentCard.getNumber().equals(previousCard.getNumber())) {
            return true;
        }
        if (currentCard.getColor().equals(previousCard.getColor())) {
            return true;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        GameCore.pauseProgressBar = true;
        alert.setTitle("Information");
        alert.setHeaderText("Zug kann nicht ausgeführt werden! Nicht zulässige Karte gelegt!");
        alert.setContentText("Zug ist beendet, 2 Strafkarten");

        alert.showAndWait();

        return false;
    }
}
