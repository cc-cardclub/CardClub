package at.rennweg.htl.cardclubserver;

/**
 * Request types
 *
 * @author Raven Burkard
 */
public enum RequestType {
    /**
     * Get leaderboard entries
     */
    get,

    /**
     * Add a win to the leaderboard
     */
    addWin,

    /**
     * Add a loss to the leaderboard
     */
    addLoss
}
