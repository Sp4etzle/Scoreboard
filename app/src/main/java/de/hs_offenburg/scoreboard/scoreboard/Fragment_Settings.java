package de.hs_offenburg.scoreboard.scoreboard;

import android.app.Activity;
import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import static de.hs_offenburg.scoreboard.scoreboard.Fragment_Game.state_game_running;
import static de.hs_offenburg.scoreboard.scoreboard.Fragment_Game.state_tournament_running;
/**
 * Created by micha on 13.05.2016.
 */


public class Fragment_Settings extends Fragment{
    private static final String TAG = Fragment_Settings.class.getSimpleName();
    //setup Elements on Fragment

    public Spinner deviceSpinner;
    private Button searchDevices;
    Switch connectToBoard;

    //Init variables
    public static boolean boardIsConnected = false;
    private  devices selectedDevice;
    private  ArrayList<String> deviceList;
    private  ArrayList<devices> detailList;
    private  ArrayAdapter<String> listAdapter;
    private  char[] btMessage;
    public  BluetoothAdapter bAdapter;
    public   BluetoothDevice btDevice;
    private   BluetoothSocket btSocket;
    public static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    static boolean dontShowToasts = true;

    public static I_Time defaulttime, correctTime;
    public static NumberPicker numberPickerSec,numberPickerMin;

    View settingsView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        settingsView = inflater.inflate(R.layout.settings_layout, container, false);

