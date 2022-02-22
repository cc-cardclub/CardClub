package at.rennweg.htl.cardclubclient;

import at.rennweg.htl.cardclubclient.cards.Deck;
import at.rennweg.htl.cardclubclient.cards.Player;
import at.rennweg.htl.cardclubclient.logic.GameCore;
import javafx.fxml.FXML;

import java.io.IOException;

public class SingleplayerMenuController {

    @FXML
    protected void onPlayButtonClick() throws IOException {
        Deck.prepareDeck();
        GameCore.addPlayer(new Player(Deck.getPlayerStartCards()));
        GameBoard.start();
    }

}
