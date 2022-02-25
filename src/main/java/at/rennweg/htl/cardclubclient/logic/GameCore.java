package at.rennweg.htl.cardclubclient.logic;

import at.rennweg.htl.cardclubclient.cards.Player;

import java.util.ArrayList;
import java.util.List;

public class GameCore {
    private static List<Player> players = new ArrayList<>();
    private static int startingCards = 7;

    public static Player getPlayer(int index) {
        return players.get(index);
    }

    public static void addPlayer(Player player) {
        GameCore.players.add(player);
    }

    public static void setPlayers(Player... players) {
        GameCore.players = List.of(players);
    }

    public static void setStartingCards(int startingCards){
        GameCore.startingCards = startingCards;
    }

    public static int getStartingCards() {
        return startingCards;
    }
}
