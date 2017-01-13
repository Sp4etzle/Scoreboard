package de.hs_offenburg.scoreboard.scoreboard;

/**
 * Created by micha on 01.08.2016.
 */
public class T_GameInfoThread extends Thread{

    private boolean runnable;
    // constructor
    public T_GameInfoThread(){
        runnable = true;
    }

    // stops the thread */
    public void Cancel()
    {
        runnable = false;
    }

    @Override
    public void run()
    {
        while (runnable)
        {
            writeBT(new byte[] { T_ConnectedThread.REQUEST_TIME, 0x00, 0x00 });
            try {
                sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void writeBT(byte[] bytes) {
        if ((T_ConnectedThread.thread == null)
                || (!T_ConnectedThread.thread.isAlive())) {
            return;
        }
        T_ConnectedThread.thread.write(bytes);
    }
}
