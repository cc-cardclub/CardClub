package at.rennweg.htl.cardclubclient.logic;

import at.rennweg.htl.cardclubclient.OptionsController;
import at.rennweg.htl.cardclubserver.User;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Class for connecting the client to the server
 *
 * @author Mattias Burkard
 */
public class ServerConnection {
    public static List<User> getLeaderboard() {
        return getLeaderboard(10);
    }

    public static List<User> getLeaderboard(int amount) {
        OptionsController.setPath();
        Properties props = OptionsController.getProps();

        String serverHostname = props.getProperty("server");
        int port = Integer.parseInt(props.getProperty("port"));

        List<User> users = new ArrayList<>();

        try (Socket socket = new Socket(serverHostname, port)) {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println("get<cc-get-amount-start>" + amount + "<cc-get-amount-end>");
            String text;
            while (true) {
                text = reader.readLine();
                if (text != null) {
                    if (text.equals("close")) break;
                    if (text.startsWith("<cc-lb>")) {
                        users.add(new User(text));
                    } else {
                        System.out.println(text);
                        break;
                    }
                }
            }
            writer.println("close");

        } catch (ConnectException e) {
            System.out.println("Can't connect to the server!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static void leaderboardWin() {
        OptionsController.setPath();
        Properties props = OptionsController.getProps();

        String serverHostname = props.getProperty("server");
        int port = Integer.parseInt(props.getProperty("port"));

        try (Socket socket = new Socket(serverHostname, port)) {
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println("addWin<cc-id-start>" + props.getProperty("uuid") + "<cc-id-end>" +
                    "<cc-username-start>" + props.getProperty("username") + "<cc-username-end>");
            writer.println("close");

        } catch (ConnectException e) {
            System.out.println("Can't connect to the server!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void leaderboardLoss() {
        OptionsController.setPath();
        Properties props = OptionsController.getProps();

        String serverHostname = props.getProperty("server");
        int port = Integer.parseInt(props.getProperty("port"));

        try (Socket socket = new Socket(serverHostname, port)) {
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println("addLoss<cc-id-start>" + props.getProperty("uuid") + "<cc-id-end>" +
                    "<cc-username-start>" + props.getProperty("username") + "<cc-username-end>");
            writer.println("close");

        } catch (ConnectException e) {
            System.out.println("Can't connect to the server!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
