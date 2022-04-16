package at.rennweg.htl.cardclubclient.cards;

import java.util.ArrayList;
import java.util.List;

/**
 * Player class<br>
 * Represents a player
 *
 * @author Bernd Reither, Lisa-Marie Hörmann, Mattias Burkard
 */
public class Player {
    /**
     * The cards
     */
    private List<Card> cards = new ArrayList<>();
    /**
     * Is it the players first try
     */
    private boolean firstTry = true;

    /**
     * Initialise the player with a list of cards
     *
     * @param cards The cards
     */
    public Player(Card... cards) {
        this.cards.addAll(List.of(cards));
    }

    /**
     * Add cards to the players list
     *
     * @param cards The cards
     */
    public void addCard(Card... cards) {
        this.cards.addAll(List.of(cards));
    }

    /**
     * Remove a cards from the players list
     *
     * @param card The card
     */
    public void removeCard(Card card) {
        this.cards.remove(card);
    }

    /**
     * Get a card from the players list
     *
     * @param index The index of the card
     * @return The card
     */
    public Card getCard(int index) {
        return this.cards.get(index);
    }

    /**
     * Get all cards from the players list
     *
     * @return The list of cards
     */
    public List<Card> getAllCards() {
        return this.cards;
    }

    /**
     * Is it the players first try
     *
     * @return True/false whether is it the players first try or not
     */
    public boolean getFirstTry() {
        return this.firstTry;
    }

    /**
     * Set if it is the players first try or not
     *
     * @param firstTry Is the players first try or not
     */
    public void setFirstTry(boolean firstTry) {
        this.firstTry = firstTry;
    }

    /**
     * Remove all cards from the players list
     */
    public void removeAllCards() {
        this.cards.clear();
    }

    /**
     * Replace the list of cards
     *
     * @param newCards The new cards
     */
    public void replaceCards(List<Card> newCards) {
        this.cards = newCards;
    }
}
