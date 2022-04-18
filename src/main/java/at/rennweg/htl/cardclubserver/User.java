package at.rennweg.htl.cardclubserver;

/**
 * Class for user data objects
 *
 * @author Mattias Burkard
 */
public class User {
    /**
     * Name of the user
     */
    private String name;

    /**
     * ID of the user
     */
    private String id;

    /**
     * Wins of the user
     */
    private int wins;

    /**
     * Losses of the user
     */
    private int losses;

    /**
     * Get the id of the user
     *
     * @return user id
     */
    public String getId() {
        return id;
    }

    /**
     * Get the wins of a user
     *
     * @return wins
     */
    public int getWins() {
        return wins;
    }

    /**
     * Get the losses of a user
     *
     * @return losses
     */
    public int getLosses() {
        return losses;
    }

    /**
     * Get the name of a user
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Create a new user
     *
     * @param name   username
     * @param id     user id
     * @param wins   wins of the user
     * @param losses losses of the user
     */
    public User(String name, String id, int wins, int losses) {
        this.name = name;
        this.id = id;
        this.wins = wins;
        this.losses = losses;
    }

    /**
     * Create a user from a request
     *
     * @param request client request string
     */
    public User(String request) {
        this.name = request.substring(request.indexOf("<cc-username-start>")
                        + "<cc-username-start>".length(),
                request.indexOf("<cc-username-end>"));
        this.id = request.substring(request.indexOf("<cc-id-start>") + "<cc-id-start>".length(),
                request.indexOf("<cc-id-end>"));
        this.wins = Integer.parseInt(request.substring(request.indexOf("<cc-wins-start>")
                        + "<cc-wins-start>".length(),
                request.indexOf("<cc-wins-end>")));
        this.losses = Integer.parseInt(request.substring(request.indexOf("<cc-losses-start>")
                        + "<cc-losses-start>".length(),
                request.indexOf("<cc-losses-end>")));
    }

    /**
     * Create a user with fewer parameters
     *
     * @param request      client string request
     * @param shortRequest whether this is a short request
     */
    public User(String request, boolean shortRequest) {
        if (shortRequest) {
            this.name = request.substring(request.indexOf("<cc-username-start>")
                            + "<cc-username-start>".length(),
                    request.indexOf("<cc-username-end>"));
            this.id = request.substring(request.indexOf("<cc-id-start>") + "<cc-id-start>".length(),
                    request.indexOf("<cc-id-end>"));
        }
    }
}
