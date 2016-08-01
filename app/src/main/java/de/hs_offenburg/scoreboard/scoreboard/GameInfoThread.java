package de.hs_offenburg.scoreboard.scoreboard;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by micha on 01.08.2016.
 */
public class GameInfoThread extends Thread{
    private InputStream inStream = null;
    private final android.os.Handler lHandler = new android.os.Handler();
    private boolean runnable;
    // constructor
    public GameInfoThread(){
        runnable = true;
    }

    // stops the thread */
    public void Cancel()
    {
        runnable = false;
    }



    @Override
    public void run() {
        while (runnable) {
            final byte[] buffer;
            int bytesAvailable;
            try {
                bytesAvailable = inStream.available();
                if (bytesAvailable > 2) {
                    buffer = new byte[bytesAvailable];
                    inStream.read(buffer);
                    try {
                        lHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                for (int lIndex = 0; lIndex < buffer.length; lIndex += 3) {
                                    if (buffer[lIndex] == 0x41 || buffer[lIndex] == 0x40) {
                                        if (buffer[lIndex] == 0x41) {
                                            int goalOne, goalTwo;
                                            String lGoals = new String();
                                            goalOne = buffer[lIndex+1];
                                            goalTwo = buffer[lIndex+2];
                                            lGoals = String.valueOf(goalOne) + ":" + String.valueOf(goalTwo);
                                            //goalLabel.setText(lGoals);
                                        }
                                        if (buffer[lIndex] == 0x40) {
                                            int time;
                                            String lTimeLabel = new String();
                                            time = ((buffer[lIndex+1] & 0xFF) << 8);
                                            time |= buffer[lIndex+2] & 0xFF;
                                            if ((time / 60) < 10) {
                                                lTimeLabel = "0" + String.valueOf(time / 60);
                                            } else {
                                                lTimeLabel = String.valueOf(time / 60);
                                            }
                                            lTimeLabel += ":";
                                            if ((time % 60) < 10) {
                                                lTimeLabel += "0" + String.valueOf(time % 60);
                                            } else {
                                                lTimeLabel += String.valueOf(time % 60);
                                            }
                                            //timeLabel.setText(lTimeLabel);
                                        }
                                    }
                                }
                            }
                        });
                    } catch (Exception e) {
                    }
                }
            } catch (IOException e) {
                break;
            }
        }
    }
}
