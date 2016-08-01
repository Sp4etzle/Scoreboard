package de.hs_offenburg.scoreboard.scoreboard;

/**
 * Created by micha on 01.08.2016.
 */
public class GameInfoThread extends Thread{

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
    public void run()
    {
        while (runnable)
        {
            writeBT(new byte[] { ConnectedThread.REQUEST_TIME, 0x00, 0x00 });
            try {
                sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void writeBT(byte[] bytes) {
        if ((ConnectedThread.thread == null)
                || (!ConnectedThread.thread.isAlive())) {
            return;
        }
        ConnectedThread.thread.write(bytes);
    }
}
