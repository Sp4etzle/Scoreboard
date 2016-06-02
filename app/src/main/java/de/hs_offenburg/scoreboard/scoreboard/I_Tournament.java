package de.hs_offenburg.scoreboard.scoreboard;

/**
 * Created by micha on 31.05.2016.
 */
public interface I_Tournament {
    String generateTournamentName();
    void generateTable(I_TeamList teamList);
    void generateRound();
}