        //Init Bluetooth device list and other Components
        detailList = new ArrayList<devices>();
        deviceList = new ArrayList<String>();
        listAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, deviceList);
        listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        btMessage = new char[3];
        bAdapter = BluetoothAdapter.getDefaultAdapter();

        //TODO: Funktioniert noch nicht, funktion unbekannt
        //deviceSpinner.setAdapter(listAdapter);

        //Switch "Connected to Board"
        connectToBoard=(Switch)settingsView.findViewById(R.id.connectToBoard);
        connectToBoard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //Connect with device and starting get Information
                    connectBT(selectedDevice);
                    if (btSocket.isConnected() == false){
                        connectToBoard.setChecked(false);
                    }
                }else{
                    //Disable Bluetooth Connection
                    if (btSocket.isConnected()) {
                        disconnectBT();
                    }
                }
            }
        });


        //Button "Search Devices"
        searchDevices=(Button)settingsView.findViewById(R.id.searchDevices);
        searchDevices.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dontShowToasts = false;
                //activate Bluetooth
                if (!bAdapter.isEnabled()) {
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, T_ConnectedThread.REQUEST_ENABLE_BT);
                    bAdapter.startDiscovery();
                }

                //research Devices
                if ((bAdapter != null) && (bAdapter.isEnabled())) {
                    bAdapter.cancelDiscovery();
                    listPairedDevices(bAdapter);
                    bAdapter.startDiscovery();
                }

                final BroadcastReceiver mReceiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        String action = intent.getAction();
                        // When discovery finds a device
                        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                            // Get the BluetoothDevice object from the Intent
                            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                            // Add the name and address to an array adapter to show in a ListView
                            addSpinnerEntry(device.getName(),device.getAddress());
                            //deviceList.add(device.getName() + "\n" + device.getAddress());
                        }
                        if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                            final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,
                                    BluetoothAdapter.ERROR);
                            switch (state) {
                                case BluetoothAdapter.STATE_OFF:
                                    clearSpinner();
                                    break;
                                case BluetoothAdapter.STATE_TURNING_OFF:
                                    //bluetooth turned off
                                    if (bAdapter.isDiscovering())
                                        bAdapter.cancelDiscovery();
                                    connectToBoard.setChecked(false);
                                    //TODO: Wenn bAdapter auf null ist stürzt die App ab
                                    //bAdapter = null;
                                    break;
                                case BluetoothAdapter.STATE_ON:
                                    //bluetooth is on
                                    bAdapter = BluetoothAdapter.getDefaultAdapter();
                                    listPairedDevices(bAdapter);
                                    break;
                                case BluetoothAdapter.STATE_TURNING_ON:
                                    break;
                            }
                        }
                        if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
                            //bluetooth connected to device
                            connectToBoard.setChecked(true);
                            boardIsConnected = true;
                        }
                        if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
                            //bluetooth disconnected from device
                            connectToBoard.setChecked(false);
                        }

                    }
                };

                // Register the BroadcastReceiver (and setup filter)
                IntentFilter filter = new IntentFilter();
                filter.addAction(BluetoothDevice.ACTION_FOUND);
                filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
                filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
                filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
                getActivity().getApplicationContext().registerReceiver(mReceiver, filter);
            }
        });

        //Spinner Devices
        deviceSpinner=(Spinner)settingsView.findViewById(R.id.deviceSpinner);
        deviceSpinner.setAdapter(listAdapter);
        deviceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //a device has been selected
                if (dontShowToasts != true){
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Gew\u00e4hltes Ger\u00e4t:\n"
                                    + detailList.get(position).name
                                    + " " + detailList.get(position).adress,
                            Toast.LENGTH_LONG).show();
                }

                selectedDevice = new devices();
                selectedDevice.adress = detailList.get(position).adress;
                selectedDevice.name = detailList.get(position).name;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //DefaultTime
        if(defaulttime == null){
            defaulttime = new O_Time();
            defaulttime.setTimeMin(10);
        }
        NumberPicker numberPickerSec = (NumberPicker) settingsView.findViewById(R.id.numberPickerSec);
        numberPickerSec.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPickerSec.setMinValue(0);
        numberPickerSec.setMaxValue(59);
        numberPickerSec.setValue(defaulttime.getTimeSec());
        numberPickerSec.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                defaulttime.setTimeSec(newVal);
            }
        });

        NumberPicker numberPickerMin = (NumberPicker) settingsView.findViewById(R.id.numberPickerMin);
        numberPickerMin.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPickerMin.setMinValue(0);
        numberPickerMin.setMaxValue(59);
        numberPickerMin.setValue(defaulttime.getTimeMin());
        numberPickerMin.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                defaulttime.setTimeMin(newVal);
            }
        });

        //CorrectTime
        if(correctTime == null){
            correctTime = new O_Time();
            correctTime.setTimeMin(1);
        }
        NumberPicker numberPickerSecCT = (NumberPicker) settingsView.findViewById(R.id.numberPickerSecCT);
        numberPickerSecCT.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPickerSecCT.setMinValue(0);
        numberPickerSecCT.setMaxValue(59);
        numberPickerSecCT.setValue(correctTime.getTimeSec());
        numberPickerSecCT.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                correctTime.setTimeSec(newVal);
            }
        });

        NumberPicker numberPickerMinCT = (NumberPicker) settingsView.findViewById(R.id.numberPickerMinCT);
        numberPickerMinCT.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPickerMinCT.setMinValue(0);
        numberPickerMinCT.setMaxValue(59);
        numberPickerMinCT.setValue(correctTime.getTimeMin());
        numberPickerMinCT.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                correctTime.setTimeMin(newVal);
            }
        });

        return settingsView;
    }

    @Override
    public void onResume(){
        //Update Frame
        super.onResume();
        hideKeyboard(getActivity());
        //research Devices
        dontShowToasts = true;
        if ((bAdapter != null) && (bAdapter.isEnabled())) {
            bAdapter.cancelDiscovery();
            listPairedDevices(bAdapter);
            bAdapter.startDiscovery();
        }

        //Current running Tournament?
        if (state_tournament_running == true && boardIsConnected  == true){
            //bluetooth connected to device
            deviceSpinner.setEnabled(false);
            searchDevices.setEnabled(false);
        }else if (state_tournament_running == false && boardIsConnected == true){
            //bluetooth connected to device
            deviceSpinner.setEnabled(false);
            searchDevices.setEnabled(false);
        }else if (state_game_running == true && boardIsConnected == false){
            deviceSpinner.setEnabled(false);
            searchDevices.setEnabled(false);
        }else if (state_game_running == false && boardIsConnected == false){
            deviceSpinner.setEnabled(true);
            searchDevices.setEnabled(true);
        }
    }

    private void updateButtons(Context ct) {
        //TODO: alles was an info zur aktuellen Seite gehört
        //research Devices

    }

    //add paired devices to SpinnerList
    private void listPairedDevices(BluetoothAdapter bt) {
        if (bt == null || !bt.isEnabled())
            return;
        clearSpinner();
        Set<BluetoothDevice> pairedDevices = bt.getBondedDevices();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                addSpinnerEntry(device.getName(), device.getAddress());
            }
        }
    }

    //add to SpinnerList
    private void addSpinnerEntry(String deviceName, String deviceAddress) {
        devices member = new devices();
        member.adress = deviceAddress;
        member.name = deviceName;
        detailList.add(member);
        deviceList.add(deviceName);
        listAdapter.notifyDataSetChanged();
    }

    //clear SpinnerList
    private void clearSpinner() {
        detailList.clear();
        deviceList.clear();
        listAdapter.notifyDataSetChanged();
    }

    //list devices after found one
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == T_ConnectedThread.REQUEST_ENABLE_BT) {
            if (resultCode == 0xffffffff)
                listPairedDevices(bAdapter);
            else {
                if (bAdapter.isDiscovering()) {
                    bAdapter.cancelDiscovery();
                }
                bAdapter = null;
            }
        }
    }

    private Boolean connectBT(devices device) {
        Log.d(TAG, "connectingBT");
        Boolean result = false;
        if (bAdapter == null || !bAdapter.isEnabled())
            return result;

        btDevice = bAdapter.getRemoteDevice(device.adress);
        try {
            Log.d(TAG, "tryconnectingBT");
            btSocket = btDevice.createRfcommSocketToServiceRecord(MY_UUID);
            bAdapter.cancelDiscovery();
            btSocket.connect();
            T_ConnectedThread.thread = new T_ConnectedThread(btSocket);
            T_ConnectedThread.thread.start();
            deviceSpinner.setEnabled(false);
            result = true;
            boardIsConnected = true;
        } catch (IOException e) {
            Toast.makeText(getActivity().getApplicationContext(), "Keine Verbindung m\u00f6glich\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return result;
    }

    public Boolean disconnectBT() {
        Boolean result = false;
        try {
            T_ConnectedThread.thread.close();
            btSocket.close();
            btDevice = null;
            if (state_tournament_running == false){
                deviceSpinner.setEnabled(true);
            }
            result = true;
            boardIsConnected = false;
        } catch (IOException e) {
            Toast.makeText(getActivity().getApplicationContext(), "Verbindung wurde nicht geschlossen", Toast.LENGTH_SHORT).show();
        }
        return result;
    }

    public static class devices {
        public String name;
        public String adress;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
