package at.rennweg.htl.cardclubclient;

import at.rennweg.htl.cardclubclient.cards.Card;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Random;

public class GameBoardController {
    @FXML
    private Button UNOButton;
    @FXML
    private HBox handCards;

    @FXML
    protected void onDrawPile() {
        String[] num = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        String[] color = { "red", "green", "blue", "yellow" };

        Random random = new Random();

        Card newCard = new Card(num[random.nextInt(num.length)], color[random.nextInt(color.length)], false);
        ImageView cardImg = new ImageView(String.valueOf(GameBoard.class.getResource(newCard.getTexture())));
        cardImg.setFitHeight(100D);
        cardImg.setFitWidth(70D);

        handCards.getChildren().add(cardImg);
    }

    @FXML
    protected void onDiscardPile() {

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
