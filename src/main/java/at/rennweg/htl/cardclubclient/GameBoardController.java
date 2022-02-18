package at.rennweg.htl.cardclubclient;

import at.rennweg.htl.cardclubclient.cards.Card;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class GameBoardController {
    @FXML
    private Button UNOButton;
    @FXML
    private HBox handCards;

    @FXML
    protected void onUNOButtonClick() {
        UNOButton.setText("Clicked");

        Card newCard = new Card(5, "green");
        ImageView cardImg = new ImageView(String.valueOf(GameBoard.class.getResource(newCard.getTexture())));
        cardImg.setFitHeight(100D);
        cardImg.setFitWidth(70D);

        handCards.getChildren().add(cardImg);
    }

    @FXML
    protected void onExitButtonClick() {
        System.exit(0);
    }
}
