package at.rennweg.htl.cardclubclient;

import at.rennweg.htl.cardclubclient.cards.Card;
import at.rennweg.htl.cardclubclient.cards.Deck;
import at.rennweg.htl.cardclubclient.logic.Bot;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class GameBoardController implements Initializable {
    public javafx.scene.control.ScrollPane ScrollPane;
    @FXML
    private Label currentPlayer;
    @FXML
    private ImageView discardPileImg;
    @FXML
    private Button UNOButton;
    @FXML
    private HBox handCards;

    private Card selectedCard;
    private int playerId;

    private Timer timer = new Timer();
    private int turnTime = 15; // 15s

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        playerId = 0;

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (turnTime < 1) {
                    Platform.runLater(() -> {
                        changeToNextPlayer();
                        refresh();
                        turnTime = 15; // reset turn time
                    });
                }

                turnTime--;
            }
        }, 0, 1000); // wait 0ms

        currentPlayer.setText("Derzeitiger Spieler: Spieler" + playerId);
        refreshDiscardPile();
        refreshHandCards();
        GameBoard gameBoard = new GameBoard();
        gameBoard.setController(this);
    }

    @FXML
    protected void onDrawPile() {
        GameCore.getPlayer(playerId).addCard(Deck.drawCard());
        refreshHandCards();
    }

    @FXML
    protected void onDiscardPile() {
        // TODO message if turn was invalid
        if (selectedCard != null) {
            // GameCore.getPlayer(playerId).removeCard(selectedCard);
            // refreshHandCards();

            Deck.playCard(GameCore.getPlayer(playerId), selectedCard);
            refreshHandCards();

            if (selectedCard.getColor().equals("black")) {
                try {
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("farbwahlPopUp_v1.fxml"));
                    Parent parent = loader.load();
                    Scene scene = new Scene(parent);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            // Change player
            selectedCard = null;
            changeToNextPlayer();
            refreshHandCards();
            refreshDiscardPile();
        }
    }

    @FXML
    protected void onCardSelect(Event event) {
        int index = handCards.getChildren().indexOf((ImageView) event.getSource());
        selectedCard = GameCore.getPlayer(playerId).getCard(index);
    }

    @FXML
    protected void onUNOButtonClick() {
        UNOButton.setText("Clicked");
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
    }

    private void refreshHandCards() {
        handCards.getChildren().clear();

        for (Card card : GameCore.getPlayer(playerId).getAllCards()) {
            ImageView cardImg = new ImageView(String.valueOf(GameBoard.class.getResource(card.getTexture())));
            cardImg.setFitHeight(100D);
            cardImg.setFitWidth(70D);

            cardImg.setOnMouseClicked(this::onCardSelect);

            handCards.getChildren().add(cardImg);
        }

        ScrollPane.hvalueProperty().bind(handCards.widthProperty());
    }

    private void changeToNextPlayer() {
        if (GameCore.getPlayer(playerId).getAllCards().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Information");
            alert.setHeaderText("Gewonnen!");
            alert.setContentText("Spieler" + playerId + " hat gewonnen!");

            alert.showAndWait();

            GameCore.setGameFinished(true);
        }

        if (GameCore.isGameFinished()) {
            try {
                Startmenu.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        GameCore.switchToNextPlayer();
        playerId = GameCore.getCurrentPlayerID();

        currentPlayer.setText("Derzeitiger Spieler: Spieler" + playerId);
    }

    public void onProgressBarClick(MouseEvent mouseEvent) {
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
    public void onBotButtonClick() {
        ((Bot) GameCore.getPlayer(playerId)).botTurn();
    }
}
