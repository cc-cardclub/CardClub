package at.rennweg.htl.cardclubclient;

import at.rennweg.htl.cardclubclient.cards.Deck;
import at.rennweg.htl.cardclubclient.cards.Player;
import at.rennweg.htl.cardclubclient.logic.Bot;
import at.rennweg.htl.cardclubclient.logic.GameCore;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SingleplayerMenuController implements Initializable {

    public TextField timeForTurn;
    public TextField startingCards;
    public TextField players;
    public CheckBox plus2and4CardsSelected;
    public TextArea textArea;

    private int amountBots;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        timeForTurn.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                checkTimeForTurn();
            }
        });

        startingCards.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                startingCards();
            }
        });

        players.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                howManyPlayers();
            }
        });
    }

    @FXML
    protected void onPlayButtonClick() throws IOException {
        Deck.prepareDeck();

        GameCore.addPlayer(new Player(Deck.getPlayerStartCards())); // Player

        for (int i = 0; i < amountBots; i++) {
            GameCore.addPlayer(new Bot(Deck.getPlayerStartCards())); // Bot
        }

        GameBoard.start();
    }

    public void checkTimeForTurn() {
        timeForTurn.setStyle("-fx-background-color: WHITE;");
        try {
            GameCore.setTurnDuration(Integer.parseInt(timeForTurn.getText()));
        } catch (NumberFormatException e) {
            timeForTurn.setText("");
            timeForTurn.setStyle("-fx-background-color: RED;");
            timeForTurn.setPromptText("nur Zahl");
        }
    }

    public void startingCards() {
        startingCards.setStyle("-fx-background-color: WHITE;");
        try {
            GameCore.setStartingCards(Integer.parseInt(startingCards.getText()));
        } catch (NumberFormatException e) {
            startingCards.setText("");
            startingCards.setStyle("-fx-background-color: RED;");
            startingCards.setPromptText("nur Zahl");
        }
    }


    public void howManyPlayers() {
        players.setStyle("-fx-background-color: WHITE;");
        try {
            amountBots = Integer.parseInt(players.getText());
            if (amountBots > 3) {
                throw new NumberFormatException("Too much Players");
            }
        } catch (NumberFormatException e) {
            players.setText("");
            players.setStyle("-fx-background-color: RED;");
            players.setPromptText("nur Zahl-max 3");
        }
    }


    public void checkBoxAction() {
        if (plus2and4CardsSelected.isSelected()) {
            GameCore.plus2and4CardsSelected = true;
            textArea.setText(textArea.getText() + "\n" + "+2 darf auf +4");
        } else {
            textArea.setText("");
        }
    }
}
