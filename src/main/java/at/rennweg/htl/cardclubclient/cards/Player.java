package at.rennweg.htl.cardclubclient.cards;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> cards = new ArrayList<>();
    private boolean firstTry = true;

    public Player(Card... cards) {
        this.cards.addAll(List.of(cards));
    }

    public void addCard(Card... cards) {
        this.cards.addAll(List.of(cards));
    }

    public void removeCard(Card card) {
        this.cards.remove(card);
    }

    public Card getCard(int index) {
        return this.cards.get(index);
    }

    public List<Card> getAllCards() {
        return this.cards;
    }

    public boolean getFirstTry() {
        return this.firstTry;
    }

    public void setFirstTry(boolean firstTry) {
        this.firstTry = firstTry;
    }

    public void removeAllCards(){
        this.cards.clear();
    }
    public void addNewCards(List<Card> newCards){
        this.cards = newCards;
    }
}
