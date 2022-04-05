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
        playerColumn.setStyle("-fx-alignment: CENTER;");
        winsColumn.setStyle("-fx-alignment: CENTER;");

        List<User> leaderboardList = ServerConnection.getLeaderboard(30);

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
