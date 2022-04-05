package at.rennweg.htl.cardclubclient.logic;

import at.rennweg.htl.cardclubclient.cards.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to store information about a game
 *
 * @author Bernd Reither, Mattias Burkard, Lisa-Marie Hörmann
 */
public class GameCore {
    /**
     * Used to store the players (cards)
     */
    private static List<Player> players = new ArrayList<>();
    /**
     * Used to store the amount of starting cards
     */
    private static int startingCards;
    /**
     * Used to store if the game is finished or not
     */
    private static boolean gameFinished;
    /**
     * Used to store the current player ID
     */
    private static int currentPlayerID;
    /**
     * Used to store the turn duration
     */
    private static int turnDuration;
    /**
     * Used to store if the game uses clockwise turns or not
     */
    private static boolean clockwiseTurn;
    /**
     * Used to store if the rule "+2 und +4" is selected
     */
    public static boolean plus2and4CardsSelected;
    /**
     * Used to store if the rule "Karte 1" is selected
     */
    public static boolean cardsSwitchingInPlayingDirectory;
    /**
     * Used to store if the progressBar/timer should be paused
     */
    public static boolean pauseProgressBar;
    /**
     * Used to store the bot difficulty
     */
    public static String botDifficulty = "Medium";

    public static boolean switchCardsWithPlayer;

    public static Player chosenPlayerSwitchCards;

    public static boolean isValid = false;

    /**
     * Reverse the playing direction
     */
    public static void reverseDirection() {
        clockwiseTurn = !clockwiseTurn;
    }

    /**
     * Get a player using his id
     *
     * @param index The id
     * @return The player
     */
    public static Player getPlayer(int index) {
        return players.get(index);
    }

    /**
     * Add a player to the game
     *
     * @param player The player
     */
    public static void addPlayer(Player player) {
        GameCore.players.add(player);
    }

    /**
     * Set the players of a game (list)
     *
     * @param players The players
     */
    public static void setPlayers(Player... players) {
        GameCore.players = List.of(players);
    }

    /**
     * Get all players of a game
     *
     * @return The players
     */
    public static List<Player> getPlayers() {
        return players;
    }

    /**
     * Set the amount of starting cards
     *
     * @param startingCards The amount of starting cards
     */
    public static void setStartingCards(int startingCards) {
        GameCore.startingCards = startingCards;
    }

    /**
     * Get the amount of starting cards
     *
     * @return The amount of starting cards
     */
    public static int getStartingCards() {
        return startingCards;
    }

    /**
     * Set if the game is finished or not
     *
     * @param gameFinished True/false whether the game is finished or not
     */
    public static void setGameFinished(boolean gameFinished) {
        GameCore.gameFinished = gameFinished;
    }

    /**
     * Check if the game is finished
     *
     * @return True/false whether the game is finished or not
     */
    public static boolean isGameFinished() {
        return gameFinished;
    }

    /**
     * Get the current player
     *
     * @return Current player
     */
    public static Player getCurrentPlayer() {
        return players.get(currentPlayerID);
    }

    /**
     * Get the current player as a bot
     *
     * @return the bot
     */
    public static Bot getCurrentBot() {
        if (getCurrentPlayer() instanceof Bot) {
            return (Bot) getCurrentPlayer();
        } else {
            return new Bot();
        }
    }

    /**
     * Get the next player
     *
     * @return Next player
     */
    public static Player getNextPlayer() {
        return players.get(getNextPlayerID());
    }

    /**
     * Switch to the next player
     */
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

    /**
     * Get the id of the current player
     *
     * @return id of the current player
     */
    public static int getCurrentPlayerID() {
        return currentPlayerID;
    }

    /**
     * Get the id of the next player
     *
     * @return id of the next player
     */
    public static int getNextPlayerID() {
        if (currentPlayerID + 1 < players.size()) {
            return currentPlayerID + 1;
        } else {
            return 0;
        }
    }

    /**
     * Set the duration of a turn
     *
     * @param sec duration of a turn in sec
     */
    public static void setTurnDuration(int sec) {
        GameCore.turnDuration = sec;
    }

    /**
     * Get the duration of a turn in sec
     *
     * @return duration of a turn in sec
     */
    public static int getTurnDuration() {
        return GameCore.turnDuration;
    }

    /**
     * Reset all GameCore settings
     */
    public static void reset() {
        final int startingCardsDefault = 7;
        final int turnDurationDefault = 20;

        players = new ArrayList<>();
        startingCards = startingCardsDefault;
        gameFinished = false;
        currentPlayerID = 0;
        turnDuration = turnDurationDefault;
        clockwiseTurn = true;
        plus2and4CardsSelected = false;
        pauseProgressBar = false;
        cardsSwitchingInPlayingDirectory = false;
        switchCardsWithPlayer = false;

        System.out.println("GameCore: Reset");
    }

}
