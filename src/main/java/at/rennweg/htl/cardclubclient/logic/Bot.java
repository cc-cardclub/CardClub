package at.rennweg.htl.cardclubclient.logic;

import at.rennweg.htl.cardclubclient.GameBoard;
import at.rennweg.htl.cardclubclient.cards.Card;
import at.rennweg.htl.cardclubclient.cards.Deck;
import at.rennweg.htl.cardclubclient.cards.Player;


import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * This class gives bots all of their needed functions to compete against players
 *
 * @author Mattias Burkard
 */
public class Bot extends Player {
    /**
     * Initialize a new bot
     *
     * @param cards cards for the bot
     */
    public Bot(Card... cards) {
        super(cards);
    }

    /**
     * Whether the bot can still draw a card in this turn or if it already did that
     */
    private boolean firstTry = true;

    /**
     * Function for the bot to make its turn
     */
    public void botTurn() {
        Card lastCard = Deck.getLastCard();
        List<Card> botCards = this.getAllCards();
        boolean hasSameColor = false;
        boolean hasSameNumber = false;
        boolean hasWildCard = false;

        // Enable the bot to play a random card, if the first ever card was a wild card
        if (lastCard.getColor().equals("black")) {
            Random random = new Random();
            Deck.playCard(this, botCards.get(random.nextInt(botCards.size())));
            firstTry = true;
            GameBoard.endBotTurn();
            return;
        }

        // Check if the bot has a card of the same color as the last played one
        for (Card botCard : botCards) {
            if (lastCard.getColor().equals(botCard.getColor())) {
                hasSameColor = true;
                break;
            }
        }

        // Check if the bot has a card with the same number as the last played one
        for (Card botCard : botCards) {
            if (lastCard.getNumber().equals(botCard.getNumber())) {
                hasSameNumber = true;
                break;
            }
        }

        // Check if the bot has a wild card
        for (Card botCard : botCards) {
            if (botCard.getColor().equals("black")) {
                hasWildCard = true;
                break;
            }
        }

        String color = getColorWithMostCards();

        // IF the last card would make the bot have to draw cards, it'll try to prevent that
        if (lastCard.getNumber().equals("draw2") || lastCard.getNumber().equals("wildDraw4")) {
            if (hasSameNumber) {
                if (GameCore.botDifficulty.equals("Easy")) {
                    Random random = new Random();
                    if (random.nextInt(100) > 50) {
                        Deck.playCard(this, getRandomCardWithNumber(lastCard.getNumber()), color);
                        firstTry = true;
                        GameBoard.endBotTurn();
                        return;
                    }
                } else if (GameCore.botDifficulty.equals("Medium")) {
                    Random random = new Random();
                    if (random.nextInt(100) > 75) {
                        Deck.playCard(this, getRandomCardWithNumber(lastCard.getNumber()), color);
                        firstTry = true;
                        GameBoard.endBotTurn();
                        return;
                    }
                } else if (GameCore.botDifficulty.equals("Hard")) {
                    Deck.playCard(this, getRandomCardWithNumber(lastCard.getNumber()), color);
                    firstTry = true;
                    GameBoard.endBotTurn();
                    return;
                }
            }
        }

        // Play a card of the same color
        if (hasSameColor) {
            Deck.playCard(this, getRandomCardWithColor(lastCard.getColor()));
            firstTry = true;
            GameBoard.endBotTurn();
            return;
        }

        // Play a card with the same number
        if (hasSameNumber) {
            Deck.playCard(this, getRandomCardWithNumber(lastCard.getNumber()));
            firstTry = true;
            GameBoard.endBotTurn();
            return;
        }

        // Play a wild card and set the color to the one, which it has the most cards of
        if (hasWildCard) {
            Deck.playCard(this, getRandomCardWithColor("black"), color);
            firstTry = true;
            GameBoard.endBotTurn();
            return;
        }

        // Draw a card if it hasn't done that yet and then try again
        if (firstTry) {
            this.addCard(Deck.drawCard());
            firstTry = false;
            GameBoard.refresh();
            this.botTurn();
        } else {
            firstTry = true;
            GameBoard.endBotTurn();
        }
    }

    /**
     * Get a random card of a given color
     *
     * @param color color to return a card of
     * @return random card
     */
    private Card getRandomCardWithColor(String color) {
        // Get the cards of the bot
        List<Card> cards = this.getAllCards();
        List<Card> colorCards = new ArrayList<>();

        // Add all cards wit the given color to a list
        for (Card card : cards) {
            if (card.getColor().equals(color)) {
                colorCards.add(card);
            }
        }

        // Return a random one of the possible cards
        Random random = new Random();
        return colorCards.get(random.nextInt(colorCards.size()));
    }

    /**
     * Get a random card with a given number
     *
     * @param number number to get a card with
     * @return random card
     */
    private Card getRandomCardWithNumber(String number) {
        // Get the cards of the bot
        List<Card> cards = this.getAllCards();
        List<Card> numberCards = new ArrayList<>();

        // Add all cards wit the given number to a list
        for (Card card : cards) {
            if (card.getNumber().equals(number)) {
                numberCards.add(card);
            }
        }

        // Return a random one of the possible cards
        Random random = new Random();
        return numberCards.get(random.nextInt(numberCards.size()));
    }

    /**
     * Gets the color which the bot has the most cards of
     *
     * @return color with the most cards
     */
    private String getColorWithMostCards() {
        return getAllCards().stream()
                .filter(cardColor -> Objects.nonNull(cardColor.getColor()))
                .collect(Collectors.groupingBy(Card::getColor, Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(null);
    }

    /**
     * Make the bot press the uno button
     */
    public void unoButtonPress() {
        Random random = new Random();
        final int fullPercentage = 100;
        int failThreshold = switch (GameCore.botDifficulty) {
            case "Easy" -> 75;
            case "Medium" -> 85;
            case "Hard" -> 95;
            default -> 100;
        };


        if (random.nextInt(fullPercentage) + 1 > failThreshold) {
            GameCore.getCurrentPlayer().addCard(Deck.getCards(2));
        }
    }
}
