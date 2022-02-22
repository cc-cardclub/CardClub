package at.rennweg.htl.cardclubclient;

import at.rennweg.htl.cardclubclient.cards.Card;
import at.rennweg.htl.cardclubclient.cards.Deck;
import at.rennweg.htl.cardclubclient.cards.PlayerCards;
import at.rennweg.htl.cardclubclient.logic.GameCore;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class GameBoardController implements Initializable {
    @FXML
    private ImageView discardPileImg;
    @FXML
    private Button UNOButton;
    @FXML
    private HBox handCards;

    private Card selectedCard;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        refreshDiscardPile();
        refreshHandCards();
    }

    @FXML
    protected void onDrawPile() {
        GameCore.getPlayer(0).addCard(Deck.drawCard());
        refreshHandCards();
    }

    @FXML
    protected void onDiscardPile() {
        // TODO message if turn was invalid
        if (selectedCard != null) {
            GameCore.getPlayer(0).removeCard(selectedCard);
            refreshHandCards();

            Deck.playCard(GameCore.getPlayer(0), selectedCard);

            if (selectedCard.getColor().equals("black")) {
                try {
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("farbwahlPopUp_v1.fxml"));
                    Parent parent = (Parent) loader.load();
                    Scene scene = new Scene(parent);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            refreshHandCards();
            refreshDiscardPile();

            selectedCard = null;
        }
    }

    @FXML
    protected void onCardSelect(Event event) {
        int index = handCards.getChildren().indexOf((ImageView) event.getSource());
        selectedCard = GameCore.getPlayer(0).getCard(index);
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

        for (Card card : GameCore.getPlayer(0).getAllCards()) {
            ImageView cardImg = new ImageView(String.valueOf(GameBoard.class.getResource(card.getTexture())));
            cardImg.setFitHeight(100D);
            cardImg.setFitWidth(70D);

            cardImg.setOnMouseClicked(this::onCardSelect);

            handCards.getChildren().add(cardImg);
        }
    }

    public void onProgressBarClick(MouseEvent mouseEvent) {
    }
}
