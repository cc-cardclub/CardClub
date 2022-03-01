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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class GameBoardController implements Initializable {
    public ProgressBar progressBar;
    public Button continueButton;
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

    private Card selectedCard;
    private int playerId;

    private final Timer timer = new Timer();
    private int turnDuration = GameCore.getTurnDuration();

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        playerId = GameCore.getCurrentPlayerID();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (turnDuration < 1) {
                    Platform.runLater(() -> {
                        GameCore.getPlayer(playerId).addCard(Deck.getCards(2));
                        changeToNextPlayer();
                        refresh();
                        turnDuration = GameCore.getTurnDuration(); // reset turn time
                    });
                }

                progressBar.setProgress((double) turnDuration/GameCore.getTurnDuration());
                turnDuration--;
            }
        }, 0, 1000); // wait 0ms, every 1s

        currentPlayer.setText("Derzeitiger Spieler: Spieler" + playerId);
        refreshDiscardPile();
        refreshHandCards();
        GameBoard gameBoard = new GameBoard();
        gameBoard.setController(this);
    }

    @FXML
    protected void onDrawPile() {
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

        String test = Deck.getLastCard().getColor();
        WildColorShower.setStyle("-fx-background-color:" + test + ";");
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
        if (GameCore.getCurrentPlayer().getAllCards().size() > 7) {
            ScrollPane.hvalueProperty().bind(handCards.widthProperty());
        }
    }

    private void changeToNextPlayer() {
        GameCore.getCurrentPlayer().setFirstTry(true);
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
        turnDuration = GameCore.getTurnDuration(); // reset turn duration
        playerId = GameCore.getCurrentPlayerID();

        currentPlayer.setText("Derzeitiger Spieler: Spieler" + playerId);
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
            changeToNextPlayer();
        } else {
            ((Bot) GameCore.getPlayer(playerId)).botTurn();
        }
    }

}
