package de.hs_offenburg.scoreboard.scoreboard;

/**
 * Created by micha on 31.05.2016.
 */
public interface I_Game {
    Boolean getStatus();
    void setStatus(Boolean status);

    I_Time getTime();
    void setTime(I_Time gameTime);
}
