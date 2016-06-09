package de.hs_offenburg.scoreboard.scoreboard;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.TreeSet;

/**
 * Created by micha on 02.06.2016.
 */
public class O_Tournament implements I_Tournament{

    String tournamentName = generateTournamentName();
    I_Tournament_Type tournamentType;
    I_TeamList currentTeamList;
    I_TeamList allTeamList; //unused but might be important
    I_TableInfo[] tableInfo;
    Boolean roundFinished;
    Boolean tournamentActive;
    ArrayList<ArrayList<I_Game>> round = new ArrayList();

    public O_Tournament(I_Tournament_Type tournamentType,I_TeamList teamList){
        //generate table and name of tournament
        this.tournamentType = tournamentType;
        this.currentTeamList = teamList;
        this.currentTeamList.shuffleTeamList();
        this.tableInfo = new I_TableInfo[teamList.getSizeTeamList()];
        roundFinished = true;
        tournamentActive = true;
    }

    @Override
    public String generateTournamentName(){
        String tournamentName;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date currentDate = new Date();
        tournamentName = dateFormat.format(currentDate);
        return tournamentName;
    }

    @Override
    public void generateRound(){
        roundFinished = false;
        ArrayList<I_Game> gameList= new ArrayList<>();
        I_Game game;
        int team1;
        int team2;
        switch(this.tournamentType.getTournamentType()){
            case "ShortGame":
                team1 = 0;
                team2 = 1;
                game = new O_Game(currentTeamList.getTeam(team1), currentTeamList.getTeam(team2));
                gameList.add(game);
                break;
            case "AllvsAll":
                for(team1 = 0; team1 < currentTeamList.getSizeTeamList() - 1; team1++){
                    for(team2 = team1 + 1; team2 < currentTeamList.getSizeTeamList(); team2++){
                        game = new O_Game(currentTeamList.getTeam(team1), currentTeamList.getTeam(team2));
                        gameList.add(game);
                    }
                }
                Collections.shuffle(gameList);
                break;
            case "KOSystem":
                Boolean side = this.tournamentType.getBoolean();
                //side false = left | side true = right
                if (currentTeamList.getSizeTeamList()%2 == 0){
                    //amount of teams divisible by 2
                    for(team1 = 0; team1 < currentTeamList.getSizeTeamList() - 1; team1++){
                        team2 = team1+1;
                        game = new O_Game(currentTeamList.getTeam(team1), currentTeamList.getTeam(team2));
                        gameList.add(game);
                    }

                }else{
                    //amount of teams not divisible by 2
                    if (side == true){
                        side = false;
                        //generate games for all teams but the last one
                        for(team1 = 0; team1 < currentTeamList.getSizeTeamList() - 2; team1++){
                            team2 = team1+1;
                            game = new O_Game(currentTeamList.getTeam(team1), currentTeamList.getTeam(team2));
                            gameList.add(game);
                        }
                        //free win for the right side (last team in list)
                        this.tableInfo[currentTeamList.getTeam(currentTeamList.getSizeTeamList()-1).getTeamNumber()].increaseVictory();
                        this.tableInfo[currentTeamList.getTeam(currentTeamList.getSizeTeamList()-1).getTeamNumber()].increaseTablePoints(3);
                    }else{
                        side = true;
                        //generate games for all teams but the first one
                        for(team1 = 1; team1 < currentTeamList.getSizeTeamList() - 1; team1++){
                            team2 = team1+1;
                            game = new O_Game(currentTeamList.getTeam(team1), currentTeamList.getTeam(team2));
                            gameList.add(game);
                        }
                        //free win for the left side (first team in list)
                        this.tableInfo[currentTeamList.getTeam(0).getTeamNumber()].increaseVictory();
                        this.tableInfo[currentTeamList.getTeam(0).getTeamNumber()].increaseTablePoints(3);
                    }
                    tournamentType.setBoolean(side);
                }
            case "Groupphase":
                //TODO: Logik für Gruppenphase überlegen
        }
        //add the gameList which contain all games for one round to the current round
        this.round.add(gameList);
    }

    @Override
    public void updateTablePoints(I_Game currentGame){
        if (currentGame.result().getPointTeam1() == currentGame.result().getPointTeam2()){
            //draw
            if (this.tournamentType.isDrawPossible() == true){
                //draw is possible
                this.tableInfo[currentGame.team1().getTeamNumber()].increasePlayedGames();
                this.tableInfo[currentGame.team1().getTeamNumber()].increaseDraw();
                this.tableInfo[currentGame.team1().getTeamNumber()].increaseTablePoints(1);
                this.tableInfo[currentGame.team1().getTeamNumber()].addGoalDifference(currentGame.result());

                this.tableInfo[currentGame.team2().getTeamNumber()].increasePlayedGames();
                this.tableInfo[currentGame.team2().getTeamNumber()].increaseDraw();
                this.tableInfo[currentGame.team2().getTeamNumber()].increaseTablePoints(1);
                this.tableInfo[currentGame.team2().getTeamNumber()].addGoalDifference(currentGame.result());
            }else{
                //1 Team have to win (because of KOSystem etc.)
                //TODO: Irgend ne fehlermeldung da beim KO System einer gewinnen muss...
            }
        }else {
            if (currentGame.result().getPointTeam1() < currentGame.result().getPointTeam2()){
                //if second team won, switch to the winner place (first)
                currentGame.switchTeams();
            }
            this.tableInfo[currentGame.team1().getTeamNumber()].increasePlayedGames();
            this.tableInfo[currentGame.team1().getTeamNumber()].increaseVictory();
            this.tableInfo[currentGame.team1().getTeamNumber()].increaseTablePoints(3);
            this.tableInfo[currentGame.team1().getTeamNumber()].addGoalDifference(currentGame.result());

            this.tableInfo[currentGame.team2().getTeamNumber()].increasePlayedGames();
            this.tableInfo[currentGame.team2().getTeamNumber()].increaseLost();
            this.tableInfo[currentGame.team2().getTeamNumber()].increaseTablePoints(0);
            this.tableInfo[currentGame.team2().getTeamNumber()].addGoalDifference(currentGame.result());
        }

        //Update the placement based on tablePoints
        int counter;
        int placement = 0;
        TreeSet<Integer> Score = new TreeSet<>();
        //fill the different Scores in a treeSet
        for (counter = 0; counter < tableInfo.length; counter++){
            Score.add(tableInfo[counter].getTablePoints());
        }
        //give the placement based on the tablePoints in the treeSet
        while (Score.isEmpty() == false){
            placement++;
            for (counter = 0; counter < tableInfo.length; counter++){
                if (tableInfo[counter].getTablePoints() == Score.last()){
                    tableInfo[counter].setPlacement(placement);
                }
            }
            Score.pollLast();
        }
    }

    @Override
    public Boolean startNextGame(){
        Boolean gamesAvaible = true;
        //TODO: Define start of next game
        return gamesAvaible;
    }
}
