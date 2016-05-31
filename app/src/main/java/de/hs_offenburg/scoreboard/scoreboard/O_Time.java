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

    //The Time which is used to correct the current Time with 1 button press
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
    public void setCorrectSec(int correctSec) {
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
        increaseTime(this.correctSec, this.correctMin, this.correctHour);
    }

    //TODO: Fill Time Function
    @Override
    public void correctDecreaseTime() {
        decreaseTime(this.correctSec, this.correctMin, this.correctHour);
    }

    @Override
    public void increaseTimeSec() {
        increaseTime(1,0,0);
    }

    @Override
    public void decreaseTimeSec() {
        decreaseTime(1,0,0);
    }

    @Override
    public void increaseTime(int isec, int imin, int ihour) {

    }

    @Override
    public void decreaseTime(int isec, int imin, int ihour) {

    }

    @Override
    public void setTimeNull(){
        this.sec = 0;
        this.min = 0;
        this.hour = 0;
    }

    @Override
    public Boolean isTimeNull(){
        Boolean timeIsNull = false;
        if(this.sec <= 0 && this.min <= 0 && this.hour <= 0){
            timeIsNull = true;
        }
        return timeIsNull;
    }
}
