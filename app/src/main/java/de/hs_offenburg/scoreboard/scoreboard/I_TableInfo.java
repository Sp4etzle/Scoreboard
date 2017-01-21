package de.hs_offenburg.scoreboard.scoreboard;

/**
 * Created by micha on 02.06.2016.
 */
public interface I_TableInfo {

    //amount of played games
    void setPlayedGames(int playedGames);
    int getPlayedGames();
    void increasePlayedGames();
    void decreasePlayedGames();

    //won games
    void setVictory(int victory);
    int getVictory();
    void increaseVictory();
    void decreaseVictory();

    //draw games
    void setDraw(int draw);
    int getDraw();
    void increaseDraw();
    void decreaseDraw();

    //lost games
    void setLost(int lost);
    int getLost();
    void increaseLost();
    void decreaseLost();

    //goal difference
    void setGoalDifference(I_Result goalDifference);
    I_Result getGoalDifference();
    void addGoalDifference(I_Result goalDifference);

    //table points
    void setTablePoints(int tablePoints);
    int getTablePoints();
    void increaseTablePoints(int increaseBy);
    void decreaseTablePoints(int decreaseBy);

    //placement
    void setPlacement(int placement);
    int getPlacement();

    String getTeam();
}
