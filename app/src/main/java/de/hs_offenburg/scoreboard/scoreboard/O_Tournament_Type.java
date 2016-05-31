package de.hs_offenburg.scoreboard.scoreboard;

import java.util.ArrayList;

/**
 * Created by micha on 31.05.2016.
 */
public class O_Tournament_Type implements I_Tournament_Type{

    String TournamentType = null;
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
                possibleTeamNumber.add(0,1);
                possibleTeamNumber.add(1,2);
                break;
            case "AllvsAll":
                for (counter = getPossibleTeamAmountMin(TournamentType); counter <= getPossibleTeamAmountMax(TournamentType); counter++) {
                    possibleTeamNumber.add(teamNumber, counter);
                    teamNumber++;
                }
                break;
            case "KOSystem":
                //Sollte per Fallthrough durch "Gruppenphase" gelöst werden, da dafür dieselben Bedingungen gelten
            case "Gruppenphase":
                for (counter = getPossibleTeamAmountMin(TournamentType); counter <= getPossibleTeamAmountMax(TournamentType); counter++) {
                    if(counter%2 == 0){
                        possibleTeamNumber.add(teamNumber, counter);
                        teamNumber++;
                    }
                }
                break;
            default:
        }
        return possibleTeamNumber;
    }
}
