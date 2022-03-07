package at.rennweg.htl.cardclubclient;

import at.rennweg.htl.cardclubclient.cards.Card;
import at.rennweg.htl.cardclubclient.cards.Deck;
import at.rennweg.htl.cardclubclient.cards.Player;
import at.rennweg.htl.cardclubclient.logic.Bot;
import at.rennweg.htl.cardclubclient.logic.GameCore;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GameBoardController implements Initializable {
    @FXML
    private Label currentPlayer;
    @FXML
    private ImageView discardPileImg;
    @FXML
    private Button UNOButton;
    @FXML
    private HBox handCards;
    @FXML
    public Button WildColorShower;
    @FXML
    private ScrollPane ScrollPane;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Button continueButton;

    private Card selectedCard;
    private ImageView selectedCardImg;
    private int playerId;

    private int UNOButtonTime = 3;
    private boolean oneCardLeft = false;
    private boolean UNOButtonClicked = false;

    private final Timer timer = new Timer();
    private int turnDuration = GameCore.getTurnDuration();

    private final TimerTask counter = new TimerTask() {
        @Override
        public void run() {
            if (turnDuration < GameCore.getTurnDuration() - (GameCore.getTurnDuration() / 10)
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
            /*
            if (oneCardLeft) {

                if (UNOButtonClicked) {
                    UNOButtonClicked = false;
                    changeToNextPlayer();
                }
                if (UNOButtonTime <= 0) {
                    oneCardLeft = false;
                    GameCore.getCurrentPlayer().addCard(Deck.getCards(2));
                    changeToNextPlayer();
                }
                UNOButtonTime--;
            }*/


        }
    };

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        playerId = GameCore.getCurrentPlayerID();

        timer.schedule(counter, 0, 1000); // wait 0ms, every 1s
        if (GameCore.pauseProgressBar) {
            timer.cancel();
        }

        currentPlayer.setText("Derzeitiger Spieler: Spieler" + playerId
                + " (" + GameCore.getNextPlayer().getClass().getSimpleName() + " hat " + GameCore.getNextPlayer().getAllCards().size() + " Karten)");

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
            GameCore.getCurrentPlayer().setFirstTry(false);
        }
    }

    @FXML
    protected void onDiscardPile() {
        // TODO message if turn was invalid
        if (selectedCard != null) {

            Deck.playCard(GameCore.getPlayer(playerId), selectedCard);
            refreshHandCards();
            if (GameCore.getCurrentPlayer().getAllCards().size() == 1) {
                oneCardLeft = true;
            }
            if (selectedCard.getColor().equals("black")) {
                try {
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("farbwahlPopUp_v1.fxml"));
                    Parent parent = loader.load();
                    Scene scene = new Scene(parent);
                    stage.setScene(scene);
                    stage.showAndWait();
                    String test = Deck.getLastCard().getColor();
                    WildColorShower.setStyle("-fx-background-color:" + test + ";");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            // Change player
            selectedCard = null;
            /*if (!oneCardLeft) {*/
                changeToNextPlayer();
            /*}*/
            refreshHandCards();
            refreshDiscardPile();
        } else {

        }
    }

    @FXML
    protected void onCardSelect(Event event) {
        if (selectedCardImg != null) {
            selectedCardImg.setEffect(null);
        }

        selectedCardImg = (ImageView) event.getSource();

        DropShadow ds = new DropShadow(15, Color.BLACK);
        selectedCardImg.setEffect(ds);

        int index = handCards.getChildren().indexOf(selectedCardImg);
        selectedCard = GameCore.getPlayer(playerId).getCard(index);
    }

    @FXML
    protected void onUNOButtonClick() {
        UNOButton.setText("Clicked");
        UNOButtonClicked = true;
    }

    @FXML
    protected void onExitButtonClick() {
        System.exit(0);
    }

    private void refreshDiscardPile() {
        Image img = new Image(String.valueOf(
                GameBoardController.class.getResource(Deck.getLastCard().getTexture())
        ));
        discardPileImg.setImage(img);

        String color = Deck.getLastCard().getColor();
        WildColorShower.setStyle("-fx-background-color:" + color + ";");
    }

    private void refreshHandCards() {
        handCards.getChildren().clear();

        if (GameCore.getCurrentPlayer() instanceof Bot) {
            for (int i = 0; i < GameCore.getCurrentPlayer().getAllCards().size(); i++) {
                ImageView cardImg = new ImageView(String.valueOf(GameBoard.class.getResource("img/UNOcardBack.png")));
                cardImg.setFitHeight(100D);
                cardImg.setFitWidth(70D);

                handCards.getChildren().add(cardImg);
            }
        } else {
            // Sort the cards
            GameCore.getCurrentPlayer().getAllCards().sort(Comparator.comparing(Card::getTexture));

            for (Card card : GameCore.getPlayer(playerId).getAllCards()) {
                ImageView cardImg = new ImageView(String.valueOf(GameBoard.class.getResource(card.getTexture())));
                cardImg.setFitHeight(100D);
                cardImg.setFitWidth(70D);

                cardImg.setOnMouseClicked(this::onCardSelect);

                handCards.getChildren().add(cardImg);
            }
            if (GameCore.getCurrentPlayer().getAllCards().size() > 7) {
                ScrollPane.hvalueProperty().bind(handCards.widthProperty());
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
                Startmenu.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            GameCore.switchToNextPlayer();
            turnDuration = GameCore.getTurnDuration(); // reset turn duration
            playerId = GameCore.getCurrentPlayerID();

            currentPlayer.setText("Derzeitiger Spieler: Spieler" + playerId
                    + " (" + GameCore.getNextPlayer().getClass().getSimpleName() + " hat " + GameCore.getNextPlayer().getAllCards().size() + " Karten)");

        }
    }

    public void endBotTurn() {
        changeToNextPlayer();
        refresh();
    }

    public void refresh() {
        refreshHandCards();
        refreshDiscardPile();
    }

    @FXML
    public void onContinueButtonClick() {
        if (!(GameCore.getCurrentPlayer() instanceof Bot)) {
            if (!GameCore.getCurrentPlayer().getFirstTry()) {
                changeToNextPlayer();
                refreshHandCards();
            }
        } else {
            ((Bot) GameCore.getPlayer(playerId)).botTurn();
        }
    }
}
