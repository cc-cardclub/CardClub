package at.rennweg.htl.cardclubserver;

import at.rennweg.htl.cardclubclient.OptionsController;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/**
 * Class for the cardclub sever
 * @author Mattias Burkard
 */
public class Server {
    /**
     * Basic server
     */
    public static void server() {
        OptionsController.setPath();
        Properties props = OptionsController.getProps();
        try {
            int port = Integer.parseInt(props.getProperty("port"));
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                writer.println("Hey, I'm your server!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
