package de.hs_offenburg.scoreboard.scoreboard;

/**
 * Created by micha on 31.05.2016.
 */
public interface I_Time {

    //current Time
    void setTimeSec(int timeSec);
    int getTimeSec();
    void setTimeMin(int timeMin);
    int getTimeMin();
    void setTimeHour(int timeHour);
    int getTimeHour();

    //default Time
    void setDefaultTimeSec(int DefaultTimeSec);
    int getDefaultTimeSec();
    void setDefaultTimeMin(int DefaultTimeMin);
    int getDefaultTimeMin();
    void setDefaultTimeHour(int DefaultTimeHour);
    int getDefaultTimeHour();

    //correct Time
    void setCorrectSec(int correctSec);
    int getCorrectSec();
    void setCorrectMin(int correctMin);
    int getCorrectMin();
    void setCorrectHour(int correctHour);
    int getCorrectHour();

    //Time function
    void correctIncreaseTime();
    void correctDecreaseTime();

    void increaseTimeSec();
    void decreaseTimeSec();

    void increaseTime(int isec, int imin, int ihour);
    void decreaseTime(int isec, int imin, int ihour);

    void setTimeNull();//LALALALLA
    Boolean isTimeNull();
}
