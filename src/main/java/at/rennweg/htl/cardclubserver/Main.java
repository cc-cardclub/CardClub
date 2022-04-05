package at.rennweg.htl.cardclubserver;

import at.rennweg.htl.cardclubclient.logic.ServerConnection;

/**
 * Main class for the CardClub server
 *
 * @author Mattias Burkard
 */
public class Main {
    /**
     * Main server method
     *
     * @param args program args
     */
    public static void main(String[] args) {
        RateLimit.timer.schedule(RateLimit.resetRate, 0, 10000);

        for (String arg : args) {
            if (arg.startsWith("databaseUrl:")) {
                Leaderboard.databaseUrl = arg.substring("databaseUrl:".length());
            } else if (arg.startsWith("databaseUser:")) {
                Leaderboard.databaseUser = arg.substring("databaseUser:".length());
            } else if (arg.startsWith("databasePassword:")) {
                Leaderboard.databasePassword = arg.substring("databasePassword:".length());
            }
        }

        Server.server();
    }
}
