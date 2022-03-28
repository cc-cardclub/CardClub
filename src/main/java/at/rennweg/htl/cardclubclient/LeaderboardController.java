package at.rennweg.htl.cardclubclient;

import java.io.IOException;

public class LeaderboardController {
    /**
     * Event for button to return to the start menu
     *
     * @throws IOException
     */
    public void backToStartMenu() throws IOException {
        Startmenu.start();
    }
}
