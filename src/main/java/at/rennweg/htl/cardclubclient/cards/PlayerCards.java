package at.rennweg.htl.cardclubclient.cards;

import java.util.ArrayList;
import java.util.List;

public class PlayerCards {
    private List<Card> cards = new ArrayList<>();

    public PlayerCards(Card... cards) {
        this.cards.addAll(List.of(cards));
    }

    public void addCard(Card card) {
        this.cards.add(card);
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
}
