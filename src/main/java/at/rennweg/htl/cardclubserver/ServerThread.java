package at.rennweg.htl.cardclubserver;

import java.io.*;
import java.net.Socket;

/**
 * Class for CardClub leaderboard server threads
 *
 * @author Mattias Burkard
 */
public class ServerThread extends Thread {
    /**
     * Socket of the thread
     */
    private final Socket socket;

    /**
     * Create a new thread
     *
     * @param socket client connection
     */
    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String text;

            while (true) {
                text = reader.readLine();
                if (text != null) {
                    if (text.equals("close")) break;
                    new Request(text).handle(socket);
                }
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
