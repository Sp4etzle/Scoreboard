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
        this.teamList.add(addTeam);
            //TODO: Fehlermeldung anzeigen, falls es das Team schon gibt
    }

    @Override
    public void deleteTeam(int deleteTeam) {
        this.teamList.remove(deleteTeam);
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
        for (counter = 0; counter < getSizeTeamList(); counter++){
            this.teamList.get(counter).setTeamNumber(counter+1);
        }
    }

    @Override
    public int getSizeTeamList(){
        if(this.teamList == null){
            return 0;
        }else {
            return this.teamList.size();
        }
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
    @Override
    public String[] getStringTeamList(){
        String[] teamListString = new String[getSizeTeamList()];
        for(int z = 0; z < getSizeTeamList(); z++){
            teamListString[z] = teamList.get(z).getTeamNumber() + " - " + teamList.get(z).getTeamName();
        }
        return teamListString;
    }

    @Override
    public I_TeamList getCopyTeamlist(){
        I_TeamList newTeamList = new O_TeamList();
        int i;
        for (i = 0; i < this.teamList.size();i++){
            newTeamList.addTeam(teamList.get(i));
        }
        return newTeamList;
    }
}
