package de.hs_offenburg.scoreboard.scoreboard;

import java.util.ArrayList;

/**
 * Created by micha on 31.05.2016.
 */
public interface I_Tournament {

    void updateTablePoints(I_Game currentGame);
    Boolean startGame();
    I_Game getCurrentGame();
    Boolean loadNextGame();
    Boolean getGameAvailable();
    Boolean nextGameAvailable();
    String getTournamentTypeS();
    I_Tournament_Type getTournamentType();
}
