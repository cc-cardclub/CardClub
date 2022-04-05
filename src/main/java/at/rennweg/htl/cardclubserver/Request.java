package at.rennweg.htl.cardclubserver;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class Request {
    private RequestType type;
    private User user;
    private int getAmount;

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

    public void handle(Socket socket) {
        try {
            if (type.equals(RequestType.get)) {
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                while (RateLimit.addresses.contains(socket.getInetAddress())) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                RateLimit.addresses.add(socket.getInetAddress());

                List<User> users = Leaderboard.getLeaderboard(getAmount);

                for (User user : users) {
                    writer.println("<cc-lb><cc-username-start>" + user.getName() + "<cc-username-end>" +
                            "<cc-id-start>" + user.getId() + "<cc-id-end>" +
                            "<cc-wins-start>" + user.getWins() + "<cc-wins-end>" +
                            "<cc-losses-start>" + user.getLosses() + "<cc-losses-end>");
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
