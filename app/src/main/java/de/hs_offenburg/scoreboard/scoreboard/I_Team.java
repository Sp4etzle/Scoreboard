package de.hs_offenburg.scoreboard.scoreboard;

/**
 * Created by micha on 31.05.2016.
 */
public interface I_Team {
    Boolean setTeamName(String teamName);
    String getTeamName();
    Boolean setTeamNumber(int teamNumber);
    int getTeamNumber();
}
