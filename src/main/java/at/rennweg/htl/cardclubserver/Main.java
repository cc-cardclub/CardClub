package at.rennweg.htl.cardclubserver;

/**
 * Main class for the CardClub server
 *
 * @author Raven Burkard
 */
public class Main {
    /**
     * Main server method
     *
     * @param args program args
     */
    public static void main(String[] args) {
        final int rateLimitTime = 10000;
        RateLimit.TIMER.schedule(RateLimit.TIMER_TASK, 0, rateLimitTime);

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
