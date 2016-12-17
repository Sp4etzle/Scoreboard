package de.hs_offenburg.scoreboard.scoreboard;

/**
 * Created by micha on 31.05.2016.
 */
public class O_Time implements I_Time{
    //Time
    int sec;
    int min;
    int hour;

    //create a default Time
    public O_Time(){
        this.sec = 0;
        this.min = 0;
        this.hour = 0;
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


    //Time Function
    //correct time (used by button)
    @Override
    public void correctIncreaseTime() {
        increaseTime(getTimeSec(), getTimeMin(), getTimeHour());
    }

    @Override
    public void correctDecreaseTime() {
        decreaseTime(getTimeSec(), getTimeMin(), getTimeHour());
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

    //increase or decrease time (used by functions)
    @Override
    public void increaseTime(int isec, int imin, int ihour) {
        //try to increase time until all parameter are 0
        while(ihour > 0 || imin > 0 || isec > 0) {
            if (ihour > 0) {
                if (this.hour < 99){
                    this.hour++;
                }
                ihour--;
            }
            if (imin > 0) {
                this.min++;
                if (this.min >= 60) {
                    ihour++;
                    this.min = 0;
                }
                imin--;
            }
            if (isec > 0) {
                this.sec++;
                if (this.sec >= 60) {
                    imin++;
                    this.sec = 0;
                }
                isec--;
            }
        }
    }

    @Override
    public void decreaseTime(int isec, int imin, int ihour) {
        //try to decrease time until parameter are 0 or time is 0 (followed by action)
        while(ihour > 0 || imin > 0 || isec > 0) {
            if (isec > 0){
                this.sec--;
                if (this.sec <= 0){
                    imin++;
                    this.sec = 59;
                }
                isec--;
            }
            if (imin > 0){
                this.min--;
                if (this.min <= 0){
                    ihour++;
                    this.min = 59;
                }
                imin--;
            }
            if (ihour > 0){
                this.hour--;
                ihour--;
            }
            if (isTimeNull() == true){
                setTimeNull();
                //TODO: Fill with Action for Counter = Null
            }
        }
    }

    public void isetTime(int time){
        this.setTimeHour(time / 3600);
        this.setTimeMin((time - this.getTimeHour() * 3600) / 60);
        this.setTimeSec(time - this.getTimeHour() * 3600 - this.getTimeMin() * 60);
    }

    public int igetTime(O_Time time){
        return (time.getTimeHour() * 3600 + time.getTimeMin() * 60 + time.getTimeSec());
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
