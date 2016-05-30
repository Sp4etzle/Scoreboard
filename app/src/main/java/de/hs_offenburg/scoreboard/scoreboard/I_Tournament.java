package de.hs_offenburg.scoreboard.scoreboard;

/**
 * Created by micha on 31.05.2016.
 */
public interface I_Tournament {
    void newTournament(I_Game newGame);
    void deleteTournament(I_Game deleteGame);
    void safeTournament(I_Game safeGame);
}
