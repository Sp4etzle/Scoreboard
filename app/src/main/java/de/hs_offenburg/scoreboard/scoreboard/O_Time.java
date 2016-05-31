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
    //get and set current time
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
    //get and set default time
    //TODO: Muss evtl. irgendwoanders abgespeichert und abgerufen werden
    //      Selbiges gilt für CorrectTime
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
    //get and set the correct time
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
    //correct time (used by button)
    @Override
    public void correctIncreaseTime() {
        increaseTime(getCorrectSec(), getCorrectMin(), getCorrectHour());
    }

    @Override
    public void correctDecreaseTime() {
        decreaseTime(getCorrectSec(), getCorrectMin(), getCorrectHour());
    }

    //increase or decrease only 1 sec (used by timer)
    @Override
    public void increaseTimeSec() {
        increaseTime(1,0,0);
    }

    @Override
    public void decreaseTimeSec() {
        decreaseTime(1,0,0);
    }

    //TODO: Fill Time Function
    //increase or decrease time (used by functions)
    @Override
    public void increaseTime(int isec, int imin, int ihour) {
        //Muss die aktuelle Zeit um den Betrag erhöhen
        //Dabei muss darauf geachtet werden, das sec von 0-60 gehen
        //(Falls auf 30 sec, 45 sec drauf gerechnet werden müssen...

    }

    @Override
    public void decreaseTime(int isec, int imin, int ihour) {
        //Hierbei gilt selbiges wie bei increase Time
        //Außerdem muss immer nach isTimeNull abgefragt werden

    }

    //time null polling
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
