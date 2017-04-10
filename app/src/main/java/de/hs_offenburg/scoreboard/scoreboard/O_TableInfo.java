package de.hs_offenburg.scoreboard.scoreboard;

/**
 * Created by micha on 02.06.2016.
 */
public class O_TableInfo implements I_TableInfo {

    int playedGames;
    int victory;
    int draw;
    int lost;
    I_Result goalDifference;
    int tablePoints;
    int placement;
    String team;

    public O_TableInfo(){
        playedGames = 0;
        victory = 0;
        draw = 0;
        lost = 0;
        goalDifference = new O_Result();
        tablePoints = 0;
        placement = 0;
        team = "";
    }

    public O_TableInfo(String team){
        playedGames = 0;
        victory = 0;
        draw = 0;
        lost = 0;
        goalDifference = new O_Result();
        tablePoints = 0;
        placement = 0;
        this.team = team;
    }

    @Override
    public void setPlayedGames(int playedGames) {
        this.playedGames = playedGames;
    }

    @Override
    public int getPlayedGames() {
        return this.playedGames;
    }

    @Override
    public void increasePlayedGames() {
        this.playedGames++;
    }

    @Override
    public void decreasePlayedGames() {
        if(this.playedGames - 1 >= 0){
            this.playedGames--;
        }
    }

    @Override
    public void setVictory(int victory) {
        this.victory = victory;
    }

    @Override
    public int getVictory() {
        return this.victory;
    }

    @Override
    public void increaseVictory() {
        this.victory++;
    }

    @Override
    public void decreaseVictory() {
        if (this.victory - 1 >= 0){
            this.victory--;
        }
    }

    @Override
    public void setDraw(int draw) {
        this.draw = draw;
    }

    @Override
    public int getDraw() {
        return this.draw;
    }

    @Override
    public void increaseDraw() {
        this.draw++;
    }

    @Override
    public void decreaseDraw() {
        if(this.draw - 1 >= 0){
            this.draw--;
        }
    }

    @Override
    public void setLost(int lost) {
        this.lost = lost;
    }

    @Override
    public int getLost() {
        return this.lost;
    }

    @Override
    public void increaseLost() {
        this.lost++;
    }

    @Override
    public void decreaseLost() {
        if (this.lost - 1 >= 0){
            this.lost--;
        }
    }

    @Override
    public void setGoalDifference(I_Result goalDifference) {
        this.goalDifference.setPointTeam1(goalDifference.getPointTeam1());
        this.goalDifference.setPointTeam2(goalDifference.getPointTeam2());
    }

    @Override
    public I_Result getGoalDifference() {
        return this.goalDifference;
    }

    @Override
    public void addGoalDifference(I_Result goalDifference){
        this.goalDifference.addPoints(goalDifference);
    }

    @Override
    public void setTablePoints(int tablePoints) {
        this.tablePoints = tablePoints;
    }

    @Override
    public int getTablePoints() {
        return this.tablePoints;
    }

    @Override
    public void increaseTablePoints(int increaseBy) {
        this.tablePoints = this.tablePoints + increaseBy;
    }

    @Override
    public void decreaseTablePoints(int decreaseBy) {
        if (this.tablePoints - decreaseBy >= 0){
            this.tablePoints = this.tablePoints - decreaseBy;
        }
    }

    @Override
    public void setPlacement(int placement) {
        this.placement = placement;
    }

    @Override
    public int getPlacement() {
        return this.placement;
    }

    @Override
    public String getTeam(){
        return this.team;
    }

    @Override
    public int getintGoalDifference(){
        return  this.goalDifference.getPointTeam1()-this.goalDifference.getPointTeam2();
    }
}
