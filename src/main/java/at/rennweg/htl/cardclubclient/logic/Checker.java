package at.rennweg.htl.cardclubclient.logic;

import at.rennweg.htl.cardclubclient.cards.Card;

public class Checker {
    public static boolean checkTurnValidity(Card currentCard, Card previousCard) {
        // TODO check if special
        if (currentCard.getNumber() == previousCard.getNumber()) {
            return true;
        }
        if (currentCard.getColor().equals(previousCard.getColor())) {
            return true;
        }
        return false;
    }
}
