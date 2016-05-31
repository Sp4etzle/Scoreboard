package de.hs_offenburg.scoreboard.scoreboard;

/**
 * Created by micha on 31.05.2016.
 */
public class O_Result implements I_Result{
    int pointTeam1 = 0;
    int pointTeam2 = 0;

    public O_Result() {
    }

    @Override
    public void setPointTeam1(int pointTeam1) {
        this.pointTeam1 = pointTeam1;
    }

    @Override
    public void setPointTeam2(int pointTeam2) {
        this.pointTeam2 = pointTeam2;
    }

    @Override
    public int getPointTeam1() {
        return this.pointTeam1;
    }

    @Override
    public int getPointTeam2() {
        return this.pointTeam2;
    }

    @Override
    public void increasePointTeam1() {
        this.pointTeam1++;
    }

    @Override
    public void increasePointTeam2() {
        this.pointTeam2++;
    }

    @Override
    public void decreasePointTeam1() {
        if (this.pointTeam1 >= 1){
            this.pointTeam1--;
        }
    }

    @Override
    public void decreasePointTeam2() {
        if (this.pointTeam2 >= 1){
            this.pointTeam2--;
        }
    }
}
