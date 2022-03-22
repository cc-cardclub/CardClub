package at.rennweg.htl.cardclubclient;

import at.rennweg.htl.cardclubclient.cards.Card;
import at.rennweg.htl.cardclubclient.cards.Deck;
import at.rennweg.htl.cardclubclient.logic.Bot;
import at.rennweg.htl.cardclubclient.logic.Checker;
import at.rennweg.htl.cardclubclient.logic.GameCore;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

/**
 * class to control the GameBoard's buttons, Cards and more
 *
 * @author Lisa-Marie Hörmann, Bernd Reither, Mattias Burkard
 */
public class GameBoardController implements Initializable {

    /**
     * Label to show whose turn it is
     */
    @FXML
    private Label currentPlayer;
    /**
     * imageView to show which card is on the discardPile
     */
    @FXML
    private ImageView discardPileImg;
    /**
     * button for the UNO (one card left) function
     */
    @FXML
    private Button unoButton;
    /**
     * HBox to store the handCards of players
     */
    @FXML
    private HBox handCards;
    /**
     * Button to show which color you have to play
     */
    @FXML
    public Button wildColorShower;
    /**
     * ScrollPane outside th HBox to scroll threw your HandCards
     */
    @FXML
    private javafx.scene.control.ScrollPane scrollPane;
    /**
     * progressBar to show how much time you have left for your turn
     */
    @FXML
    private ProgressBar progressBar;
    /**
     * button to speed up the Bot Turn and switch to next player if you can't draw cards
     */
    @FXML
    private Button continueButton;

    /**
     * which card is selected
     */
    private Card selectedCard;
    /**
     * which card is selected-image
     */
    private ImageView selectedCardImg;
    /**
     * which player's turn is it
     */
    private int playerId;

    /**
     * how many seconds do you have to click the UNO-Button
     */
    private static final int UNO_BUTTON_TIME = 3;
    /**
     * count from unoButtonTime to zero
     */
    private int countUnoButtonTime = UNO_BUTTON_TIME;
    /**
     * does the player only have one card left?
     */
    private boolean oneCardLeft = false;
    /**
     * has the player clicked the UNO-Button?
     */
    private boolean unoButtonClicked = false;

    /**
     * final variable for turn duration
     */
    private final int botTurns = 10;

    /**
     * Timer for the player's turn
     */
    private final Timer timer = new Timer();
    /**
     * turn-duration for player's turn
     */
    private int turnDuration = GameCore.getTurnDuration();

    /**
     * Timer Task for player's turn
     */
    private final TimerTask counter = new TimerTask() {
        @Override
        public void run() {
            if (turnDuration < GameCore.getTurnDuration() - (GameCore.getTurnDuration() / botTurns)
                    && GameCore.getCurrentPlayer() instanceof Bot) {
                Platform.runLater(() -> ((Bot) GameCore.getCurrentPlayer()).botTurn());
            }
            if (turnDuration < 1) {
                Platform.runLater(() -> {
                    GameCore.getPlayer(playerId).addCard(Deck.getCards(2));
                    changeToNextPlayer();
                    refresh();
                    turnDuration = GameCore.getTurnDuration(); // reset turn time
                });
            }

            progressBar.setProgress((double) turnDuration / GameCore.getTurnDuration());
            turnDuration--;
            if (oneCardLeft) {
                countUnoButtonTime--;
                if (countUnoButtonTime <= 0) {
                    if (!unoButtonClicked) {
                        Platform.runLater(() -> {
                            oneCardLeft = false;
                            countUnoButtonTime = UNO_BUTTON_TIME;
                            unoButtonCheck();
                        });
                    } else {
                        unoButtonClicked = false;
                    }

                    Platform.runLater(() -> unoButton.setText("UNO"));
                }
                if (unoButtonClicked) {
                    Platform.runLater(() -> {
                        oneCardLeft = false;
                        countUnoButtonTime = UNO_BUTTON_TIME;
                        changeToNextPlayer();
                        refresh();
                    });
                }
                System.out.println(countUnoButtonTime);
            }
        }
    };

    /**
     * start the timer, refresh cards when GameBoard-Controller is called
     *
     * @param location
     * @param resources
     */
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        final int oneSecond = 1000;
        playerId = GameCore.getCurrentPlayerID();

        timer.schedule(counter, 0, oneSecond); // wait 0ms, every 1s
        if (GameCore.pauseProgressBar) {
            timer.cancel();
        }

        currentPlayer.setText("Derzeitiger Spieler: Spieler" + playerId
                + " (" + GameCore.getNextPlayer().getClass().getSimpleName() + " hat "
                + GameCore.getNextPlayer().getAllCards().size() + " Karten)");

