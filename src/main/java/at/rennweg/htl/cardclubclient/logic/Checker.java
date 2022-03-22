package at.rennweg.htl.cardclubclient.logic;

import at.rennweg.htl.cardclubclient.cards.Card;
import at.rennweg.htl.cardclubclient.cards.Deck;
import at.rennweg.htl.cardclubclient.cards.Player;
import javafx.scene.control.Alert;

/**
 * Class for checking the validity of a turn
 *
 * @author Mattias Burkard, Lisa-Marie Hörmann
 */
public class Checker {
    /**
     * Check whether a turn is valid
     *
     * @param currentCard  card to be played
     * @param previousCard card that was last played
     * @return if the turn is valid
     */
    public static boolean checkTurnValidity(Card currentCard, Card previousCard) {
        if (cardValid(currentCard, previousCard)) {
            return true;
        }

        // Show alert as the turn is invalid
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        GameCore.pauseProgressBar = true;
        alert.setTitle("Information");
        alert.setHeaderText("Zug kann nicht ausgeführt werden! Nicht zulässige Karte gelegt!");
        alert.setContentText("Zug ist beendet, 2 Strafkarten");

        alert.showAndWait();

        return false;
    }

    public static boolean cardValid(Card currentCard, Card previousCard) {
        // Check for the extra rule, which allows more possibilities with draw cards
        if (GameCore.plus2and4CardsSelected && previousCard.getNumber().equals("wildDraw4")
                && currentCard.getNumber().equals("draw2")) {
            return true;
        }

        // Cards with the color black (wild cards) can be put on top of any other card
        if (currentCard.getColor().equals("black") || previousCard.getColor().equals("black")) {
            return true;
        }

        // Test if the cards have an equal number
        if (currentCard.getNumber().equals(previousCard.getNumber())) {
            return true;
        }

        // Test if the cards have an equal color
        if (currentCard.getColor().equals(previousCard.getColor())) {
            return true;
        }

        return false;
    }

    public static boolean canPlay(Player playerCards) {
        boolean canPlay = false;
        for (Card card : playerCards.getAllCards()) {
            if (cardValid(card, Deck.getLastCard())) {
                canPlay = true;
                break;
            }
        }
        return canPlay;
    }
}
