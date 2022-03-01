package at.rennweg.htl.cardclubclient.logic;

import at.rennweg.htl.cardclubclient.cards.Player;

import java.util.ArrayList;
import java.util.List;

public class GameCore {
    private static List<Player> players = new ArrayList<>();
    private static int startingCards = 7;
    private static boolean gameFinished;
    private static int currentPlayerID = 0;
    private static int turnDuration = 20;

    public static Player getPlayer(int index) {
        return players.get(index);
    }

    public static void addPlayer(Player player) {
        GameCore.players.add(player);
    }

    public static void setPlayers(Player... players) {
        GameCore.players = List.of(players);
    }

    public static void setStartingCards(int startingCards) {
        GameCore.startingCards = startingCards;
    }

    public static int getStartingCards() {
        return startingCards;
    }

    public static void setGameFinished(boolean gameFinished) {
        GameCore.gameFinished = gameFinished;
    }

    public static boolean isGameFinished() {
        return gameFinished;
    }

    public static Player getCurrentPlayer() {
        return players.get(currentPlayerID);
    }

    public static Player getNextPlayer() {
        return players.get(getNextPlayerID());
    }

    public static void switchToNextPlayer() {
        if (currentPlayerID + 1 < players.size()) {
            currentPlayerID++;
        } else {
            currentPlayerID = 0;
        }
    }

    public static int getCurrentPlayerID() {
        return currentPlayerID;
    }

    public static int getNextPlayerID() {
        if (currentPlayerID + 1 < players.size()) {
            return currentPlayerID + 1;
        } else {
            return 0;
        }
    }

    public static void setTurnDuration(int sec) {
        GameCore.turnDuration = sec;
    }

    public static int getTurnDuration() {
        return GameCore.turnDuration;
    }

    public static boolean plus2and4CardsSelected = false;
}
