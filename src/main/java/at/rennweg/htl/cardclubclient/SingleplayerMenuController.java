package at.rennweg.htl.cardclubclient;

import at.rennweg.htl.cardclubclient.cards.Card;
import at.rennweg.htl.cardclubclient.cards.Deck;
import at.rennweg.htl.cardclubclient.cards.Player;
import at.rennweg.htl.cardclubclient.logic.Bot;
import at.rennweg.htl.cardclubclient.logic.GameCore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * class to control the Singleplayer Menu's buttons
 *
 * @author Lisa-Marie Hörmann, Bernd Reither, Mattias Burkard
 */
public class SingleplayerMenuController implements Initializable {
    /**
     * TextField how many seconds you have for one turn
     */
    public TextField timeForTurn;
    /**
     * TextField how many starting Cards does everyone get
     */
    public TextField startingCards;
    /**
     * TextField how many Bots you want to play against
     */
    public TextField players;
    /**
     * Checkbox for extra rule
     */
    public CheckBox plus2and4CardsSelected;
    /**
     * Checkbox for extra rule
     */
    public CheckBox cardsSwitchingInPlayingDirectory;
    /**
     * TextArea to show which rules you want to play with
     */
    public TextArea textArea;
    public ChoiceBox choiceBox;
    public CheckBox switchCardsWithPlayer;
    /**
     * standard amount of Bots you play against
     */
    private int amountBots = 1; // Standard amount

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Reset the GameCore to default values
        GameCore.reset();

        // Set default values for input fields
        timeForTurn.setText(String.valueOf(GameCore.getTurnDuration()));
        startingCards.setText(String.valueOf(GameCore.getStartingCards()));
        players.setText(String.valueOf(amountBots));

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

        if (!GameCore.cardsSwitchingInPlayingDirectory && !GameCore.switchCardsWithPlayer
                && !GameCore.plus2and4CardsSelected && GameCore.getTurnDuration() <= 20
                && GameCore.getStartingCards() == 7 && GameCore.botDifficulty.equals("Medium")) {
            GameCore.isValid = true;
        }

        GameCore.addPlayer(new Player(Deck.getPlayerStartCards())); // Player

        for (int i = 0; i < amountBots; i++) {
            GameCore.addPlayer(new Bot(Deck.getPlayerStartCards())); // Bot
        }

        GameBoard.start();
    }

    @FXML
    protected void checkTimeForTurn() {
        timeForTurn.setStyle("-fx-background-color: WHITE;");
        try {
            GameCore.setTurnDuration(Integer.parseInt(timeForTurn.getText()));
        } catch (NumberFormatException e) {
            timeForTurn.setText("");
            timeForTurn.setStyle("-fx-background-color: RED;");
            timeForTurn.setPromptText("nur Zahl");
        }
    }

    @FXML
    protected void startingCards() {
        startingCards.setStyle("-fx-background-color: WHITE;");
        try {
            GameCore.setStartingCards(Integer.parseInt(startingCards.getText()));
        } catch (NumberFormatException e) {
            startingCards.setText("");
            startingCards.setStyle("-fx-background-color: RED;");
            startingCards.setPromptText("nur Zahl");
        }
    }

    @FXML
    protected void howManyPlayers() {
        final int maximumEnemy = 3;
        players.setStyle("-fx-background-color: WHITE;");
        try {
            amountBots = Integer.parseInt(players.getText());
            if (amountBots > maximumEnemy) {
                throw new NumberFormatException("Too much Players");
            }
        } catch (NumberFormatException e) {
            players.setText("");
            players.setStyle("-fx-background-color: RED;");
            players.setPromptText("nur Zahl-max 3");
        }
    }

    @FXML
    protected void checkBoxCard1() {

        if (cardsSwitchingInPlayingDirectory.isSelected()) {
            GameCore.cardsSwitchingInPlayingDirectory = true;
            textArea.setText(textArea.getText()
                    + "\n" + "Karten werden in Spielrichtung weitergegeben");
        } else if (!cardsSwitchingInPlayingDirectory.isSelected()) {
            GameCore.cardsSwitchingInPlayingDirectory = false;
            textArea.setText(textArea.getText().replaceAll("\n"
                    + "Karten werden in Spielrichtung weitergegeben", ""));
        }
    }

    public void checkBoxCard7(ActionEvent actionEvent) {
        if (switchCardsWithPlayer.isSelected()) {
            GameCore.switchCardsWithPlayer = true;
            textArea.setText(textArea.getText()
                    + "\n" + "Karten mit beliebigem Mitspieler tauschen");
        } else if (!switchCardsWithPlayer.isSelected()) {
            GameCore.switchCardsWithPlayer = false;
            textArea.setText(textArea.getText().replaceAll("\n"
                    + "Karten mit beliebigem Mitspieler tauschen", ""));
        }
    }

    /**
     * set the text in the TextArea for the plus2and4CardsSelected Checkbox
     */
    @FXML
    public void checkBox2and4() {
        if (plus2and4CardsSelected.isSelected()) {
            GameCore.plus2and4CardsSelected = true;
            textArea.setText(textArea.getText() + "\n" + "+2 darf auf +4 gelegt werden");
        } else if (!plus2and4CardsSelected.isSelected()) {
            GameCore.plus2and4CardsSelected = false;
            textArea.setText(textArea.getText().replace("\n" + "+2 darf auf +4 gelegt werden", ""));
        }
    }

    /**
     * Event for button to return to the start menu
     *
     * @throws IOException
     */
    public void backToStartMenu() throws IOException {
        Startmenu.start();
    }

    public void choiceBoxValue() {
        GameCore.botDifficulty = (String) choiceBox.getValue();
        System.out.println(GameCore.botDifficulty);
    }

}
