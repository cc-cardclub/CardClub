package at.rennweg.htl.cardclubclient;

import at.rennweg.htl.cardclubclient.cards.Deck;
import at.rennweg.htl.cardclubclient.cards.PlayerCards;
import at.rennweg.htl.cardclubclient.logic.GameCore;
import javafx.fxml.FXML;

import java.io.IOException;

public class AboutController {
    @FXML
    protected void onReturnToStartmenuButtonClick() throws IOException {
        Startmenu.start();
    }
}
