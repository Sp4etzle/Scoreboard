package de.hs_offenburg.scoreboard.scoreboard;

import java.util.ArrayList;

/**
 * Created by micha on 31.05.2016.
 */
public class O_Tournament_Type implements I_Tournament_Type{

    String TournamentType = null;
    Boolean side = false;

    public O_Tournament_Type(String TournamentType){
       setTournamentType(TournamentType);
    }

    @Override
    public String getTournamentType() {
        return this.TournamentType;
    }

    @Override
    public void setTournamentType(String TournamentType) {
        this.TournamentType = TournamentType;
    }

    @Override
    public int getPossibleTeamAmountMin(String TournamentType) {
        int NumberOfTeamsMin = 0;
        switch(TournamentType) {
            case "ShortGame"    : NumberOfTeamsMin = 2; break;
            case "AllvsAll"     : NumberOfTeamsMin = 3; break;
            case "KOSystem"     : NumberOfTeamsMin = 4; break;
            case "Groupphase"   : NumberOfTeamsMin = 6; break;
            default             : NumberOfTeamsMin = 2; break;
        }
        return NumberOfTeamsMin;
    }

    @Override
    public int getPossibleTeamAmountMax(String TournamentType) {
        int NumberOfTeamsMax = 2;
        switch(TournamentType) {
            case "ShortGame"    : NumberOfTeamsMax = 2; break;
            case "AllvsAll"     : NumberOfTeamsMax = 10; break;
            case "KOSystem"     : NumberOfTeamsMax = 20; break;
            case "Groupphase"   : NumberOfTeamsMax = 20; break;
            default             : NumberOfTeamsMax = 2; break;
        }
        return NumberOfTeamsMax;
    }

    @Override
    public ArrayList<Integer> isPossibleTeamNumber (String TournamentType){
        ArrayList<Integer> possibleTeamNumber = null;
        int counter;
        int teamNumber = 0;
        switch(TournamentType){
            case "ShortGame":
                possibleTeamNumber.add(2);
                break;
            default:
                for (teamNumber = getPossibleTeamAmountMin(TournamentType); teamNumber <= getPossibleTeamAmountMax(TournamentType); teamNumber++) {
                    possibleTeamNumber.add(teamNumber);
                }
                break;
        }
        return possibleTeamNumber;
    }

    @Override
    public Boolean isPossibleTeamNumber (int teamNumber){
        Boolean possibleNumber = true;
        if(teamNumber < getPossibleTeamAmountMin(this.TournamentType) || teamNumber > getPossibleTeamAmountMax(this.TournamentType)){
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
        switch (TournamentType){
            case "ShortGame": drawIsPossible = true;
            case "AllvsAll": drawIsPossible = true;
            case "KOSystem": drawIsPossible = false;
            case "Groupphase": drawIsPossible = false;
        }
        return drawIsPossible;
    }
}
