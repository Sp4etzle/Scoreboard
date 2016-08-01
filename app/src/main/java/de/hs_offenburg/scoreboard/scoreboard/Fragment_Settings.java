package de.hs_offenburg.scoreboard.scoreboard;

import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

/**
 * Created by micha on 13.05.2016.
 */
public class Fragment_Settings extends Fragment{

    Switch connectToBoard;
    boolean boardIsConnected = false;
    View settingsView;

    //Have to be similar like Connected Thread!!!
    BluetoothAdapter bAdapter;
    private static final int REQUEST_ENABLE_BT = 9;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        settingsView = inflater.inflate(R.layout.settings_layout, container, false);

        connectToBoard=(Switch)settingsView.findViewById(R.id.connectToBoard);
        connectToBoard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    bAdapter = BluetoothAdapter.getDefaultAdapter();
                    if (!bAdapter.isEnabled()) {
                        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                    }
                    IntentFilter filter = new IntentFilter();
                    filter.addAction(BluetoothDevice.ACTION_FOUND);
                    filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
                    filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
                    filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);


                }else{
                    boardIsConnected = false;
                    bAdapter.cancelDiscovery();
                }
            }
        });


        return settingsView;
    }
}
