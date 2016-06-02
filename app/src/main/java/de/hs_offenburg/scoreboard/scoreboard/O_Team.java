package de.hs_offenburg.scoreboard.scoreboard;

/**
 * Created by micha on 01.06.2016.
 */
public class O_Team implements I_Team{

    int teamNumber;
    String teamName;
    int tablePoints;

    public O_Team(int teamNumber,String teamName){
        setTeamName(teamName);
        setTeamNumber(teamNumber);
    }

    @Override
    public Boolean setTeamName(String teamName) {
        Boolean possibleName = false;
        //length between 2 and 20
        if (teamName.length() > 2 && teamName.length() < 20){
            possibleName = true;
            this.teamName = teamName;
        }else{
            this.teamName = "Default";
        }
        return possibleName;
        //if false returned, toast: "Der Name muss zwischen 2 und 20 Zeichen lang sein"
    }

    @Override
    public String getTeamName() {
        return this.teamName;
    }

    @Override
    public Boolean setTeamNumber(int teamNumber) {
        Boolean possibleNumber = false;
        if (teamNumber >= 0){
            this.teamNumber = teamNumber;
            possibleNumber = true;
        }
        return possibleNumber;
    }

    @Override
    public int getTeamNumber() {
        return this.teamNumber;
    }
}
