package at.rennweg.htl.cardclubclient.cards;

import at.rennweg.htl.cardclubclient.GameBoard;
import at.rennweg.htl.cardclubclient.logic.Checker;
import at.rennweg.htl.cardclubclient.logic.GameCore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private static List<Card> remainingCards = new ArrayList<>();
    private static List<Card> playedCards = new ArrayList<>();
    private static int drawAmount = 0;

    public static void playCard(Player playerCards, Card card) {
        if (Checker.checkTurnValidity(card, getLastCard())) {
            doTurn(playerCards, card);
        } else {
            playerCards.addCard(card);
            playerCards.addCard(getCards(2));
        }
    }

    public static void playCard(Player playerCards, Card card, String color) {
        if (Checker.checkTurnValidity(card, getLastCard())) {
            doTurn(playerCards, card);
            if (card.getColor().equals("black")) {
                if (color.equals("black")) {
                    Random random = new Random();
                    switch (random.nextInt(4)) {
                        case 0 -> getLastCard().setColor("yellow");
                        case 1 -> getLastCard().setColor("blue");
                        case 2 -> getLastCard().setColor("green");
                        case 3 -> getLastCard().setColor("red");
                    }
                } else {
                    getLastCard().setColor(color);
                }
            }
        } else {
            playerCards.addCard(card);
            playerCards.addCard(getCards(2));
        }
    }

    private static void doTurn(Player playerCards, Card card) {
        if (getLastCard().getNumber().equals("draw2")) {
            if (!card.getNumber().equals("draw2")) {
                playerCards.addCard(getCards(drawAmount));
                GameBoard.refresh();
                drawAmount = 0;
            }
        }
        if (getLastCard().getNumber().equals("wildDraw4")) {
            if (!card.getNumber().equals("wildDraw4")) {
                playerCards.addCard(getCards(drawAmount));
                GameBoard.refresh();
                drawAmount = 0;
            }
        }
        playedCards.add(card);
        playerCards.removeCard(card);
        if (card.isSpecial()) {
            if (card.getNumber().equals("skip")) {
                GameCore.switchToNextPlayer();
            } else if (card.getNumber().equals("draw2")) {
                drawAmount += 2;
            } else if (card.getNumber().equals("wildDraw4")) {
                drawAmount += 4;
            }
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
        return getCards(GameCore.getStartingCards());
    }

    public static Card[] getCards(int amount) {
        Card[] cards = new Card[amount];

        for (int i = 0; i < amount; i++) {
            cards[i] = drawCard();
        }

        return cards;
    }
}
