package de.hs_offenburg.scoreboard.scoreboard;

/**
 * Created by micha on 31.05.2016.
 */
public class O_Game implements I_Game{

    Boolean gameActive = false;
    I_Team team1;
    I_Team team2;
    I_Time gameTime = new O_Time();
    I_Result result = new O_Result();

    public O_Game(I_Team team1, I_Team team2){
        this.team1 = team1;
        this.team2 = team2;
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
    public I_Team team1(){
        return this.team1;
    }

    @Override
    public I_Team team2(){
        return this.team2;
    }

    @Override
    public I_Time gameTime(){
        return this.gameTime;
    }

    @Override
    public I_Result result(){
        return this.result;
    }

    @Override
    public void switchTeams(){
        I_Team teamBuffer = this.team1;
        this.team1 = this.team2;
        this.team2 = teamBuffer;
    }
}
