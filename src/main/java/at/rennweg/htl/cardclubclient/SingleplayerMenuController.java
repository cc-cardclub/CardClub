package at.rennweg.htl.cardclubclient;

import at.rennweg.htl.cardclubclient.cards.Deck;
import javafx.fxml.FXML;

import java.io.IOException;

public class SingleplayerMenuController {

    @FXML
    protected void onPlayButtonClick() throws IOException {
        Deck.prepareDeck();
        GameBoard.start();
    }

}
