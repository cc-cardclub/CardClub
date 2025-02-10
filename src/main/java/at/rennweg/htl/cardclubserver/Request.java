package at.rennweg.htl.cardclubserver;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * Class for handling server requests
 *
 * @author Raven Burkard
 */
public class Request {
    /**
     * Type of the request
     */
    private RequestType type;

    /**
     * User which the request should be fulfilled for
     */
    private User user;

    /**
     * How many entries to get
     */
    private int getAmount;

    /**
     * Construct a new request
     *
     * @param request request string sent by the client
     */
    public Request(String request) {
        if (request.startsWith("get")) {
            this.type = RequestType.get;
            this.getAmount = Integer.parseInt(request.substring(
                    request.indexOf("<cc-get-amount-start>") + "<cc-get-amount-start>".length(),
                    request.indexOf("<cc-get-amount-end>")));
        } else if (request.startsWith("addWin")) {
            this.type = RequestType.addWin;
            user = new User(request, true);
        } else if (request.startsWith("addLoss")) {
            this.type = RequestType.addLoss;
            user = new User(request, true);
        }
    }

    /**
     * Handle the request
     *
     * @param socket connection to the client
     */
    public void handle(Socket socket) {
        try {
            if (type.equals(RequestType.get)) {
                final int sleepDuration = 1000;
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                while (RateLimit.addresses.contains(socket.getInetAddress())) {
                    try {
                        Thread.sleep(sleepDuration);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                RateLimit.addresses.add(socket.getInetAddress());

                List<User> users = Leaderboard.getLeaderboard(getAmount);

                for (User user : users) {
                    writer.println(
                            "<cc-lb><cc-username-start>" + user.getName() + "<cc-username-end>"
                                    + "<cc-id-start>" + user.getId() + "<cc-id-end>"
                                    + "<cc-wins-start>" + user.getWins() + "<cc-wins-end>"
                                    + "<cc-losses-start>" + user.getLosses() + "<cc-losses-end>");
                }
                writer.println("close");
            } else if (type.equals(RequestType.addWin)) {
                Leaderboard.leaderBoardWin(this.user);
            } else if (type.equals(RequestType.addLoss)) {
                Leaderboard.leaderBoardLoss(this.user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
