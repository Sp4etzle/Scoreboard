package de.hs_offenburg.scoreboard.scoreboard;

/**
 * Created by micha on 31.05.2016.
 */
public class O_Result implements I_Result{
    int pointTeam1;
    int pointTeam2;

    public O_Result() {
        pointTeam1 = 0;
        pointTeam2 = 0;
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

    @Override
    public void addPoints(I_Result addPoints){
        this.pointTeam1 = this.pointTeam1 + addPoints.getPointTeam1();
        this.pointTeam2 = this.pointTeam2 + addPoints.getPointTeam2();
    }

    @Override
    public void switchResult(){
        int buffer = this.pointTeam1;
        this.pointTeam1 = this.pointTeam2;
        this.pointTeam2 = buffer;
    }

    @Override
    public String getResult(){
        String result;
        if (this.pointTeam1 < 10){
            result = "0" + pointTeam1 +":";
        }else{
            result = pointTeam1 + ":";
        }
        if (this.pointTeam2 < 10){
            result = result + "0" + pointTeam2;
        }else{
            result = result + pointTeam2;
        }
        return result;
    }
}
