package at.rennweg.htl.cardclubclient.cards;

import at.rennweg.htl.cardclubclient.GameBoard;
import at.rennweg.htl.cardclubclient.logic.Bot;
import at.rennweg.htl.cardclubclient.logic.Checker;
import at.rennweg.htl.cardclubclient.logic.GameCore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Class containing the card deck for the game
 *
 * @author Raven Burkard
 */
public class Deck {
    /**
     * Remaining cards in the deck
     */
    private static List<Card> remainingCards = new ArrayList<>();

    /**
     * Already played cards
     */
    private static final List<Card> PLAYED_CARDS = new ArrayList<>();

    /**
     * Amount of cards that the next player needs to draw
     */
    private static int drawAmount = 0;

    /**
     * Make the player draw cards if needed
     */
    public static void drawNeeded() {
        boolean hasSameNumber = false;

        for (Card card : GameCore.getCurrentPlayer().getAllCards()) {
            if (Objects.requireNonNull(getLastCard()).getNumber().equals(card.getNumber())) {
                hasSameNumber = true;
                break;
            }
        }

        if (!hasSameNumber) {
            GameCore.getCurrentPlayer().addCard(Deck.getCards(drawAmount));
            drawAmount = 0;
        }
    }

    /**
     * Method for playing a card
     *
     * @param playerCards all cards which the player has
     * @param card        card that the player wants to play
     */
    public static void playCard(Player playerCards, Card card) {
        if (Checker.checkTurnValidity(card, getLastCard())) {
            doTurn(playerCards, card);
        } else {
            playerCards.addCard(card);
            playerCards.addCard(getCards(2));
        }
    }

    /**
     * Method for playing a card and selecting a color
     *
     * @param playerCards all cards which the player has
     * @param card        card that the player wants to play
     * @param color       color which should be chosen if the card was a wild card
     */
    public static void playCard(Player playerCards, Card card, String color) {
        if (Checker.checkTurnValidity(card, getLastCard())) {
            doTurn(playerCards, card);
            if (card.getColor().equals("black")) {
                if (color.equals("black")) {
                    final int colorAmount = 4;
                    final int firstCase = 0;
                    final int secondCase = 1;
                    final int thirdCase = 2;
                    final int fourthCase = 3;

                    Random random = new Random();

                    switch (random.nextInt(colorAmount)) {
                        case firstCase -> Objects.requireNonNull(getLastCard()).setColor("yellow");
                        case secondCase -> Objects.requireNonNull(getLastCard()).setColor("blue");
                        case thirdCase -> Objects.requireNonNull(getLastCard()).setColor("green");
                        case fourthCase -> Objects.requireNonNull(getLastCard()).setColor("red");
                        default -> Objects.requireNonNull(getLastCard()).setColor("black");
                    }
                } else {
                    Objects.requireNonNull(getLastCard()).setColor(color);
                }
            }
        } else {
            playerCards.addCard(card);
            playerCards.addCard(getCards(2));
        }
    }

