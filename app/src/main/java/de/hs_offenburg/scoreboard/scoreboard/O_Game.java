package de.hs_offenburg.scoreboard.scoreboard;

/**
 * Created by micha on 31.05.2016.
 */
public class O_Game implements I_Game{
    Boolean gameActive = false;
    I_Team[] opponents = new I_Team[2];
    I_Time gameTime = new O_Time();
    I_Result result = new O_Result();

    public O_Game(I_Team team1, I_Team team2){
        opponents[0] = team1;
        opponents[1] = team2;
    }
    @Override
    public Boolean getStatus() {
        return this.gameActive;
    }

    @Override
    public void setStatus(Boolean gameActive) {
        this.gameActive = gameActive;
    }

    @Override
    public I_Time getTime() {
        return this.gameTime;
    }

    @Override
    public void setTime(I_Time gameTime) {
        this.gameTime = gameTime;
    }
}
