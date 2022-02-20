package at.rennweg.htl.cardclubclient;

import at.rennweg.htl.cardclubclient.cards.Card;
import at.rennweg.htl.cardclubclient.cards.Deck;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

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
        Image img = new Image(String.valueOf(
                        GameBoardController.class.getResource(Deck.getLastCard().getTexture())
        ));
        discardPileImg.setImage(img);
    }

    @FXML
    protected void onDrawPile() {
        Card newCard = Deck.drawCard();
        ImageView cardImg = new ImageView(String.valueOf(GameBoard.class.getResource(newCard.getTexture())));
        cardImg.setFitHeight(100D);
        cardImg.setFitWidth(70D);

        handCards.getChildren().add(cardImg);
    }

    @FXML
    protected void onDiscardPile() {

    }

    @FXML
    protected void onCardSelect() {

    }

    @FXML
    protected void onUNOButtonClick() {
        UNOButton.setText("Clicked");
    }

    @FXML
    protected void onExitButtonClick() {
        System.exit(0);
    }
}
