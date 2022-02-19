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
        Random random = new Random();

        while (cards.size() > 0) {
            int randomCard = random.nextInt(cards.size());

            shuffledCards.add(cards.get(randomCard));
            cards.remove(randomCard);
        }

        return shuffledCards;
    }

    public static void prepareDeck() {
        List<Card> deck = new ArrayList<>();
        String[] colors = {"yellow", "red", "green", "blue", "black"};

        for (String color : colors) {
            deck.addAll(getAllCardsWithColor(color));
        }

        remainingCards = shuffleCards(deck);
        playedCards.add(drawCard());
    }

    private static List<Card> getAllCardsWithColor(String color) {
        List<Card> cards = new ArrayList<>();

        if (color.equals("black")) {
            for (int i = 0; i < 4; i++) {
                cards.add(new Card("wild", color, true));
                cards.add(new Card("wildDraw4", color, true));
            }
        } else {
            for (int i = 0; i < 10; i++) {
                cards.add(new Card(i + "", color, false));
            }
            for (int i = 1; i < 10; i++) {
                cards.add(new Card(i + "", color, false));
            }

            for (int i = 0; i < 2; i++) {
                cards.add(new Card("draw2", color, true));
                cards.add(new Card("reverse", color, true));
                cards.add(new Card("skip", color, true));
            }
        }

        return cards;
    }
}
