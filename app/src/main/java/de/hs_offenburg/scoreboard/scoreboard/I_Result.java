package de.hs_offenburg.scoreboard.scoreboard;

/**
 * Created by micha on 31.05.2016.
 */
public interface I_Result {
    void setPointTeam1(int pointTeam1);
    void setPointTeam2(int pointTeam2);
    int getPointTeam1();
    int getPointTeam2();
    String getResult();

    Boolean increasePointTeam1();
    Boolean increasePointTeam2();
    Boolean decreasePointTeam1();
    Boolean decreasePointTeam2();

    void addPoints(I_Result addPoints);
    void switchResult();
}
