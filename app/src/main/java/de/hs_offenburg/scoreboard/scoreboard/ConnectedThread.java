package de.hs_offenburg.scoreboard.scoreboard;

import android.bluetooth.BluetoothSocket;
import android.text.Html;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by micha on 01.08.2016.
 */
public class ConnectedThread extends Thread {
    private static final String TAG = ConnectedThread.class.getSimpleName();
    //Bluetooth Data
    public static BluetoothSocket socket = null;
    private InputStream inStream = null;
    private OutputStream outStream = null;
    private List<Byte> buffer;
    private boolean runnable = true;
    private GameInfoThread gameInfo = null;
    private final char[] hexArray = "0123456789ABCDEF".toCharArray();
    private final android.os.Handler lHandler = new android.os.Handler();
    public static ConnectedThread thread;
    public String test;

    //Commands via Bluetooth
    public static final Integer PLAYER1 = 1;
    public static final Integer PLAYER2 = 2;
    public static final byte SET_PLAYTIME = 0x10;
    public static final byte SCORE_PLAYER1 = 0x11;
    public static final byte SCORE_PLAYER2 = 0x12;
    public static final byte STOP_GAME = 0x13;
    public static final byte START_PAUSE_GAME = 0x14;
    public static final byte PAUSE_GAME = 0x17;
    public static final byte REQUEST_TIME = 0x16;
    public static final int REQUEST_ENABLE_BT = 9;

    public ConnectedThread(BluetoothSocket socket) {
        if (socket != null) {
            this.socket = socket;
            try {
                inStream = this.socket.getInputStream();
                outStream = this.socket.getOutputStream();
                gameInfo = new GameInfoThread();
                //TODO: Hier passiert irgendwas mit Daten holen
                gameInfo.start();
            } catch (IOException e) {
            }
            buffer = new ArrayList<Byte>();
        }
    }

    public String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[(bytes.length * 3)];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[(j * 2)] = hexArray[v >>> 4];
            hexChars[(j * 2) + 1] = hexArray[v & 0x0F];
            hexChars[(j * 2) + 2] = ' ';
        }
        return new String(hexChars);
    }

    public void write(byte[] bytes) {
        if (this.outStream == null || this.socket == null)
            return;
        try {
            outStream.write(bytes);
        } catch (IOException e) {
        }
    }

    public void run() {
        Log.d(TAG, "thread run");
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
                                            goalOne = buffer[lIndex+1];
                                            //TODO: Team 1 Tore
                                            test = Integer.toString(goalOne);
                                            Log.i(TAG,test);
                                            goalTwo = buffer[lIndex+2];
                                            //TODO: Team 2 Tore
                                            test = Integer.toString(goalTwo);
                                            Log.i(TAG,test);
                                        }
                                        if (buffer[lIndex] == 0x40) {
                                            int time;
                                            time = ((buffer[lIndex+1] & 0xFF) << 8);
                                            time |= buffer[lIndex+2] & 0xFF;
                                            //TODO: Zeit abgreifen
                                            test = Integer.toString(time);
                                            Log.i(TAG,test);
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
    public void close() {
        runnable = false;
        try {
            inStream = null;
            outStream = null;
            this.socket.close();
        } catch (IOException e) {
        }
        gameInfo.Cancel();
    }
}
