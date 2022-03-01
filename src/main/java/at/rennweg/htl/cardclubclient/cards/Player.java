package at.rennweg.htl.cardclubclient.cards;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> cards = new ArrayList<>();

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

}
