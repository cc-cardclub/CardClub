package at.rennweg.htl.cardclubclient.logic;

import at.rennweg.htl.cardclubclient.GameBoard;
import at.rennweg.htl.cardclubclient.cards.Card;

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
        return false;
    }
}
