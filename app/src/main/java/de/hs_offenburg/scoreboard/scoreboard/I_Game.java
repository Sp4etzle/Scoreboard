package de.hs_offenburg.scoreboard.scoreboard;

/**
 * Created by micha on 31.05.2016.
 */
public interface I_Game {
    I_Team getTeamName();
    I_Team getTeamNumber();
    I_Time getTime();
    I_Time setTime();
    I_Time setTimeParameter();
}
