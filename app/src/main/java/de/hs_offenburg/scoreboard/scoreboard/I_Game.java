package de.hs_offenburg.scoreboard.scoreboard;

/**
 * Created by micha on 31.05.2016.
 */
public interface I_Game {
    Boolean getStatus();
    void setStatus(Boolean status);
    I_Team team1();
    I_Team team2();
    I_Time gameTime();
    I_Result result();
    void switchTeams();

}
