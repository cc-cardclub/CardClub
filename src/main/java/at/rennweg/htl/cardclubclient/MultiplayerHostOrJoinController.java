package at.rennweg.htl.cardclubclient;

import javafx.event.ActionEvent;

import java.io.IOException;

public class MultiplayerHostOrJoinController {
    public void multiplayerHostGame(ActionEvent actionEvent) throws IOException {
        MultiplayerMenuHost.start();
    }

    public void multiplayerJoinGame(ActionEvent actionEvent) throws IOException {
        MultiplayerMenuJoin.start();
    }

    public void backToStartMenu(ActionEvent actionEvent) throws IOException {
        Startmenu.start();
    }
}
