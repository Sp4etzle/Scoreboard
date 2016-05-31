package de.hs_offenburg.scoreboard.scoreboard;

/**
 * Created by micha on 31.05.2016.
 */
public class O_Time implements I_Time{
    //The Default Time for the Gamestart
    int defaultSec = 0;
    int defaultMin = 15;
    int defaultHour = 0;

    //The Current Time for the Game
    int sec;
    int min;
    int hour;

    //The Time which is used to correct the current Time
    int correctSec = 0;
    int correctMin = 1;
    int correctHour = 0;

    //create a default Time
    public O_Time(){
        this.sec = getDefaultTimeSec();
        this.min = getDefaultTimeMin();
        this.hour = getDefaultTimeHour();
    }

    //Current Time
    @Override
    public void setTimeSec(int timeSec) {
        this.sec = timeSec;
    }

    @Override
    public int getTimeSec() {
        return this.sec;
    }

    @Override
    public void setTimeMin(int timeMin) {
        this.min = timeMin;
    }

    @Override
    public int getTimeMin() {
        return this.min;
    }

    @Override
    public void setTimeHour(int timeHour) {
        this.hour = timeHour;
    }

    @Override
    public int getTimeHour() {
        return this.hour;
    }

    //Default Time
    @Override
    public void setDefaultTimeSec(int DefaultTimeSec) {
        this.defaultSec = DefaultTimeSec;
    }

    @Override
    public int getDefaultTimeSec() {
        return this.defaultSec;
    }

    @Override
    public void setDefaultTimeMin(int DefaultTimeMin) {
        this.defaultMin = DefaultTimeMin;
    }

    @Override
    public int getDefaultTimeMin() {
        return this.defaultMin;
    }

    @Override
    public void setDefaultTimeHour(int DefaultTimeHour) {
        this.defaultHour = DefaultTimeHour;
    }

    @Override
    public int getDefaultTimeHour() {
        return this.defaultHour;
    }

    //Correct Time
    @Override
    public void setCorrecteSec(int correctSec) {
        this.correctSec = correctSec;
    }

    @Override
    public int getCorrectSec() {
        return this.correctSec;
    }

    @Override
    public void setCorrectMin(int correctMin) {
        this.correctMin = correctMin;
    }

    @Override
    public int getCorrectMin() {
        return this.correctMin;
    }

    @Override
    public void setCorrectHour(int correctHour) {
        this.correctHour = correctHour;
    }

    @Override
    public int getCorrectHour() {
        return this.correctHour;
    }

    //Time Function
    @Override
    public void correctIncreaseTime() {
        this.sec = this.sec + this.correctSec;
        this.min = this.min + this.correctMin;
        this.hour = this.hour + this.correctHour;
    }

    //TODO: Fill Time Function
    @Override
    public void correctDecreaseTime() {

    }

    @Override
    public void increaseTime() {

    }

    @Override
    public Boolean decreaseTime() {
        return null;
    }
}
