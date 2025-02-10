package at.rennweg.htl.cardclubclient.logic;

import at.rennweg.htl.cardclubclient.cards.Card;
import at.rennweg.htl.cardclubclient.cards.Deck;
import at.rennweg.htl.cardclubclient.cards.Player;
import javafx.scene.control.Alert;

/**
 * Class for checking the validity of a turn
 *
 * @author Raven Burkard, Lisa-Marie Hörmann
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
        GameCore.pauseProgressBar = true;

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Zug kann nicht ausgeführt werden! Nicht zulässige Karte gelegt!");
        alert.setContentText("Zug ist beendet, 2 Strafkarten");

        alert.showAndWait();
        GameCore.pauseProgressBar = false;
        return false;
    }

    /**
     * Check if a card could be played
     *
     * @param currentCard  card to play
     * @param previousCard previously played card
     * @return whether the turn is valid
     */
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
        return currentCard.getColor().equals(previousCard.getColor());
    }

    /**
     * Check if a play could play a card
     *
     * @param playerCards cards of the player
     * @return whether the player has any possible turns
     */
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
