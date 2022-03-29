package at.rennweg.htl.cardclubclient.logic;

import at.rennweg.htl.cardclubclient.OptionsController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Properties;

/**
 * Class for connecting the client to the server
 * @author Mattias Burkard
 */
public class ServerConnection {
    /**
     * Connect the client to the server
     */
    public static void connect() {
        OptionsController.setPath();
        Properties props = OptionsController.getProps();

        String serverHostname = props.getProperty("server");
        int port = Integer.parseInt(props.getProperty("port"));

        try (Socket socket = new Socket(serverHostname, port)) {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String server = reader.readLine();
            System.out.println("Server:" + server);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
