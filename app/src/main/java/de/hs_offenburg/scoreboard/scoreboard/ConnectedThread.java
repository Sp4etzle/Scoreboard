package de.hs_offenburg.scoreboard.scoreboard;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.widget.ArrayAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by micha on 01.08.2016.
 */
public class ConnectedThread extends Thread {
    //Bluetooth Connection
    private BluetoothDevice btDevice;
    private devices selectedDevice;
    private BluetoothSocket socket = null;
    private BluetoothAdapter bAdapter;
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private ArrayList<String> deviceList;
    private ArrayList<devices> detailList;
    private ArrayAdapter<String> listAdapter;

    //Bluetooth Data
    private char[] btMessage;
    private InputStream inStream = null;
    private OutputStream outStream = null;
    private List<Byte> buffer;
    private Boolean runnable = true;
    private GameInfoThread gameInfo = null;
    private final char[] hexArray = "0123456789ABCDEF".toCharArray();
    //private final android.os.Handler lHandler = new android.os.Handler();
    private ConnectedThread thread;

    //Commands via Bluetooth
    private static final Integer PLAYER1 = 1;
    private static final Integer PLAYER2 = 2;
    private static final byte SET_PLAYTIME = 0x10;
    private static final byte SCORE_PLAYER1 = 0x11;
    private static final byte SCORE_PLAYER2 = 0x12;
    private static final byte STOP_GAME = 0x13;
    private static final byte START_PAUSE_GAME = 0x14;
    private static final byte PAUSE_GAME = 0x17;
    private static final byte REQUEST_TIME = 0x16;
    private static final int REQUEST_ENABLE_BT = 9;

    public ConnectedThread(BluetoothSocket socket) {
        if (socket != null) {
            this.socket = socket;
            try {
                inStream = this.socket.getInputStream();
                outStream = this.socket.getOutputStream();
                gameInfo = new GameInfoThread();
                gameInfo.start();
            } catch (IOException e) {
            }
            buffer = new ArrayList<Byte>();
        }
    }

    private class devices {
        public String name;
        public String adress;
    }

    private void configureBluetooth() {
        bAdapter = BluetoothAdapter.getDefaultAdapter();
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

    private void writeBT(byte[] bytes) {
        if ((this.thread == null)
                || (!thread.isAlive())) {
            return;
        }
        thread.write(bytes);
    }

    private Boolean connectBT(devices device) {
        Boolean result = false;
        if (bAdapter == null || !bAdapter.isEnabled())
            return result;
        btDevice = bAdapter.getRemoteDevice(device.adress);

        try {
            socket = btDevice.createRfcommSocketToServiceRecord(MY_UUID);
            bAdapter.cancelDiscovery();
            socket.connect();
            thread = new ConnectedThread(socket);
            thread.start();
            result = true;
        } catch (IOException e) {

        }
        return result;
    }

    private Boolean disconnectBT() {
        Boolean result = false;
        try {
            thread.close();
            socket.close();
            btDevice = null;
            result = true;
        } catch (IOException e) {

        }
        return result;
    }



    public void run()
    {
        while (runnable)
        {
            writeBT(new byte[] { REQUEST_TIME, 0x00, 0x00 });
            try {
                sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
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
