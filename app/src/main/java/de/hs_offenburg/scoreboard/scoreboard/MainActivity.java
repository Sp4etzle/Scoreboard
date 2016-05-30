package de.hs_offenburg.scoreboard.scoreboard;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.UUID;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    /////////////////Code below///////////////////////////
    /////////////Used by the group before/////////////////
    //////////////////////////////////////////////////////
    //Variable for Layout on Screen
    private Button start, reset, player1, player2, timeSet;
    private TextView timeLabel, goalLabel;

    //Variable that will send to the Bluetooth Controller
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static final Integer PLAYER1 = 1;
    private static final Integer PLAYER2 = 2;
    private static final byte SET_PLAYTIME = 0x10;
    private static final byte SCORE_PLAYER1 = 0x11;
    private static final byte SCORE_PLAYER2 = 0x12;
    private static final byte STOP_GAME = 0x13;
    private static final byte START_PAUSE_GAME = 0x14;
    private static final byte PAUSE_GAME = 0x17;
    private static final byte REQUEST_TIME = 0x16;

    //Variable for Screen to check Bluetooth state
    private Spinner deviceSpinner;
    private devices selectedDevice;
    private ArrayList<String> deviceList;
    private ArrayList<devices> detailList;
    private ArrayAdapter<String> listAdapter;

    private BluetoothAdapter bAdapter;
    private static final int REQUEST_ENABLE_BT = 9;
    private BluetoothDevice btDevice;
    private BluetoothSocket btSocket;
    private char[] btMessage;

//  Cant use in this version...
//    private ConnectedThread thread;
//    private GoogleApiClient client;


    private class devices {
        public String name;
        public String adress;
    }


    //////////////////Code above//////////////////////////
    /////////////Used by the group before/////////////////
    //////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.app.FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_game) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new GameFragment()).commit();
        } else if (id == R.id.nav_tournament) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new TournamentFragment()).commit();
        } else if (id == R.id.nav_table) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new TableFragment()).commit();
        } else if (id == R.id.nav_history) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new HistoryFragment()).commit();
        }else if (id == R.id.nav_music) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new MusicFragment()).commit();
        } else if (id == R.id.nav_settings) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new SettingsFragment()).commit();
        }else if (id == R.id.nav_imprint) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new ImprintFragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void resetApp(){

    }
}
