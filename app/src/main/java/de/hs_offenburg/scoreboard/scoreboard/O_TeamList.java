package de.hs_offenburg.scoreboard.scoreboard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by micha on 02.06.2016.
 */
public class O_TeamList implements I_TeamList{

    ArrayList<I_Team> teamList;
    public O_TeamList(){
        teamList= new ArrayList<I_Team>();
    }
    @Override
    public void addTeam(I_Team addTeam) {
        if (teamList.contains(addTeam) == false) {
            this.teamList.add(addTeam);
        }else{
            //TODO: Fehlermeldung anzeigen, dass es das Team schon gibt
        }
    }

    @Override
    public void deleteTeam(I_Team deleteTeam) {
        this.teamList.remove(deleteTeam);
    }

    @Override
    public I_Team getTeam(int teamNumber){
        return this.teamList.get(teamNumber);
    }

    @Override
    public void resetTeamList() {
        this.teamList.clear();
    }

    @Override
    public void saveTeamList() {
        //TODO: Muss abgespeichert werden
    }

    @Override
    public void loadTeamList(){
        //Ruft vorhande TeamList auf oder generiert neue
    }

    @Override
    public void generateTeamNumber(){
        int counter;
        for (counter = 0; counter <= this.teamList.size(); counter++){
            this.teamList.get(counter).setTeamNumber(counter);
        }
    }

    @Override
    public int getSizeTeamList(){
        return this.teamList.size();
    }

    @Override
    public void shuffleTeamList(){
        Collections.shuffle(this.teamList);
    }

    @Override
    public Boolean possibleTeamList(I_Tournament_Type tournamentType){
        Boolean teamispossible = true;
        if (tournamentType.isPossibleTeamNumber(getSizeTeamList()) == false){
            teamispossible = false;
        }
        //TODO: weitere beliebige checks einfügen ob mit der liste alles inordnung ist
        return teamispossible;
    }
}
