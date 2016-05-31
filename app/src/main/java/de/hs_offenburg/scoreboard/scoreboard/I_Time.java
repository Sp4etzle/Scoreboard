package de.hs_offenburg.scoreboard.scoreboard;

/**
 * Created by micha on 31.05.2016.
 */
public interface I_Time {

    void setTimeSec(int timeSec);
    int getTimeSec();
    void setTimeMin(int timeMin);
    int getTimeMin();
    void setTimeHour(int timeHour);
    int getTimeHour();

    void setDefaultTimeSec(int DefaultTimeSec);
    int getDefaultTimeSec();
    void setDefaultTimeMin(int DefaultTimeMin);
    int getDefaultTimeMin();
    void setDefaultTimeHour(int DefaultTimeHour);
    int getDefaultTimeHour();

    void setCorrecteSec(int correctSec);
    int getCorrectSec();
    void setCorrectMin(int correctMin);
    int getCorrectMin();
    void setCorrectHour(int correctHour);
    int getCorrectHour();

    void correctIncreaseTime();
    void correctDecreaseTime();

    void increaseTime();
    void decreaseTime();
}
