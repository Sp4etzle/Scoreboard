package de.hs_offenburg.scoreboard.scoreboard;

/**
 * Created by micha on 31.05.2016.
 */
public interface I_Time {

    //Time
    void setTimeSec(int timeSec);
    int getTimeSec();
    void setTimeMin(int timeMin);
    int getTimeMin();
    void setTimeHour(int timeHour);
    int getTimeHour();
    String getTime();

    //Time function

    void increaseTimeSec();
    void decreaseTimeSec();

    void increaseTime(int isec, int imin, int ihour);
    void decreaseTime(int isec, int imin, int ihour);

    void isetTime(int time);
    int igetTime();

    void setTimeNull();
    Boolean isTimeNull();
}
