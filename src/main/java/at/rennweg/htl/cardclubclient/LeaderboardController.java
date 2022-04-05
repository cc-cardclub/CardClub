package at.rennweg.htl.cardclubclient;

import at.rennweg.htl.cardclubclient.logic.ServerConnection;
import at.rennweg.htl.cardclubserver.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LeaderboardController implements Initializable {
    @FXML
    private TableView<User> leaderboard;
    @FXML
    private TableColumn<User, String> playerColumn;
    @FXML
    private TableColumn<User, String> winsColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServerConnection.leaderboardWin();
        // Die folgende Zeile führt zu einer Wartezeit von 10 Sekunden beim Öffnen des Leaderboards und wurde daher auskommentiert
        // Grund: Rate limit - nur eine Anfrage pro 10 Sekunden, dann wird gewartet
        // System.out.println(ServerConnection.getLeaderboard());

        List<User> leaderboardList = ServerConnection.getLeaderboard();

        playerColumn.setCellValueFactory((data) -> new SimpleStringProperty(data.getValue().getName()));
        winsColumn.setCellValueFactory((data) -> new SimpleStringProperty(String.valueOf(data.getValue().getWins())));

        leaderboard.getItems().addAll(leaderboardList);
    }

    /**
     * Event for button to return to the start menu
     *
     * @throws IOException
     */
    public void backToStartMenu() throws IOException {
        Startmenu.start();
    }
}