        refreshDiscardPile();
        refreshHandCards();
        GameBoard gameBoard = new GameBoard();
        gameBoard.setController(this);
    }

    @FXML
    protected void onDrawPile() {
        if (GameCore.getCurrentPlayer() instanceof Bot) return;
        if (GameCore.getCurrentPlayer().getFirstTry()) {
            GameCore.getCurrentPlayer().addCard(Deck.drawCard());
            refreshHandCards();
            if (Checker.canPlay(GameCore.getCurrentPlayer())) {
                GameCore.getCurrentPlayer().setFirstTry(false);
            } else {
                selectedCard = null;
                if (!oneCardLeft) {
                    changeToNextPlayer();
                }
                refreshHandCards();
                refreshDiscardPile();
            }
        }
    }

    @FXML
    protected void onDiscardPile() {
        if (selectedCard != null) {

            Deck.playCard(GameCore.getPlayer(playerId), selectedCard);
            refreshHandCards();
            if (GameCore.getCurrentPlayer().getAllCards().size() == 1) {
                oneCardLeft = true;
                unoButton.setText("oneCard");
            }
            if (selectedCard.getColor().equals("black")) {
                try {
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(
                            "farbwahlPopUp_v1.fxml"));
                    Parent parent = loader.load();
                    Scene scene = new Scene(parent);
                    stage.setScene(scene);
                    stage.showAndWait();
                    String color = Deck.getLastCard().getColor();

                    if (color.equals("yellow")) {
                        wildColorShower.setStyle("-fx-background-color: #FF931E;");
                    } else {
                        wildColorShower.setStyle("-fx-background-color: " + color + ";");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            // Change player
            selectedCard = null;
            if (!oneCardLeft) {
                changeToNextPlayer();
            }
            refreshHandCards();
            refreshDiscardPile();
        }
    }

    @FXML
    protected void onCardSelect(Event event) {
        if (selectedCardImg != null) {
            selectedCardImg.setEffect(null);
        }

        selectedCardImg = (ImageView) event.getSource();
        final int shadowRadius = 15;
        DropShadow ds = new DropShadow(shadowRadius, Color.BLACK);
        selectedCardImg.setEffect(ds);

        int index = handCards.getChildren().indexOf(selectedCardImg);
        selectedCard = GameCore.getPlayer(playerId).getCard(index);
    }

    @FXML
    protected void onUNOButtonClick() {
        unoButton.setText("Clicked");
        unoButtonClicked = true;
    }

    /**
     * check if the UNO-Button is used the right way
     */
    public void unoButtonCheck() {
        if (!oneCardLeft || !unoButtonClicked) {
            GameCore.getCurrentPlayer().addCard(Deck.getCards(2));
        }
        changeToNextPlayer();
        refresh();
        unoButtonClicked = false;
    }

    @FXML
    protected void onContinueButtonClick() {
        if (!(GameCore.getCurrentPlayer() instanceof Bot)) {
            if (!GameCore.getCurrentPlayer().getFirstTry()) {
                changeToNextPlayer();
                refreshHandCards();
            }
        } else {
            ((Bot) GameCore.getPlayer(playerId)).botTurn();
        }
    }

    @FXML
    protected void onExitButtonClick() throws IOException {
        timer.cancel();
        Startmenu.start();
    }

    private void refreshDiscardPile() {
        Image img = new Image(String.valueOf(
                GameBoardController.class.getResource(Deck.getLastCard().getTexture())
        ));
        discardPileImg.setImage(img);

        String color = Deck.getLastCard().getColor();

        if (color.equals("yellow")) {
            wildColorShower.setStyle("-fx-background-color: #FF931E;");
        } else {
            wildColorShower.setStyle("-fx-background-color: " + color + ";");
        }
    }

    private void refreshHandCards() {
        final double cardHeight = 100D;
        final double cardWidth = 70D;
        handCards.getChildren().clear();

        if (GameCore.getCurrentPlayer() instanceof Bot) {
            for (int i = 0; i < GameCore.getCurrentPlayer().getAllCards().size(); i++) {
                ImageView cardImg = new ImageView(String.valueOf(
                        GameBoard.class.getResource("img/UNOcardBack.png")));
                cardImg.setFitHeight(cardHeight);
                cardImg.setFitWidth(cardWidth);

                handCards.getChildren().add(cardImg);
            }
        } else {
            // Sort the cards
            GameCore.getCurrentPlayer().getAllCards().sort(Comparator.comparing(Card::getTexture));

            for (Card card : GameCore.getPlayer(playerId).getAllCards()) {
                ImageView cardImg = new ImageView(String.valueOf(
                        GameBoard.class.getResource(card.getTexture())));
                cardImg.setFitHeight(cardHeight);
                cardImg.setFitWidth(cardWidth);

                cardImg.setOnMouseClicked(this::onCardSelect);

                handCards.getChildren().add(cardImg);
            }
        }
    }

    private void changeToNextPlayer() {
        GameCore.getCurrentPlayer().setFirstTry(true);

        if (GameCore.getPlayer(playerId).getAllCards().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Information");
            alert.setHeaderText("Gewonnen!");
            alert.setContentText("Spieler" + playerId + " hat gewonnen!");

            alert.show();
            GameCore.pauseProgressBar = true;
            GameCore.setGameFinished(true);
        }

        if (GameCore.isGameFinished()) {
            try {
                timer.cancel();
                Startmenu.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            GameCore.switchToNextPlayer();
            turnDuration = GameCore.getTurnDuration(); // reset turn duration
            playerId = GameCore.getCurrentPlayerID();

            currentPlayer.setText("Derzeitiger Spieler: Spieler" + playerId
                    + " (" + GameCore.getNextPlayer().getClass().getSimpleName() + " hat "
                    + GameCore.getNextPlayer().getAllCards().size() + " Karten)");

        }
    }

    /**
     * end the Bot's turn
     */
    public void endBotTurn() {
        if (GameCore.getCurrentPlayer().getAllCards().size() == 1) {
            ((Bot) GameCore.getCurrentPlayer()).unoButtonPress();
        }
        changeToNextPlayer();
        refresh();
    }

    /**
     * refresh HandCards and DiscardPile at once
     */
    public void refresh() {
        refreshHandCards();
        refreshDiscardPile();
    }
}
