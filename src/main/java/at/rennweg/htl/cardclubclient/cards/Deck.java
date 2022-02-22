package at.rennweg.htl.cardclubclient.cards;

import at.rennweg.htl.cardclubclient.logic.Checker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private static List<Card> remainingCards = new ArrayList<>();
    private static List<Card> playedCards = new ArrayList<>();

    public static void playCard(Player playerCards, Card card) {
        if (Checker.checkTurnValidity(card, getLastCard())) {
            playedCards.add(card);
            playerCards.removeCard(card);
        } else {
            playerCards.addCard(card);
            playerCards.addCard(getCards(2));
        }
    }

    public static void playCard(Player playerCards, Card card, Boolean automaticColor) {
        if (Checker.checkTurnValidity(card, getLastCard())) {
            playedCards.add(card);
            playerCards.removeCard(card);
            if (automaticColor) {
                if (card.getColor().equals("black")) {
                    Random random = new Random();
                    int color = random.nextInt(4);
                    switch (color) {
                        case 0 -> getLastCard().setColor("blue");
                        case 1 -> getLastCard().setColor("green");
                        case 2 -> getLastCard().setColor("yellow");
                        case 3 -> getLastCard().setColor("red");
                    }
                }
            }
        } else {
            playerCards.addCard(card);
            playerCards.addCard(getCards(2));
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
            List<Card> shuffleThis = new ArrayList<>();

            for (int i = 0; i < playedCards.size() - 1; i++) {
                shuffleThis.add(playedCards.get(0));
                playedCards.remove(0);
            }

            remainingCards.addAll(shuffleCards(shuffleThis));
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

            if (cards.get(randomCard).getNumber().equals("wild")
                    || cards.get(randomCard).getNumber().equals("wildDraw4")) {
                cards.get(randomCard).setColor("black");
            }

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

    public static Card[] getPlayerStartCards() {
        final int startCardAmount = 7;
        return getCards(startCardAmount);
    }

    public static Card[] getCards(int amount) {
        Card[] cards = new Card[amount];

        for (int i = 0; i < amount; i++) {
            cards[i] = drawCard();
        }

        return cards;
    }
}
