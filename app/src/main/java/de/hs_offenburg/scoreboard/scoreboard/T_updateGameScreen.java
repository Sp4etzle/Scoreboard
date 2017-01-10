package de.hs_offenburg.scoreboard.scoreboard;

import static de.hs_offenburg.scoreboard.scoreboard.Fragment_Game.tournament;
/**
 * Created by micha on 10.01.2017.
 */

public class T_updateGameScreen extends Thread {
//muss zerstört warden wenn updateGameData().getalive(false)
    public I_Team oldTeam1;
    public I_Team oldTeam2;
    public I_Game oldData;
        T_updateGameScreen() {
            oldTeam1 = new O_Team(tournament.getCurrentGame().team1().getTeamNumber(),tournament.getCurrentGame().team1().getTeamName());
            oldTeam2 = new O_Team(tournament.getCurrentGame().team2().getTeamNumber(),tournament.getCurrentGame().team2().getTeamName());
            oldData = new O_Game(oldTeam1,oldTeam2);
        }
    public void run(){
    //updateScreen
        if(tournament.getCurrentGame().gameTime().getTime() != oldData.gameTime().getTime() ||
            tournament.getCurrentGame().result().getPointTeam1() != oldData.result().getPointTeam1() ||
            tournament.getCurrentGame().result().getPointTeam2() != oldData.result().getPointTeam2()
                ) {
            //updateGameScreenH.obtainMessage(1).sendToTarget();
        }
    }
}

