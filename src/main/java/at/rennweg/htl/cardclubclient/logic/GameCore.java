package at.rennweg.htl.cardclubclient.logic;

import at.rennweg.htl.cardclubclient.cards.Player;

import java.util.ArrayList;
import java.util.List;

public class GameCore {
    private static List<Player> players = new ArrayList<>();
    private static int startingCards;
    private static boolean gameFinished;
    private static int currentPlayerID;
    private static int turnDuration;
    private static boolean clockwiseTurn;
    public static boolean plus2and4CardsSelected;
    public static boolean pauseProgressBar;

    public static void reverseDirection() {
        clockwiseTurn = !clockwiseTurn;
    }

    public static Player getPlayer(int index) {
        return players.get(index);
    }

    public static void addPlayer(Player player) {
        GameCore.players.add(player);
    }

    public static void setPlayers(Player... players) {
        GameCore.players = List.of(players);
    }

    public static List<Player> getPlayers() {
        return players;
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
        if (clockwiseTurn) {
            if (currentPlayerID + 1 < players.size()) {
                currentPlayerID++;
            } else {
                currentPlayerID = 0;
            }
        } else {
            if (currentPlayerID > 0) {
                currentPlayerID--;
            } else {
                currentPlayerID = players.size() - 1;
            }
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

    public static void reset() {
        players = new ArrayList<>();
        startingCards = 7;
        gameFinished = false;
        currentPlayerID = 0;
        turnDuration = 20;
        clockwiseTurn = true;
        plus2and4CardsSelected = false;
        pauseProgressBar = false;
    }

}
