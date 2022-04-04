package at.rennweg.htl.cardclubserver;

public class User {
    private String name;
    private String id;
    private int wins;
    private int losses;

    public String getId() {
        return id;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public String getName() {
        return name;
    }

    public User(String name, String id, int wins, int losses) {
        this.name = name;
        this.id = id;
        this.wins = wins;
        this.losses = losses;
    }

    public User(String request) {
        this.name = request.substring(request.indexOf("<cc-username-start>") + "<cc-username-start>".length(),
                request.indexOf("<cc-username-end>"));
        this.id = request.substring(request.indexOf("<cc-id-start>") + "<cc-id-start>".length(),
                request.indexOf("<cc-id-end>"));
        this.wins = Integer.parseInt(request.substring(request.indexOf("<cc-wins-start>") + "<cc-wins-start>".length(),
                request.indexOf("<cc-wins-end>")));
        this.losses = Integer.parseInt(request.substring(request.indexOf("<cc-losses-start>") + "<cc-losses-start>".length(),
                request.indexOf("<cc-losses-end>")));
    }

    public User(String request, boolean shortRequest) {
        if (shortRequest) {
            this.name = request.substring(request.indexOf("<cc-username-start>") + "<cc-username-start>".length(),
                    request.indexOf("<cc-username-end>"));
            this.id = request.substring(request.indexOf("<cc-id-start>") + "<cc-id-start>".length(),
                    request.indexOf("<cc-id-end>"));
        }
    }
}