    private static void doTurn(Player playerCards, Card card) {
        if (Objects.requireNonNull(getLastCard()).getNumber().equals("draw2")) {
            if (GameCore.plus2and4CardsSelected) {
                if (!card.getNumber().equals("draw2") && !card.getNumber().equals("wildDraw4")) {
                    playerCards.addCard(getCards(drawAmount));
                    GameBoard.refresh();
                    drawAmount = 0;
                }
            } else {
                if (!card.getNumber().equals("draw2")) {
                    playerCards.addCard(getCards(drawAmount));
                    GameBoard.refresh();
                    drawAmount = 0;
                }
            }
        }
        if (getLastCard().getNumber().equals("wildDraw4")) {
            if (GameCore.plus2and4CardsSelected) {
                if (!card.getNumber().equals("draw2") && !card.getNumber().equals("wildDraw4")) {
                    playerCards.addCard(getCards(drawAmount));
                    GameBoard.refresh();
                    drawAmount = 0;
                }
            } else {
                if (!card.getNumber().equals("wildDraw4")) {
                    playerCards.addCard(getCards(drawAmount));
                    GameBoard.refresh();
                    drawAmount = 0;
                }
            }
        }
        PLAYED_CARDS.add(card);
        playerCards.removeCard(card);
        if (card.isSpecial()) {
            switch (card.getNumber()) {
                case "skip":
                    GameCore.switchToNextPlayer();
                    break;
                case "draw2":
                    drawAmount += 2;
                    break;
                case "wildDraw4":
                    final int draw4Amount = 4;
                    drawAmount += draw4Amount;
                    break;
                case "reverse":
                    if (GameCore.getPlayers().size() == 2) {
                        GameCore.switchToNextPlayer();
                    } else {
                        GameCore.reverseDirection();
                    }
                    break;
                default:
                    break;
            }
        }

        if (GameCore.cardsSwitchingInPlayingDirectory && card.getNumber().equals("1")) {
            List<Card> tempCards;
            List<Card> tempCards1;
            //player ID 0
            int playerID = 0;
            tempCards = new ArrayList<>(GameCore.getPlayer(playerID).getAllCards());
            GameCore.getPlayer(playerID).replaceCards(GameCore.getPlayer(GameCore.getPlayers().size() - 1).getAllCards());
            //Bot 1 ID 1
            playerID++;
            tempCards1 = new ArrayList<>(GameCore.getPlayer(playerID).getAllCards());
            GameCore.getPlayer(playerID).replaceCards(tempCards);
            if (GameCore.getPlayers().size() > 2) {
                //Bot 2 ID 2
                playerID++;
                tempCards = new ArrayList<>(GameCore.getPlayer(playerID).getAllCards());
                GameCore.getPlayer(playerID).replaceCards(tempCards1);
            }
            if (GameCore.getPlayers().size() > 3) {
                //Bot 3 ID 3
                playerID++;
                GameCore.getPlayer(playerID).replaceCards(tempCards);
            }
        }

        if (GameCore.switchCardsWithPlayer
                && GameCore.getCurrentPlayer() instanceof Bot
                && card.getNumber().equals("7")) {
            List<Player> players = GameCore.getPlayers();

            int leastCards = 0;
            Player leastCardsPlayer = null;

            for (Player player : players) {
                if (leastCards == 0) {
                    leastCards = player.getAllCards().size();
                    leastCardsPlayer = player;
                } else if (leastCards > player.getAllCards().size()) {
                    leastCards = player.getAllCards().size();
                    leastCardsPlayer = player;
                }
            }

            GameCore.chosenPlayerSwitchCards = leastCardsPlayer;

            List<Card> tempCards = new ArrayList<>(GameCore.getCurrentPlayer().getAllCards());
            GameCore.getCurrentPlayer().replaceCards(GameCore.chosenPlayerSwitchCards.getAllCards());
            GameCore.chosenPlayerSwitchCards.replaceCards(tempCards);
        }

        if (GameCore.getPlayers().size() == 2) {
            if (card.getNumber().equals("skip") || card.getNumber().equals("reverse")) {
                GameCore.getCurrentPlayer().setFirstTry(true);
            }
        }
    }

    /**
     * Method for getting the last played card
     *
     * @return the last played card
     */
    public static Card getLastCard() {
        if (PLAYED_CARDS.size() == 0) {
            return null;
        }
        return PLAYED_CARDS.get(PLAYED_CARDS.size() - 1);
    }

    /**
     * Method for drawing a card
     *
     * @return the next available card
     */
    public static Card drawCard() {
        if (remainingCards.size() < 1) {
            List<Card> shuffleThis = new ArrayList<>();

            for (int i = 0; i < PLAYED_CARDS.size() - 1; i++) {
                shuffleThis.add(PLAYED_CARDS.get(0));
                PLAYED_CARDS.remove(0);
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

    /**
     * Method for preparing a new deck and adding all cards to it
     */
    public static void prepareDeck() {
        List<Card> deck = new ArrayList<>();
        String[] colors = {"yellow", "red", "green", "blue", "black"};

        for (String color : colors) {
            deck.addAll(getAllCardsWithColor(color));
        }

        remainingCards = shuffleCards(deck);
        PLAYED_CARDS.add(drawCard());
    }

    private static List<Card> getAllCardsWithColor(String color) {
        List<Card> cards = new ArrayList<>();
        final int wildCardAmount = 4;
        final int numberCards = 10;

        if (color.equals("black")) {
            for (int i = 0; i < wildCardAmount; i++) {
                cards.add(new Card("wild", color, true));
                cards.add(new Card("wildDraw4", color, true));
            }
        } else {
            for (int i = 0; i < numberCards; i++) {
                cards.add(new Card(i + "", color, false));
            }
            for (int i = 1; i < numberCards; i++) {
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

    /**
     * Method for getting the starting cards for a player
     *
     * @return an array of cards for the player
     */
    public static Card[] getPlayerStartCards() {
        return getCards(GameCore.getStartingCards());
    }

    /**
     * Method for getting a specific amount of cards
     *
     * @param amount amount of cards to get
     * @return an array of cards for the player
     */
    public static Card[] getCards(int amount) {
        Card[] cards = new Card[amount];

        for (int i = 0; i < amount; i++) {
            cards[i] = drawCard();
        }

        return cards;
    }
}
