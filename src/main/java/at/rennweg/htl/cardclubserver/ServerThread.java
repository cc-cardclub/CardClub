package at.rennweg.htl.cardclubserver;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private final Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

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
