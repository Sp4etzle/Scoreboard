package de.hs_offenburg.scoreboard.scoreboard;

/**
 * Created by micha on 02.06.2016.
 */
public interface I_TeamList {
    void addTeam(I_Team addTeam);
    void deleteTeam(I_Team deleteTeam);
    I_Team getTeam(int teamNumber);
    void resetTeamList();
    void saveTeamList();
    void loadTeamList();
    void generateTeamNumber();
    int getSizeTeamList();
    void shuffleTeamList();
    Boolean possibleTeamList(I_Tournament_Type tournamentType);
}
