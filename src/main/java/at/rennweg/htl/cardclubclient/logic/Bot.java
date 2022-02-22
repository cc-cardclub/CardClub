package at.rennweg.htl.cardclubclient.logic;

import at.rennweg.htl.cardclubclient.GameBoard;
import at.rennweg.htl.cardclubclient.GameBoardController;
import at.rennweg.htl.cardclubclient.SingleplayerMenuController;
import at.rennweg.htl.cardclubclient.cards.Card;
import at.rennweg.htl.cardclubclient.cards.Deck;
import at.rennweg.htl.cardclubclient.cards.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot extends Player {
    public Bot(Card... cards) {
        super(cards);
    }

    private boolean firstTry = true;

    public void botTurn() {
        Card lastCard = Deck.getLastCard();
        List<Card> botCards = this.getAllCards();
        boolean hasSameColor = false;
        boolean hasSameNumber = false;
        boolean hasWildCard = false;

        for (int i = 0; i < botCards.size(); i++) {
            if (lastCard.getColor().equals(botCards.get(i).getColor())) {
                hasSameColor = true;
                break;
            }
        }

        for (int i = 0; i < botCards.size(); i++) {
            if (lastCard.getNumber().equals(botCards.get(i).getNumber())) {
                hasSameNumber = true;
                break;
            }
        }

        for (int i = 0; i < botCards.size(); i++) {
            if (botCards.get(i).getColor().equals("black")) {
                hasWildCard = true;
                break;
            }
        }

        if (hasSameColor) {
            Deck.playCard(this, getRandomCardWithColor(lastCard.getColor()));
            firstTry = true;
            GameBoard.endBotTurn();
            return;
        }
        if (hasSameNumber) {
            Deck.playCard(this, getRandomCardWithNumber(lastCard.getNumber()));
            firstTry = true;
            GameBoard.endBotTurn();
            return;
        }
        if (hasWildCard) {
            Deck.playCard(this, getRandomCardWithColor("black"), true);
            firstTry = true;
            GameBoard.endBotTurn();
            return;
        }

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

    private Card getRandomCardWithColor(String color) {
        List<Card> cards = this.getAllCards();
        List<Card> colorCards = new ArrayList<>();

        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getColor().equals(color)) {
                colorCards.add(cards.get(i));
            }
        }

        Random random = new Random();
        return colorCards.get(random.nextInt(colorCards.size()));
    }

    private Card getRandomCardWithNumber(String number) {
        List<Card> cards = this.getAllCards();
        List<Card> numberCards = new ArrayList<>();

        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getNumber().equals(number)) {
                numberCards.add(cards.get(i));
            }
        }

        Random random = new Random();
        return numberCards.get(random.nextInt(numberCards.size()));
    }
}
