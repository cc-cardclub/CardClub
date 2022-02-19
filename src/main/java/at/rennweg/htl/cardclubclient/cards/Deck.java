package at.rennweg.htl.cardclubclient.cards;

import at.rennweg.htl.cardclubclient.logic.Checker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private static List<Card> remainingCards = new ArrayList<>();
    private static List<Card> playedCards = new ArrayList<>();

    public static void playCard(Card card) {
        if (Checker.checkTurnValidity(card, getLastCard())) {
            playedCards.add(card);
        } else {
            // TODO give player two cards
        }
    }

    public static Card getLastCard() {
        if (playedCards.size() == 0) {
            return null;
        }
        return playedCards.get(playedCards.size() - 1);
    }

    public static Card drawCard() {
        if (remainingCards.size() < 1) {
            // TODO leave last played card
            shuffleCards(playedCards);
        }

        Card drawnCard = remainingCards.get(0);
        remainingCards.remove(drawnCard);

        return drawnCard;
    }

    private static List<Card> shuffleCards(List<Card> cards) {
        List<Card> shuffledCards = new ArrayList<>();
        while (cards.size() > 0) {
            // TODO actually shuffle
            Random random = new Random();
            random.nextInt();
        }

        return shuffledCards;
    }
}
