package de.hs_offenburg.scoreboard.scoreboard;

import java.util.ArrayList;
import java.util.StringTokenizer;

import static de.hs_offenburg.scoreboard.scoreboard.GameMode.ShortGame;

/**
 * Created by micha on 31.05.2016.
 */
public class O_Tournament_Type implements I_Tournament_Type{

    GameMode TournamentType = GameMode.fromInteger(0);
    Boolean side = false;

    public O_Tournament_Type(){

    }

    @Override
    public String getTournamentTypeS() {
        return GameMode.fromEnumToString(this.TournamentType);
    }

    @Override
    public int getTournamentTypeI() {
        return this.TournamentType.toInteger(TournamentType);
    }

    @Override
    public void setTournamentType(GameMode TournamentType) {
        this.TournamentType = TournamentType;
    }

    @Override
    public int getPossibleTeamAmountMin() {
        int NumberOfTeamsMin = 0;
        switch(TournamentType.toInteger(TournamentType)) {
            case 0  : NumberOfTeamsMin = 2; break;
            case 1  : NumberOfTeamsMin = 3; break;
            case 2  : NumberOfTeamsMin = 4; break;
            case 3  : NumberOfTeamsMin = 6; break;
            default : NumberOfTeamsMin = 2; break;
        }
        return NumberOfTeamsMin;
    }

    @Override
    public int getPossibleTeamAmountMax() {
        int NumberOfTeamsMax = 2;
        switch(TournamentType.toInteger(TournamentType)) {
            case 0    : NumberOfTeamsMax = 2; break;
            case 1     : NumberOfTeamsMax = 15; break;
            case 2     : NumberOfTeamsMax = 30; break;
            case 3   : NumberOfTeamsMax = 40; break;
            default             : NumberOfTeamsMax = 2; break;
        }
        return NumberOfTeamsMax;
    }

    @Override
    public ArrayList<Integer> getPossibleTeamNumber (){
        ArrayList<Integer> possibleTeamNumber = null;
        int teamNumber = 0;
        switch(TournamentType.toInteger(TournamentType)){
            case 0:
                possibleTeamNumber.add(2);
                break;
            default:
                for (teamNumber = getPossibleTeamAmountMin(); teamNumber <= getPossibleTeamAmountMax(); teamNumber++) {
                    possibleTeamNumber.add(teamNumber);
                }
                break;
        }
        return possibleTeamNumber;
    }

    @Override
    public Boolean isPossibleTeamNumber (int teamNumber){
        Boolean possibleNumber = true;
        if(teamNumber < getPossibleTeamAmountMin() || teamNumber > getPossibleTeamAmountMax()){
            possibleNumber = false;
        }
        return possibleNumber;
    }

    @Override
    public void setBoolean(Boolean side){
        this.side = side;
    }

    @Override
    public Boolean getBoolean(){
        return this.side;
    }

    @Override
    public Boolean isDrawPossible(){
        Boolean drawIsPossible = false;
        switch (TournamentType.toInteger(TournamentType)){
            case 0: drawIsPossible = true; break;
            case 1: drawIsPossible = true; break;
            case 2: drawIsPossible = false; break;
            case 3: drawIsPossible = false; break;
        }
        return drawIsPossible;
    }
}