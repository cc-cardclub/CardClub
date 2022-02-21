package at.rennweg.htl.cardclubclient.logic;

import at.rennweg.htl.cardclubclient.cards.PlayerCards;

import java.util.ArrayList;
import java.util.List;

public class GameCore {
    private static List<PlayerCards> players = new ArrayList<>();

    public static PlayerCards getPlayer(int index) {
        return players.get(index);
    }

    public static void addPlayer(PlayerCards player) {
        GameCore.players.add(player);
    }

    public static void setPlayers(PlayerCards... players) {
        GameCore.players = List.of(players);
    }
}
