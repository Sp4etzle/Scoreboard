package de.hs_offenburg.scoreboard.scoreboard;

import android.app.Fragment;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import static de.hs_offenburg.scoreboard.scoreboard.Fragment_Game.state_game_running;
import static de.hs_offenburg.scoreboard.scoreboard.Fragment_Game.state_tournament_running;
import static de.hs_offenburg.scoreboard.scoreboard.Fragment_Game.tournament;


/**
 * Created by micha on 10.01.2017.
 */

public class T_updateGameScreen extends Thread {
    //muss zerstört warden wenn updateGameData().getalive(false)
    private I_Team oldTeam1;
    private I_Team oldTeam2;
    private I_Game oldData;
    T_updateGameScreen() {
        oldTeam1 = new O_Team(1,"");
        oldTeam2 = new O_Team(2,"");
        oldData = new O_Game(oldTeam1,oldTeam2);
    }

    public void run(){
    //updateScreen
        while (state_game_running  && state_tournament_running) {

            if (tournament.getCurrentGame().gameTime().getTime() != oldData.gameTime().getTime() ||
                    tournament.getCurrentGame().result().getPointTeam1() != oldData.result().getPointTeam1() ||
                    tournament.getCurrentGame().result().getPointTeam2() != oldData.result().getPointTeam2()
                    ) {

                //Set Old Data
                oldData.gameTime().setTimeSec(tournament.getCurrentGame().gameTime().getTimeSec());
                oldData.gameTime().setTimeMin(tournament.getCurrentGame().gameTime().getTimeMin());
                oldData.gameTime().setTimeHour(tournament.getCurrentGame().gameTime().getTimeHour());
                oldData.result().setPointTeam1(tournament.getCurrentGame().result().getPointTeam1());
                oldData.result().setPointTeam2(tournament.getCurrentGame().result().getPointTeam2());


            }
        }
    }
}

